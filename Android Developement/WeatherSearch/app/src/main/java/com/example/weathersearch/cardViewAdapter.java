package com.example.weathersearch;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cardViewAdapter extends RecyclerView.Adapter<cardViewAdapter.cardViewHolder> {

    private ArrayList<cardView> cardViewdata;
    private OnItemClickListerner listener;

    public void setOnItemClickListener(OnItemClickListerner mylistener){
        listener = mylistener;
    }

    public interface OnItemClickListerner{
        void onItemClick(int position);
    }

    public static class cardViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public TextView textView1;
        public TextView textView2;
        public ImageView humidity;
        public ImageView windspeed;
        public ImageView visibility;
        public ImageView pressure;
        public TextView hu;
        public TextView ws;
        public TextView vi;
        public TextView pr;
        public CardView cardView;

        public cardViewHolder(View itemView, final OnItemClickListerner mylistener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.id1);
            textView = itemView.findViewById(R.id.id2);
            textView1 = itemView.findViewById(R.id.sum);
            textView2 = itemView.findViewById(R.id.city);
            humidity = itemView.findViewById(R.id.humidity);
            windspeed = itemView.findViewById(R.id.windspeed);
            visibility = itemView.findViewById(R.id.visibility);
            pressure = itemView.findViewById(R.id.pressure);
            hu = itemView.findViewById(R.id.hu);
            ws = itemView.findViewById(R.id.ws);
            vi = itemView.findViewById(R.id.vi);
            pr = itemView.findViewById(R.id.pr);
            cardView = itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(mylistener != null){
                        int position = getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION){
                            mylistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public cardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        cardViewHolder holder = new cardViewHolder(v,listener);
        return holder;
    }

    public cardViewAdapter(ArrayList<cardView> cardViews){
            this.cardViewdata = cardViews;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            holder.hu.setText(curr.getHu());
            holder.ws.setText(curr.getWs());
            holder.vi.setText(curr.getVi());
            holder.pr.setText(curr.getPr());
    }

    @Override
    public int getItemCount() {
        return cardViewdata.size();
    }
}
