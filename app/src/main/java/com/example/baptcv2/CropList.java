package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baptcv2.Adapter.CropsAdapter;
import com.example.baptcv2.Callbacks.MyButtonClickListener;
import com.example.baptcv2.Database.Crops;
import com.example.baptcv2.Database.SessionManager;
import com.example.baptcv2.Database.ShipDB;
import com.example.baptcv2.Database.Sold;
import com.example.baptcv2.Helper.MySwipeHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CropList extends AppCompatActivity {

    RecyclerView recyclerView;
    CropsAdapter cropsAdapter;
    FloatingActionButton floatingActionButton;
    List<Crops> cropsList;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String phoneNum, date_sales;
    DatabaseReference phoneRef;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_crop_list);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_sales = simpleDateFormat.format(calendar.getTime());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cropsList = new ArrayList<>();
        SessionManager sessionManager = new SessionManager(CropList.this, SessionManager.SESSION_USERSESSION);
        if (sessionManager.checkLogin()) {
            HashMap<String, String> userDetails = sessionManager.getUsersDetailsFromSession();
            phoneNum = userDetails.get(SessionManager.KEY_SESSIONPHONENO);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference userRef = myRef.child(phoneNum);
        phoneRef = userRef.child("Crops");
        phoneRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Crops data = ds.getValue(Crops.class);
                    cropsList.add(data);
                }
                cropsAdapter = new CropsAdapter(cropsList);
                recyclerView.setAdapter(cropsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        floatingActionButton = findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cropsList.clear();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");
                DatabaseReference userRef = myRef.child(phoneNum);
                phoneRef = userRef.child("Crops");
                phoneRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Crops ndata = ds.getValue(Crops.class);
                            cropsList.add(ndata);
                        }
                        cropsAdapter = new CropsAdapter(cropsList);
                        recyclerView.setAdapter(cropsAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                refreshLayout.setRefreshing(false);
            }
        });

        MySwipeHelper swipeHelper = new MySwipeHelper(this, recyclerView, 200) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {
                buffer.add(new MyButton(CropList.this,
                        "Sold",
                        30,
                        0,
                        Color.parseColor("#333639"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Common.cropsSelected = cropsList.get(pos);
                                String _crop_name = String.valueOf(Common.cropsSelected.getCrop_name());
                                String _crop_price = String.valueOf(Common.cropsSelected.getCrop_price());
                                String _crop_volume = String.valueOf(Common.cropsSelected.getCrop_volume());
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Users");
                                DatabaseReference userRef = myRef.child(phoneNum);
                                DatabaseReference phoneRef = userRef.child("Sales");
                                Sold addSales = new Sold(_crop_name, _crop_price, _crop_volume, date_sales);
                                phoneRef.push().setValue(addSales);
                            }
                        }));

                buffer.add(new MyButton(CropList.this,
                        "Ship",
                        30,
                        0,
                        Color.parseColor("#560027"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Common.cropsSelected = cropsList.get(pos);
                                showShipDialog();
                            }
                        }));
            }
        };
    }

    private void showShipDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CropList.this);
        builder.setTitle("Ship");
        builder.setMessage("Please fill information");

        View itemView = LayoutInflater.from(CropList.this).inflate(R.layout.layout_ship_dialog, null);
        final EditText edt_destination_name = (EditText) itemView.findViewById(R.id.edt_destination_name);

        builder.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setPositiveButton("SHIP", (dialogInterface, i) -> {
            Map<String, Object> addShip = new HashMap<>();
            addShip.put("name", edt_destination_name.getText().toString());
            addShip(addShip);
        });

        builder.setView(itemView);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addShip(Map<String, Object> addShip) {
        String _crop_name = String.valueOf(Common.cropsSelected.getCrop_name());
        String _crop_price = String.valueOf(Common.cropsSelected.getCrop_price());
        String _crop_volume = String.valueOf(Common.cropsSelected.getCrop_volume());
        String _destination = addShip.values().toString().replace("[", "")
                .replace("]", "")
                .trim();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference userRef = myRef.child(phoneNum);
        DatabaseReference phoneRef = userRef.child("Shipping");
        ShipDB addShipValue = new ShipDB(_crop_name, _crop_price, _crop_volume, date_sales, _destination);
        phoneRef.push().setValue(addShipValue);

    }

    private void openDialog() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "Add Dialog");
    }

}