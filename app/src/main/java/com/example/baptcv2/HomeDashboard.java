package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.baptcv2.Database.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class HomeDashboard extends AppCompatActivity implements View.OnClickListener {

    CardView profile, pricelist, croplist, ship, sales, signout;

    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_dashboard);

        //Define cardview
        profile = (CardView) findViewById(R.id.cardview_profile);
        pricelist = (CardView) findViewById(R.id.cardview_pricelist);
        croplist = (CardView) findViewById(R.id.cardview_croplist);
        ship = (CardView) findViewById(R.id.cardview_ship);
        sales = (CardView) findViewById(R.id.cardview_sales);
        signout = (CardView) findViewById(R.id.cardview_signout);

        //Click Listener
        profile.setOnClickListener(this);
        pricelist.setOnClickListener(this);
        croplist.setOnClickListener(this);
        ship.setOnClickListener(this);
        sales.setOnClickListener(this);
        signout.setOnClickListener(this);

        SessionManager sessionManager = new SessionManager(HomeDashboard.this, SessionManager.SESSION_USERSESSION);
        if (sessionManager.checkLogin()) {
            HashMap<String, String> userDetails = sessionManager.getUsersDetailsFromSession();
            phoneNum = userDetails.get(SessionManager.KEY_SESSIONPHONENO);
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        DatabaseReference userRef = ref.child(phoneNum);
        DatabaseReference phoneRef = userRef.child("Sales");
        phoneRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                    String value = String.valueOf(ds.child("crop_volume").getValue());
                    Log.i("OUR VALUE", value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.cardview_profile:
                //Get all values passed from previous screens
                String _fullname = getIntent().getStringExtra("fullname");
                String _origin = getIntent().getStringExtra("origin");
                String _current = getIntent().getStringExtra("current");
                String _dateOfBirth = getIntent().getStringExtra("date");
                String _phoneNo = getIntent().getStringExtra("phoneNo");
                i = new Intent(this, Profile.class);
                i.putExtra("fullname", _fullname);
                i.putExtra("origin", _origin);
                i.putExtra("current", _current);
                i.putExtra("date", _dateOfBirth);
                i.putExtra("phoneNo", _phoneNo);
                startActivity(i);
                break;

            case R.id.cardview_pricelist:
                i = new Intent(this, Prices.class);
                startActivity(i);
                break;


            case R.id.cardview_croplist:
                i = new Intent(this, CropList.class);
                startActivity(i);
                break;


            case R.id.cardview_ship:
                i = new Intent(this, Ship.class);
                startActivity(i);
                break;


            case R.id.cardview_sales:
                i = new Intent(this, Sales.class);
                startActivity(i);
                break;


            case R.id.cardview_signout:
                i = new Intent(HomeDashboard.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;

        }

    }
}