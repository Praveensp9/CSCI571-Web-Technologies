package com.example.weathersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageview;
    private RecyclerView recyclerView;
    private cardViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private jsonData jsondata = new jsonData();
    private dayData day0;
    private dayData day1;
    private dayData day2;
    private dayData day3;
    private dayData day4;
    private dayData day5;
    private dayData day6;
    private dayData day7;

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

    private void setData(){

        ArrayList<cardView> cardViews = new ArrayList<>();
        String icon = jsondata.getIcon();

        cardViews.add((new cardView(setIcon(icon), jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                R.drawable.humidity, R.drawable.windspeed, R.drawable.visibility, R.drawable.pressure,
                jsondata.getHumidity(), jsondata.getWindSpeed(), jsondata.getVisibility(), jsondata.getPressure(),
                day0, day1, day2, day3, day4, day5, day6, day7
        )));


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        layoutManager = new LinearLayoutManager(this);
        adapter = new cardViewAdapter(cardViews);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        CardView cardview = findViewById(R.id.card_view4);
        cardview.setVisibility(View.VISIBLE);
        ProgressBar progressBar = findViewById(R.id.progress1);
        progressBar.setVisibility(View.GONE);

        adapter.setOnItemClickListener(new cardViewAdapter.OnItemClickListerner() {
            @Override
            public void onItemClick(int position) {
                System.out.println("ClickedCard");
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.date1);
        textView.setText(day0.dat);
        textView = findViewById(R.id.date2);
        textView.setText(day1.dat);
        textView = findViewById(R.id.date3);
        textView.setText(day2.dat);
        textView = findViewById(R.id.date4);
        textView.setText(day3.dat);
        textView = findViewById(R.id.date5);
        textView.setText(day4.dat);
        textView = findViewById(R.id.date6);
        textView.setText(day5.dat);
        textView = findViewById(R.id.date7);
        textView.setText(day6.dat);
        textView = findViewById(R.id.date8);
        textView.setText(day7.dat);


        imageview = findViewById(R.id.img1);
        imageview.setImageResource(day0.icon);
        imageview = findViewById(R.id.img2);
        imageview.setImageResource(day1.icon);
        imageview = findViewById(R.id.img3);
        imageview.setImageResource(day2.icon);
        imageview = findViewById(R.id.img4);
        imageview.setImageResource(day3.icon);
        imageview = findViewById(R.id.img5);
        imageview.setImageResource(day4.icon);
        imageview = findViewById(R.id.img6);
        imageview.setImageResource(day5.icon);
        imageview = findViewById(R.id.img7);
        imageview.setImageResource(day6.icon);
        imageview = findViewById(R.id.img8);
        imageview.setImageResource(day7.icon);

        textView = findViewById(R.id.min1);
        textView.setText(day0.minTemp);
        textView = findViewById(R.id.min2);
        textView.setText(day1.minTemp);
        textView = findViewById(R.id.min3);
        textView.setText(day2.minTemp);
        textView = findViewById(R.id.min4);
        textView.setText(day3.minTemp);
        textView = findViewById(R.id.min5);
        textView.setText(day4.minTemp);
        textView = findViewById(R.id.min6);
        textView.setText(day5.minTemp);
        textView = findViewById(R.id.min7);
        textView.setText(day6.minTemp);
        textView = findViewById(R.id.min8);
        textView.setText(day7.minTemp);


        textView = findViewById(R.id.max1);
        textView.setText(day0.maxTemp);
        textView = findViewById(R.id.max2);
        textView.setText(day1.maxTemp);
        textView = findViewById(R.id.max3);
        textView.setText(day2.maxTemp);
        textView = findViewById(R.id.max4);
        textView.setText(day3.maxTemp);
        textView = findViewById(R.id.max5);
        textView.setText(day4.maxTemp);
        textView = findViewById(R.id.max6);
        textView.setText(day5.maxTemp);
        textView = findViewById(R.id.max7);
        textView.setText(day6.maxTemp);
        textView = findViewById(R.id.max8);
        textView.setText(day7.maxTemp);

    }


    private void getDayData(JSONArray data) throws JSONException {
        day0 = new dayData( data.getJSONObject(0).getString("time"),
                            setIcon(data.getJSONObject(0).getString("icon")),
                            data.getJSONObject(0).getString("temperatureLow"),
                            data.getJSONObject(0).getString("temperatureHigh"));
        day1 = new dayData( data.getJSONObject(1).getString("time"),
                            setIcon(data.getJSONObject(1).getString("icon")),
                            data.getJSONObject(1).getString("temperatureLow"),
                            data.getJSONObject(1).getString("temperatureHigh"));
        day2 = new dayData( data.getJSONObject(2).getString("time"),
                            setIcon(data.getJSONObject(2).getString("icon")),
                            data.getJSONObject(2).getString("temperatureLow"),
                            data.getJSONObject(2).getString("temperatureHigh"));
        day3 = new dayData( data.getJSONObject(3).getString("time"),
                            setIcon(data.getJSONObject(3).getString("icon")),
                            data.getJSONObject(3).getString("temperatureLow"),
                            data.getJSONObject(3).getString("temperatureHigh"));
        day4 = new dayData( data.getJSONObject(4).getString("time"),
                            setIcon(data.getJSONObject(4).getString("icon")),
                            data.getJSONObject(4).getString("temperatureLow"),
                            data.getJSONObject(4).getString("temperatureHigh"));
        day5 = new dayData( data.getJSONObject(5).getString("time"),
                            setIcon(data.getJSONObject(5).getString("icon")),
                            data.getJSONObject(5).getString("temperatureLow"),
                            data.getJSONObject(5).getString("temperatureHigh"));
        day6 = new dayData( data.getJSONObject(6).getString("time"),
                            setIcon(data.getJSONObject(6).getString("icon")),
                            data.getJSONObject(6).getString("temperatureLow"),
                            data.getJSONObject(6).getString("temperatureHigh"));
        day7 = new dayData( data.getJSONObject(7).getString("time"),
                            setIcon(data.getJSONObject(7).getString("icon")),
                            data.getJSONObject(7).getString("temperatureLow"),
                            data.getJSONObject(7).getString("temperatureHigh"));
    }
    private void currentLocation(final String lat, final String lon){

        RequestQueue queue = Volley.newRequestQueue(this);
        // http://ec2-3-135-53-103.us-east-2.compute.amazonaws.com/
        String url ="http://ec2-3-18-230-248.us-east-2.compute.amazonaws.com:8081/current";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Respans : " + response);
                        try {
                            JSONObject json = new JSONObject(response);
                            System.out.println(json.getString("latitude"));
                            jsondata.setTemperature(json.getJSONObject("currently").getString("temperature")+"°F");
                            jsondata.setSummary(json.getJSONObject("currently").getString("summary"));
                            jsondata.setIcon(json.getJSONObject("currently").getString("icon"));
                            jsondata.setCity("Los Angeles,CA,USA");
                            jsondata.setHumidity(json.getJSONObject("currently").getString("humidity"));
                            jsondata.setWindSpeed(json.getJSONObject("currently").getString("windSpeed"));
                            jsondata.setVisibility(json.getJSONObject("currently").getString("visibility"));
                            jsondata.setPressure(json.getJSONObject("currently").getString("pressure"));
                            getDayData(json.getJSONObject("daily").getJSONArray("data"));
                            setData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errarr: " +error);

            }

        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("lat", lat);
                params.put("lon", lon);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue currentLocationQueue = Volley.newRequestQueue(this);
        String location ="http://ip-api.com/json";

        StringRequest locationRequest = new StringRequest(Request.Method.GET, location,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Respanseee : " + response);
                        try {
                            JSONObject loc = new JSONObject(response);
                            String lat = loc.getString("lat");
                            String lon = loc.getString("lon");
                            currentLocation(lat,lon);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("errarr: " +error);
            }

        });

        currentLocationQueue.add(locationRequest);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardview = findViewById(R.id.card_view4);
        cardview.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_toolbar2, menu);
        return true;
    }

    public void onComposeAction(MenuItem mi) {
        // handle click here
        System.out.println("Search button is clicked");
    }
}