package com.example.weathersearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;


import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private jsonData jsondata = new jsonData();
    private dayData day0;
    private dayData day1;
    private dayData day2;
    private dayData day3;
    private dayData day4;
    private dayData day5;
    private dayData day6;
    private dayData day7;
    private AutoCompleteAdapter autoCompleteAdapter;
    private Handler handler;

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

    public Bundle getMyData() {
        Bundle hm = new Bundle();
        hm.putString("icon",jsondata.getIcon());
        hm.putString("temperature",jsondata.getTemperature());
        hm.putString("summary",jsondata.getSummary());
        hm.putString("city",jsondata.getCity());
        hm.putString("humidity",jsondata.getHumidity());
        hm.putString("visibility",jsondata.getVisibility());
        hm.putString("windspeed",jsondata.getWindSpeed());
        hm.putString("pressure",jsondata.getPressure());
        hm.putSerializable("seriliazable",jsondata);
        hm.putSerializable("day0",day0);
        hm.putSerializable("day1",day1);
        hm.putSerializable("day2",day2);
        hm.putSerializable("day3",day3);
        hm.putSerializable("day4",day4);
        hm.putSerializable("day5",day5);
        hm.putSerializable("day6",day6);
        hm.putSerializable("day7",day7);

        return hm;
    }
    private static boolean flag = true;
    private void setData(){

        ViewPager viewPager = (ViewPager) findViewById(R.id.favoriteViewpager);
        FavoriteAdapter adapter = new FavoriteAdapter(getSupportFragmentManager());
        System.out.println("adapter: "+adapter);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(10);

        adapter.AddFragment(new FavoriteFragment(),jsondata.getCity());
        adapter.notifyDataSetChanged();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.favoritetabs);
        tabLayout.setupWithViewPager(viewPager);


        SharedPreferences mycities = getSharedPreferences("mycities",MODE_PRIVATE);
        HashSet<String> cities = (HashSet<String>) mycities.getStringSet("favoriteCities",new HashSet<String>());


        if(cities.size() != 0){
            for(String city:cities) {
                System.out.println("Share "+ city);
                adapter.AddFragment(new FavoriteFragment2(), city);
            }
        }
        adapter.notifyDataSetChanged();
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
        String url ="http://ec2-3-135-53-103.us-east-2.compute.amazonaws.com:8082/current";

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
                            jsondata.setCity("New York,NY,USA");
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
                params.put("lat", lat);
                params.put("lon", lon);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void makeApiCall(String text) {

        AutoCompleteApiCall.make(this, text, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                List<String> stringList = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(response);

                    System.out.println("JSONOBJECT: "+json.getJSONArray("predictions").getJSONObject(0).getString("description"));
                    System.out.println("JSONOBJECT: "+json.getJSONArray("predictions").getJSONObject(1).getString("description"));
                    System.out.println("JSONOBJECT: "+json.getJSONArray("predictions").getJSONObject(2).getString("description"));
                    System.out.println("JSONOBJECT: "+json.getJSONArray("predictions").getJSONObject(3).getString("description"));
                    System.out.println("JSONOBJECT: "+json.getJSONArray("predictions").getJSONObject(4).getString("description"));

                    JSONArray array = json.getJSONArray("predictions");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject row = array.getJSONObject(i);
                        stringList.add(row.getString("description"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //IMPORTANT: set data here and notify
                autoCompleteAdapter.setData(stringList);
                autoCompleteAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
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
    }

    @Override
    protected void onPostResume() {

        super.onPostResume();
        System.out.println("SharedONResume");
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
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.customer_toolbar2, menu);
//        return true;
//    }


//    public void onComposeAction(MenuItem mi) {
//        // handle click here
//        System.out.println("Searchhh button is clicked");
//        onSearchRequested();
//    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_toolbar2, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.mysearch).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);

        if (searchPlate!=null) {
            searchPlate.setBackgroundColor(Color.DKGRAY);

            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
            if (searchText!=null) {
                searchText.setTextColor(Color.WHITE);
                searchText.setHintTextColor(Color.WHITE);
            }
        }

//        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
//        searchAutoComplete.setBackgroundColor(Color.DKGRAY);
//        searchAutoComplete.setTextColor(Color.WHITE);
//        searchAutoComplete.setDropDownBackgroundResource(R.color.white);
//
//        String dataArr[] = {"Seattle" , "Bangalore" , "Mumbai", "Hongkong", "Delhi"};
//        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
//        searchAutoComplete.setAdapter(newsAdapter);
//
//        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
//                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
//                searchAutoComplete.setText("" + queryString);
//
//            }
//        });

        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setBackgroundColor(Color.DKGRAY);
        searchAutoComplete.setTextColor(Color.WHITE);
        searchAutoComplete.setDropDownBackgroundResource(R.color.white);

        autoCompleteAdapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        searchAutoComplete.setAdapter(autoCompleteAdapter);

        searchAutoComplete.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String queryString=(String)parent.getItemAtPosition(position);
                        searchAutoComplete.setText("" + queryString);
                    }
                });


        searchAutoComplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                handler.removeMessages(100);
                handler.sendEmptyMessageDelayed(100,300);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 100) {
                    if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
                        makeApiCall(searchAutoComplete.getText().toString());
                    }
                }
                return false;
            }
        });

            // Below event is triggered when submit search query.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Search keyword is " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }
}