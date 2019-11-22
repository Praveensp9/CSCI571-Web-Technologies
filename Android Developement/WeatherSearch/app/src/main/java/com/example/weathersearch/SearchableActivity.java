package com.example.weathersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SearchableActivity extends AppCompatActivity {
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
        String icon = jsondata.getIcon();
        imageView = findViewById(R.id.id1);
        textView = findViewById(R.id.id2);
        textView1 = findViewById(R.id.sum);
        textView2 = findViewById(R.id.city);
        humidity = findViewById(R.id.humidity);
        windspeed = findViewById(R.id.windspeed);
        visibility = findViewById(R.id.visibility);
        pressure = findViewById(R.id.pressure);
        hu = findViewById(R.id.hu);
        ws = findViewById(R.id.ws);
        vi = findViewById(R.id.vi);
        pr = findViewById(R.id.pr);
        cardView = findViewById(R.id.card_view);


        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("ClickedCard");
                Intent intent = new Intent(SearchableActivity.this,SearchNextActivity.class);
                intent.putExtra("data", jsondata);
                startActivity(intent);
            }
        });


        imageView.setImageResource(setIcon(icon));
        textView.setText(jsondata.getTemperature());
        textView1.setText(jsondata.getSummary());
        textView2.setText(jsondata.getCity());
        humidity.setImageResource(R.drawable.humidity);
        windspeed.setImageResource(R.drawable.windspeed);
        visibility.setImageResource(R.drawable.visibility);
        pressure.setImageResource(R.drawable.pressure);
        hu.setText(jsondata.getHumidity());
        ws.setText(jsondata.getWindSpeed());
        vi.setText(jsondata.getVisibility());
        pr.setText(jsondata.getPressure());


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
        ProgressBar progressBar = findViewById(R.id.progress1);
        progressBar.setVisibility(View.GONE);
        CardView cardView4 = findViewById(R.id.card_view4);
        cardView4.setVisibility(View.VISIBLE);
        CardView cardView = findViewById(R.id.card_view);
        cardView.setVisibility(View.VISIBLE);
        CardView cardView3 = findViewById(R.id.card_view3);
        cardView3.setVisibility(View.VISIBLE);

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

    private void location(final String city){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://ec2-3-135-53-103.us-east-2.compute.amazonaws.com:8082/ping";

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
                            jsondata.setHumidity(json.getJSONObject("currently").getString("humidity"));
                            jsondata.setWindSpeed(json.getJSONObject("currently").getString("windSpeed"));
                            jsondata.setVisibility(json.getJSONObject("currently").getString("visibility"));
                            jsondata.setPressure(json.getJSONObject("currently").getString("pressure"));
                            jsondata.setPrecipitation(json.getJSONObject("currently").getString("precipIntensity"));
                            jsondata.setCloudCover(json.getJSONObject("currently").getString("cloudCover"));
                            jsondata.setOzone(json.getJSONObject("currently").getString("ozone"));
                            jsondata.setWeeklySummary(json.getJSONObject("daily").getString("summary"));
                            jsondata.setWeeklyIcon(json.getJSONObject("daily").getString("icon"));
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
                params.put("City", city);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void doMySearch(String city){
        this.setTitle(city);
        jsondata.setCity(city);
        location(city);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    @Override
    protected void onPostResume() {

        SharedPreferences mycities1 = getSharedPreferences("mycities",MODE_PRIVATE);
        SharedPreferences.Editor  edit1 = mycities1.edit();
        HashSet<String> cities = (HashSet<String>) mycities1.getStringSet("favoriteCities",new HashSet<String>());
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab);

        if(cities.contains(mainCity)) {
            fab1.setImageResource(R.drawable.favorite_minus);
        }
        else {
            fab1.setImageResource(R.drawable.favorite_plus);
        }
        super.onPostResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private static String mainCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ProgressBar progressBar = findViewById(R.id.progress1);
        progressBar.setVisibility(View.VISIBLE);
        CardView cardView4 = findViewById(R.id.card_view4);
        cardView4.setVisibility(View.GONE);
        CardView cardView = findViewById(R.id.card_view);
        cardView.setVisibility(View.GONE);
        CardView cardView3 = findViewById(R.id.card_view3);
        cardView3.setVisibility(View.GONE);

        SharedPreferences mycities1 = getSharedPreferences("mycities",MODE_PRIVATE);
        SharedPreferences.Editor  edit1 = mycities1.edit();
        HashSet<String> cities = (HashSet<String>) mycities1.getStringSet("favoriteCities",new HashSet<String>());
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab);

        if(cities.contains(mainCity)) {
            fab1.setImageResource(R.drawable.favorite_minus);
        }
        else {
            fab1.setImageResource(R.drawable.favorite_plus);
        }

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String city = intent.getStringExtra(SearchManager.QUERY);
            mainCity = city;
            doMySearch(city);
        }
        /* else{
            doMySearch(mainCity);
        } */

        // Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mycities = getSharedPreferences("mycities",MODE_PRIVATE);
                SharedPreferences.Editor  edit = mycities.edit();
                HashSet<String> cities = (HashSet<String>) mycities.getStringSet("favoriteCities",new HashSet<String>());
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

                if(!cities.contains(mainCity)) {
                    Toast.makeText(SearchableActivity.this, mainCity + " was added to favorites",
                            Toast.LENGTH_LONG).show();

                    fab.setImageResource(R.drawable.favorite_minus);
                    cities.add(mainCity);
                    edit.clear();
                    edit.putStringSet("favoriteCities",cities);
                    edit.commit();
                }
                else {
                    Toast.makeText(SearchableActivity.this, mainCity + " was removed from favorites",
                            Toast.LENGTH_LONG).show();
                    fab.setImageResource(R.drawable.favorite_plus);
                    if(cities.contains(mainCity)) {
                        cities.remove(mainCity);
                    }
                    edit.clear();
                    edit.putStringSet("favoriteCities",cities);
                    edit.commit();
                }
            }
        });
    }
}