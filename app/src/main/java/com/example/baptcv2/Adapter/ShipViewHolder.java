package com.example.baptcv2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baptcv2.Database.ShipDB;
import com.example.baptcv2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ShipViewHolder extends FirebaseRecyclerAdapter<ShipDB, ShipViewHolder.shipViewHolder> {

    public ShipViewHolder(@NonNull FirebaseRecyclerOptions<ShipDB> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull shipViewHolder holder, int position, @NonNull ShipDB model) {
        holder.crop_name.setText(model.getCrop_name());
        holder.crop_price.setText("₱" + model.getCrop_price());
        holder.crop_destination.setText(model.getDestination());
        holder.date_ship.setText(model.getDate_ship());
        holder.crop_volume.setText(model.getCrop_volume() + "kg");

    }

    @NonNull
    @Override
    public shipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ship_view_layout, parent, false);
        return new shipViewHolder(v);
    }

    public class shipViewHolder extends RecyclerView.ViewHolder {
        TextView crop_name, date_ship, crop_destination, crop_price, crop_volume;
        public shipViewHolder(@NonNull View itemView) {
            super(itemView);
            crop_name = (TextView) itemView.findViewById(R.id.crop_name);
            crop_destination = (TextView) itemView.findViewById(R.id.crop_destination);
            date_ship = (TextView) itemView.findViewById(R.id.date_ship);
            crop_price = (TextView) itemView.findViewById(R.id.crop_price);
            crop_volume = (TextView) itemView.findViewById(R.id.crop_volume);
        }
    }
}
