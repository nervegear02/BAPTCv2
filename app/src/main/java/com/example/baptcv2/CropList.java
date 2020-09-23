package com.example.baptcv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.baptcv2.Adapter.CropsAdapter;
import com.example.baptcv2.Database.Crops;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CropList extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    List<Crops> cropsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_crop_list);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fab_add);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        initData();
        setRecyclerView();
    }

    private void openDialog() {

        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(),"Add Dialog");
    }

    private void setRecyclerView() {
        CropsAdapter cropsAdapter = new CropsAdapter(cropsList);
        recyclerView.setAdapter(cropsAdapter);
    }

    private void initData() {
        cropsList = new ArrayList<>();
        cropsList.add(new Crops("Sample", "Sample", "Sample", "Sample", "Sample"));
        cropsList.add(new Crops("Sample", "Sample", "Sample", "Sample", "Sample"));
        cropsList.add(new Crops("Sample", "Sample", "Sample", "Sample", "Sample"));

    }
}