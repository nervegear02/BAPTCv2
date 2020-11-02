package com.example.baptcv2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baptcv2.Database.Sold;
import com.example.baptcv2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class SalesViewHolder extends FirebaseRecyclerAdapter<Sold, SalesViewHolder.salesViewHolder> {

    public SalesViewHolder(@NonNull FirebaseRecyclerOptions<Sold> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull salesViewHolder holder, int position, @NonNull Sold model) {
        holder.sold_to.setText(model.getSold_to());
        holder.crop_name.setText(model.getCrop_name());
        holder.crop_price.setText("₱" + model.getCrop_price());
        holder.date_sales.setText(model.getDate_sales());
        holder.crop_volume.setText(model.getCrop_volume() + "kg");
        String sprice = model.getCrop_price();
        String svolume = model.getCrop_volume();
        int iprice = Integer.parseInt(sprice);
        int ivolume = Integer.parseInt(svolume);
        int itotal = iprice * ivolume;
        String stotal = Integer.toString(itotal);
        holder.total_price.setText("₱" + stotal);

    }

    @NonNull
    @Override
    public salesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_view_layout, parent, false);
        return new salesViewHolder(v);
    }

    public class salesViewHolder extends RecyclerView.ViewHolder {
        TextView sold_to, crop_name, date_sales, crop_price, crop_volume, total_price;
        public salesViewHolder(@NonNull View itemView) {
            super(itemView);
            sold_to = (TextView) itemView.findViewById(R.id.sold_to);
            crop_name = (TextView) itemView.findViewById(R.id.crop_name);
            date_sales = (TextView) itemView.findViewById(R.id.date_sales);
            crop_price = (TextView) itemView.findViewById(R.id.crop_price);
            crop_volume = (TextView) itemView.findViewById(R.id.crop_volume);
            total_price = (TextView) itemView.findViewById(R.id.total_price);
        }
    }
}
