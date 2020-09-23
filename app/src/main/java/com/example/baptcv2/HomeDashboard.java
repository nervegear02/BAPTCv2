package com.example.baptcv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HomeDashboard extends AppCompatActivity implements View.OnClickListener {

    CardView profile, pricelist, croplist, ship, sales, signout;

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
                i = new Intent(this, Profile.class);
                startActivity(i);
                break;


            case R.id.cardview_ship:
                i = new Intent(this, Profile.class);
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