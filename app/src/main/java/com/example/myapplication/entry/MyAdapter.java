package com.example.myapplication.entry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Integer> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        ViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.textView);
        }
    }

    public MyAdapter(List<Integer> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_horizontal_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
