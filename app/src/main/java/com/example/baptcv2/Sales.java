package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.baptcv2.Adapter.SalesViewHolder;
import com.example.baptcv2.Database.SessionManager;
import com.example.baptcv2.Database.Sold;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Sales extends AppCompatActivity {

    private FirebaseRecyclerOptions<Sold> options;

    private RecyclerView recyclerView;

    String phoneNum;

    SalesViewHolder adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sales);

        SessionManager sessionManager = new SessionManager(Sales.this, SessionManager.SESSION_USERSESSION);
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
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String value = String.valueOf(ds.child("date_sales").getValue());
                    Log.i("OUR VALUE", value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Sold>().setQuery(phoneRef, Sold.class).build();

        adapter = new SalesViewHolder(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}