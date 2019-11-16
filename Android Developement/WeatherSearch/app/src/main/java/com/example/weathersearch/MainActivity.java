package com.example.weathersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public  String temperature;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private jsonData jsondata = new jsonData();

    private void setData(){
        System.out.println(("Temp3: "+jsondata.getTemperature()));
        ArrayList<cardView> cardViews = new ArrayList<>();
        String icon = jsondata.getIcon();
        System.out.println("iconn: " + icon);
        if(icon.equals("clear-night")) {
            cardViews.add((new cardView(R.drawable.clear_night, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("clear-day")) {
            cardViews.add((new cardView(R.drawable.clear_day, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("partly-cloudy-day")) {
            cardViews.add((new cardView(R.drawable.partly_cloudy_day, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("rain")) {
            cardViews.add((new cardView(R.drawable.rainy, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("partly-cloudy-night")) {
            cardViews.add((new cardView(R.drawable.partly_cloudy, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("cloudy")) {
            cardViews.add((new cardView(R.drawable.cloudy, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("fog")) {
            cardViews.add((new cardView(R.drawable.fog, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("wind")) {
            cardViews.add((new cardView(R.drawable.wind, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("snow")) {
            cardViews.add((new cardView(R.drawable.snow, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }
        else if(icon.equals("sleet")) {
            cardViews.add((new cardView(R.drawable.sleet, jsondata.getTemperature(), jsondata.getSummary(), jsondata.getCity(),
                    R.drawable.humidity,R.drawable.windspeed,R.drawable.visibility,R.drawable.pressure
            )));
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new cardViewAdapter(cardViews);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://ec2-3-18-230-248.us-east-2.compute.amazonaws.com:8081/weathersearch?street=1210&city=Los%20Angeles&state=California";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Respanseee : " + response);
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
        });
        queue.add(stringRequest);


        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}