package com.example.weathersearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cardViewAdapter extends RecyclerView.Adapter<cardViewAdapter.cardViewHolder> {

    private ArrayList<cardView> cardViewdata;

    public static class cardViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public TextView textView1;
        public TextView textView2;
        public ImageView humidity;
        public ImageView windspeed;
        public ImageView visibility;
        public ImageView pressure;

        public cardViewHolder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.id1);
            textView = itemView.findViewById(R.id.id2);
            textView1 = itemView.findViewById(R.id.sum);
            textView2 = itemView.findViewById(R.id.city);
            humidity = itemView.findViewById(R.id.humidity);
            windspeed = itemView.findViewById(R.id.windspeed);
            visibility = itemView.findViewById(R.id.visibility);
            pressure = itemView.findViewById(R.id.pressure);

        }
    }

    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        cardViewHolder holder = new cardViewHolder(v);
        return holder;


    }

    public cardViewAdapter(ArrayList<cardView> cardViews){
            this.cardViewdata = cardViews;
    }

    @Override
    public void onBindViewHolder(@NonNull cardViewHolder holder, int position) {
            cardView curr =cardViewdata.get(position);
            holder.imageView.setImageResource(curr.getImg());
            holder.textView.setText(curr.getText());
            holder.textView1.setText(curr.getText1());
            holder.textView2.setText(curr.getText2());
            holder.humidity.setImageResource(curr.getHumidity());
            holder.windspeed.setImageResource(curr.getWindspeed());
            holder.visibility.setImageResource(curr.getVisibility());
            holder.pressure.setImageResource(curr.getPressure());
    }

    @Override
    public int getItemCount() {
        return cardViewdata.size();
    }
}
