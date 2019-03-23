package com.wzh.study.history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzh.study.R;

import java.util.List;

class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemHolder> {

    private List<Sensor> mItems;

    RecycleViewAdapter(List<Sensor> items) {
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.temperature.setText(mItems.get(position).getTemperature());
        holder.heartRate.setText(mItems.get(position).getHeartRate());
        holder.liquidLevel.setText(mItems.get(position).getLiquidLevel());
        holder.collectDate.setText(mItems.get(position).getCollectDate());
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    class ItemHolder extends RecyclerView.ViewHolder {


        TextView temperature, heartRate, liquidLevel, collectDate;

        ItemHolder(View item) {
            super(item);

            temperature = item.findViewById(R.id.temperature);
            heartRate = item.findViewById(R.id.heartRate);
            liquidLevel = item.findViewById(R.id.liquidLevel);
            collectDate = item.findViewById(R.id.collectDate);
        }
    }
}