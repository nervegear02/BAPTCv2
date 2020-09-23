package com.example.baptcv2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baptcv2.Database.Crops;
import com.example.baptcv2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.CropsViewHolder> {

    List<Crops> cropsList;

    public CropsAdapter(List<Crops> cropsList) {
        this.cropsList = cropsList;
    }

    @NonNull
    @Override
    public CropsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_view_layout, parent, false);
        return new CropsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropsViewHolder holder, int position) {
        Crops crops = cropsList.get(position);
        holder.crop_name.setText(crops.getCrop_name());
        holder.crop_price.setText(crops.getCrop_price());
        holder.crop_volume.setText(crops.getCrop_volume());
        holder.crop_planted.setText(crops.getDate_planted());
        holder.crop_harvested.setText(crops.getDate_harvested());

        boolean isExpandable = cropsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return cropsList.size();
    }

    class CropsViewHolder extends RecyclerView.ViewHolder {

        TextView crop_name, crop_price, crop_volume, crop_planted, crop_harvested;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public CropsViewHolder(@NonNull View itemView) {
            super(itemView);

            crop_name = itemView.findViewById(R.id.crop_name);
            crop_price = itemView.findViewById(R.id.crop_price);
            crop_volume = itemView.findViewById(R.id.crop_volume);
            crop_planted = itemView.findViewById(R.id.date_planted);
            crop_harvested = itemView.findViewById(R.id.date_harvested);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Crops crops = cropsList.get(getAdapterPosition());
                    crops.setExpandable(!crops.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
