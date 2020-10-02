package com.example.baptcv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baptcv2.Database.ToolsDB;
import com.example.baptcv2.R;

import java.util.List;

public class ToolViewAdapter extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView, textView_description;

    public ToolViewAdapter(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        textView = itemView.findViewById(R.id.textView);
        textView_description = itemView.findViewById(R.id.textView_description);
    }
}
