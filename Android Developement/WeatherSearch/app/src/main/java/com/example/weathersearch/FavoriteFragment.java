package com.example.weathersearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FavoriteFragment extends Fragment {

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


    public FavoriteFragment(){

    }



    private int setIcon(String icon){
        if(icon.equals("clear-night")) {
            return R.drawable.clear_night;
        }
        else if(icon.equals("clear-day")) {
            return R.drawable.clear_day;
        }
        else if(icon.equals("partly-cloudy-day")) {
            return R.drawable.partly_cloudy_day;
        }
        else if(icon.equals("rain")) {
            return R.drawable.rainy;
        }
        else if(icon.equals("partly-cloudy-night")) {
            return R.drawable.partly_cloudy;
        }
        else if(icon.equals("cloudy")) {
            return R.drawable.cloudy;
        }
        else if(icon.equals("fog")) {
            return R.drawable.fog;
        }
        else if(icon.equals("wind")) {
            return R.drawable.wind;
        }
        else if(icon.equals("snow")) {
            return R.drawable.snow;
        }
        else if(icon.equals("sleet")) {
            return R.drawable.sleet;
        }
        return R.drawable.ic_launcher;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_favorite, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        final Bundle jsondata = activity.getMyData();
        final jsonData mydata = (jsonData) jsondata.getSerializable("seriliazable");
        final dayData day0 = (dayData) jsondata.getSerializable("day0");
        final dayData day1 = (dayData) jsondata.getSerializable("day1");
        final dayData day2 = (dayData) jsondata.getSerializable("day2");
        final dayData day3 = (dayData) jsondata.getSerializable("day3");
        final dayData day4 = (dayData) jsondata.getSerializable("day4");
        final dayData day5 = (dayData) jsondata.getSerializable("day5");
        final dayData day6 = (dayData) jsondata.getSerializable("day6");
        final dayData day7 = (dayData) jsondata.getSerializable("day7");

        imageView = rootView.findViewById(R.id.id1);
        textView = rootView.findViewById(R.id.id2);
        textView1 = rootView.findViewById(R.id.sum);
        textView2 = rootView.findViewById(R.id.city);
        humidity = rootView.findViewById(R.id.humidity);
        windspeed = rootView.findViewById(R.id.windspeed);
        visibility = rootView.findViewById(R.id.visibility);
        pressure = rootView.findViewById(R.id.pressure);
        hu = rootView.findViewById(R.id.hu);
        ws = rootView.findViewById(R.id.ws);
        vi = rootView.findViewById(R.id.vi);
        pr = rootView.findViewById(R.id.pr);
        cardView = rootView.findViewById(R.id.card_view);


        imageView.setImageResource(setIcon(jsondata.getString("icon")));
        textView.setText(jsondata.getString("temperature"));
        textView1.setText(jsondata.getString("summary"));
        textView2.setText(jsondata.getString("city"));


        humidity.setImageResource(R.drawable.humidity);
        windspeed.setImageResource(R.drawable.windspeed);
        visibility.setImageResource(R.drawable.visibility);
        pressure.setImageResource(R.drawable.pressure);
        hu.setText(jsondata.getString("humidity"));
        ws.setText(jsondata.getString("windspeed"));
        vi.setText(jsondata.getString("visibility"));
        pr.setText(jsondata.getString("pressure"));


        textView = rootView.findViewById(R.id.date1);
        textView.setText(day0.dat);
        textView = rootView.findViewById(R.id.date2);
        textView.setText(day1.dat);
        textView = rootView.findViewById(R.id.date3);
        textView.setText(day2.dat);
        textView = rootView.findViewById(R.id.date4);
        textView.setText(day3.dat);
        textView = rootView.findViewById(R.id.date5);
        textView.setText(day4.dat);
        textView = rootView.findViewById(R.id.date6);
        textView.setText(day5.dat);
        textView = rootView.findViewById(R.id.date7);
        textView.setText(day6.dat);
        textView = rootView.findViewById(R.id.date8);
        textView.setText(day7.dat);


        imageView = rootView.findViewById(R.id.img1);
        imageView.setImageResource(day0.icon);
        imageView = rootView.findViewById(R.id.img2);
        imageView.setImageResource(day1.icon);
        imageView = rootView.findViewById(R.id.img3);
        imageView.setImageResource(day2.icon);
        imageView = rootView.findViewById(R.id.img4);
        imageView.setImageResource(day3.icon);
        imageView = rootView.findViewById(R.id.img5);
        imageView.setImageResource(day4.icon);
        imageView = rootView.findViewById(R.id.img6);
        imageView.setImageResource(day5.icon);
        imageView = rootView.findViewById(R.id.img7);
        imageView.setImageResource(day6.icon);
        imageView = rootView.findViewById(R.id.img8);
        imageView.setImageResource(day7.icon);

        textView = rootView.findViewById(R.id.min1);
        textView.setText(day0.minTemp);
        textView = rootView.findViewById(R.id.min2);
        textView.setText(day1.minTemp);
        textView = rootView.findViewById(R.id.min3);
        textView.setText(day2.minTemp);
        textView = rootView.findViewById(R.id.min4);
        textView.setText(day3.minTemp);
        textView = rootView.findViewById(R.id.min5);
        textView.setText(day4.minTemp);
        textView = rootView.findViewById(R.id.min6);
        textView.setText(day5.minTemp);
        textView = rootView.findViewById(R.id.min7);
        textView.setText(day6.minTemp);
        textView = rootView.findViewById(R.id.min8);
        textView.setText(day7.minTemp);


        textView = rootView.findViewById(R.id.max1);
        textView.setText(day0.maxTemp);
        textView = rootView.findViewById(R.id.max2);
        textView.setText(day1.maxTemp);
        textView = rootView.findViewById(R.id.max3);
        textView.setText(day2.maxTemp);
        textView = rootView.findViewById(R.id.max4);
        textView.setText(day3.maxTemp);
        textView = rootView.findViewById(R.id.max5);
        textView.setText(day4.maxTemp);
        textView = rootView.findViewById(R.id.max6);
        textView.setText(day5.maxTemp);
        textView = rootView.findViewById(R.id.max7);
        textView.setText(day6.maxTemp);
        textView = rootView.findViewById(R.id.max8);
        textView.setText(day7.maxTemp);


        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("ClickedCard");
                Intent intent = new Intent(activity,NextActivity.class);
                intent.putExtra("data", mydata);
                startActivity(intent);
            }
        });


        return rootView;
    }

}
