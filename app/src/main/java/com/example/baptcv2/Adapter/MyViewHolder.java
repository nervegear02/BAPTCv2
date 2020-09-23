package com.example.baptcv2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baptcv2.Database.PriceList;
import com.example.baptcv2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MyViewHolder extends FirebaseRecyclerAdapter <PriceList, MyViewHolder.ViewHolder> {

    public MyViewHolder(@NonNull FirebaseRecyclerOptions<PriceList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PriceList model) {
        holder.name_list.setText(model.getName());
        holder.price_list.setText("₱" + model.getPrice());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_view_layout, parent, false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_list, price_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_list = (TextView) itemView.findViewById(R.id.name_list);
            price_list = (TextView) itemView.findViewById(R.id.price_list);
        }
    }
}
