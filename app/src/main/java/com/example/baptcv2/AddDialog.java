package com.example.baptcv2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.baptcv2.Database.Crops;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDialog extends AppCompatDialogFragment {

    EditText add_crop_name, add_crop_price, add_crop_volume;
    DatePicker datePlanted, dateHarvested;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_dialog, null);
        builder.setView(view)
                .setTitle("Add")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String crop_name = add_crop_name.getText().toString();
                        String crop_price = add_crop_price.getText().toString();
                        String crop_volume = add_crop_volume.getText().toString();
                        int plantedday = datePlanted.getDayOfMonth();
                        int plantedmonth = datePlanted.getMonth();
                        int plantedyear = datePlanted.getYear();
                        String planted_date = plantedday + "/" + plantedmonth + "/" + plantedyear;
                        int harvestedday = dateHarvested.getDayOfMonth();
                        int harvestedmonth = dateHarvested.getMonth();
                        int harvestedyear = dateHarvested.getYear();
                        String harvested_date = harvestedday + "/" + harvestedmonth + "/" + harvestedyear;

                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Users");
                        Crops addCrops = new Crops(crop_name, crop_price, crop_volume, planted_date, harvested_date);
                        myRef.child("Crops").setValue(addCrops);


                    }
                });

        add_crop_name = view.findViewById(R.id.add_crop_name);
        add_crop_price = view.findViewById(R.id.add_crop_price);
        add_crop_volume = view.findViewById(R.id.add_crop_volume);
        datePlanted = view.findViewById(R.id.planted_picker);
        dateHarvested = view.findViewById(R.id.harvested_picker);
        return builder.create();
    }
}
