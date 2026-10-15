package com.fnu.androidlab3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 实验5：RecyclerView 的 Adapter + ViewHolder。
 * 单击 item -> Toast 显示名称；再由 Activity 负责新增一条数据并刷新。
 */
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.VH> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Animal> data;
    private final OnItemClickListener listener;

    public AnimalAdapter(List<Animal> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivIcon;

        VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Animal a = data.get(position);
        holder.tvName.setText(a.name);
        holder.ivIcon.setImageResource(a.iconRes);

        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && listener != null) {
                listener.onItemClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
