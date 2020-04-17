package com.example.myapplication.entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.MyViewHolder> {
    private Context context;
    private List<RecieveData.Image> imageList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTime;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.img);
            this.textViewTime = itemView.findViewById(R.id.img_time);
        }
    }

    public ImageItemAdapter(Context context, List<RecieveData.Image> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_img_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewTime.setText(this.imageList.get(position).getTime());
        Glide.with(this.context)
                .load(this.imageList.get(position).getImg())
                .placeholder(R.mipmap.harley)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }

    public void updateList(List<RecieveData.Image> list) {
        this.imageList = list;
    }

}
