package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.baptcv2.Adapter.CropsAdapter;
import com.example.baptcv2.Database.Crops;
import com.example.baptcv2.Database.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CropList extends AppCompatActivity {

    RecyclerView recyclerView;
    CropsAdapter cropsAdapter;
    FloatingActionButton floatingActionButton;
    List<Crops> cropsList;
    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_crop_list);

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
        DatabaseReference phoneRef = userRef.child("Crops");
        phoneRef.keepSynced(true);
        phoneRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Crops data = ds.getValue(Crops.class);
                    cropsList.add(data);
                }
                cropsAdapter = new CropsAdapter(cropsList);
                recyclerView.setAdapter(cropsAdapter);
                cropsAdapter.notifyDataSetChanged();
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

    }

    private void openDialog() {

        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(),"Add Dialog");
    }
}