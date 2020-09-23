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

import org.w3c.dom.Text;

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
        CropsViewHolder cropsViewHolder = (CropsViewHolder)holder;
        Crops crops = cropsList.get(position);
        cropsViewHolder.setName(crops.getCrop_name());
        cropsViewHolder.setPrice("₱" + crops.getCrop_price());
        cropsViewHolder.setVolume(crops.getCrop_volume() + "kg");
        cropsViewHolder.setPlanted(crops.getDate_planted());
        cropsViewHolder.setHarvested(crops.getDate_harvested());

        boolean isExpandable = cropsList.get(position).isExpandable();
        cropsViewHolder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return cropsList.size();
    }

    class CropsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public CropsViewHolder(@NonNull View itemView) {
            super(itemView);

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

        public void setName(String name){
            TextView crop_name = (TextView) itemView.findViewById(R.id.crop_name);
            crop_name.setText(name);
        }

        public void setPrice(String price){
            TextView crop_price = (TextView) itemView.findViewById(R.id.crop_price);
            crop_price.setText(price);
        }

        public void setVolume(String volume){
            TextView crop_volume = (TextView) itemView.findViewById(R.id.crop_volume);
            crop_volume.setText(volume);
        }

        public void setPlanted(String planted){
            TextView crop_planted = (TextView) itemView.findViewById(R.id.date_planted);
            crop_planted.setText(planted);
        }

        public void setHarvested(String harvested){
            TextView crop_harvested = (TextView) itemView.findViewById(R.id.date_harvested);
            crop_harvested.setText(harvested);
        }
    }
}
