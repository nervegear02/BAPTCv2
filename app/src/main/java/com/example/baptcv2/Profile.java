package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView header_fullname, profile_date, profile_origin, profile_fullname, profile_current, profile_phonenum;
    String user_origin, user_fname, user_date, user_phoneNo, user_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);


        //Hooks
        profile_date = findViewById(R.id.profile_date);
        profile_fullname = findViewById(R.id.profile_fullname);
        profile_phonenum = findViewById(R.id.profile_phonenum);
        profile_origin = findViewById(R.id.profile_origin);
        header_fullname = findViewById(R.id.header_fullname);
        profile_current = findViewById(R.id.profile_current);

        //Show Data
        showAllData();
    }

    private void showAllData() {
        Intent intent = getIntent();
        user_current = intent.getStringExtra("current");
        user_fname = intent.getStringExtra("fullname");
        user_date = intent.getStringExtra("date");
        user_phoneNo = intent.getStringExtra("phoneNo");
        user_origin= intent.getStringExtra("origin");

        header_fullname.setText(user_fname);
        profile_origin.setText(user_origin);
        profile_fullname.setText(user_fname);
        profile_date.setText(user_date);
        profile_phonenum.setText(user_phoneNo);
        profile_current.setText(user_current);

    }


}