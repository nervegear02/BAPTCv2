package com.example.baptcv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.baptcv2.Adapter.CropsAdapter;
import com.example.baptcv2.Adapter.ToolViewAdapter;
import com.example.baptcv2.Database.Crops;
import com.example.baptcv2.Database.ToolsDB;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ToolsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    ToolViewAdapter toolViewAdapter;
    FirebaseRecyclerOptions<ToolsDB> options;
    FirebaseRecyclerAdapter<ToolsDB, ToolViewAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Tools");
        options = new FirebaseRecyclerOptions.Builder<ToolsDB>()
                .setQuery(ref, ToolsDB.class).build();
        adapter = new FirebaseRecyclerAdapter<ToolsDB, ToolViewAdapter>(options){

            @NonNull
            @Override
            public ToolViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tools_layout, parent, false);
                return new ToolViewAdapter(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ToolViewAdapter holder, int position, @NonNull ToolsDB model) {
                Picasso.get().load(model.getImage()).into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                holder.textView.setText(model.getName());
                holder.textView_description.setText(model.getDescription());

            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }
}