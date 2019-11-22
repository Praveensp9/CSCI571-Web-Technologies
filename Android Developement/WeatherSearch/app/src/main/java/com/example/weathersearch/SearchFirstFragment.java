package com.example.weathersearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SearchFirstFragment extends Fragment {
    private static final String MY_NUM_KEY = "num";
    private int mNum;

    // You can modify the parameters to pass in whatever you want
    static SearchFirstFragment newInstance(int num) {
        SearchFirstFragment f = new SearchFirstFragment();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt(MY_NUM_KEY);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        SearchNextActivity activity = (SearchNextActivity) getActivity();
        Bundle jsondata = activity.getMyData();
        TextView textView;

        ImageView ws = v.findViewById(R.id.ws);
        ws.setImageResource(R.drawable.windspeed);
        textView = v.findViewById(R.id.wind1);
        textView.setText(jsondata.getString("windspeed"));
        textView = v.findViewById(R.id.wind2);
        textView.setText("Wind Speed");


        ImageView pressure = v.findViewById(R.id.pr);
        pressure.setImageResource(R.drawable.pressure);
        textView = v.findViewById(R.id.pressure1);
        textView.setText(jsondata.getString("pressure"));
        textView = v.findViewById(R.id.pressure2);
        textView.setText("Pressure");

        ImageView pc = v.findViewById(R.id.pc);
        pc.setImageResource(R.drawable.precipitation);
        textView = v.findViewById(R.id.precipitation1);
        textView.setText(jsondata.getString("precipitation"));
        textView = v.findViewById(R.id.precipitation2);
        textView.setText("Precipitation");


        ImageView temperature = v.findViewById(R.id.temperature);
        temperature.setImageResource(R.drawable.temperature);
        textView = v.findViewById(R.id.temp1);
        textView.setText(jsondata.getString("temp"));
        textView = v.findViewById(R.id.temp2);
        textView.setText("Temperature");


        ImageView summary = v.findViewById(R.id.summary);
        summary.setImageResource(setIcon(jsondata.getString("icon")));
        textView = v.findViewById(R.id.summary1);
        textView.setText(jsondata.getString("icon"));
        textView = v.findViewById(R.id.summary2);
        textView.setText(jsondata.getString("icon"));

        ImageView humid = v.findViewById(R.id.humid);
        humid.setImageResource(R.drawable.humidity);
        textView = v.findViewById(R.id.humid1);
        textView.setText(jsondata.getString("humid"));
        textView = v.findViewById(R.id.humid2);
        textView.setText("Humidity");

        ImageView visi = v.findViewById(R.id.visi);
        visi.setImageResource(R.drawable.visibility);
        textView = v.findViewById(R.id.visi1);
        textView.setText(jsondata.getString("visibility"));
        textView = v.findViewById(R.id.visi2);
        textView.setText("Visibility");

        ImageView cloud = v.findViewById(R.id.cloud);
        cloud.setImageResource(R.drawable.cloud);
        textView = v.findViewById(R.id.cloud1);
        textView.setText(jsondata.getString("cloud"));
        textView = v.findViewById(R.id.cloud2);
        textView.setText("Cloud Cover");

        ImageView ozone = v.findViewById(R.id.ozone);
        ozone.setImageResource(R.drawable.ozone);
        textView = v.findViewById(R.id.ozone1);
        textView.setText(jsondata.getString("ozone"));
        textView = v.findViewById(R.id.ozone2);
        textView.setText("Ozone");

        return v;
    }
}