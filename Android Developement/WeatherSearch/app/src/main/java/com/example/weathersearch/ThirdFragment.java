package com.example.weathersearch;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThirdFragment extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private int mNum;
    ArrayList<PhotoLoader> images = new ArrayList<>();
    private final ArrayList<String> urls = new ArrayList<>();

    // You can modify the parameters to pass in whatever you want
    static ThirdFragment newInstance(int num) {
        ThirdFragment f = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_third, container, false);
        NextActivity activity = (NextActivity) getActivity();
        Bundle jsondata = activity.getMyData();
        final String city = jsondata.getString("city");

        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.photostab);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);

        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url ="http://ec2-3-135-53-103.us-east-2.compute.amazonaws.com:8082/logo";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject json = new JSONObject(response);
                            for(int i=0;i<8;i++){
                                PhotoLoader photos = new PhotoLoader();
                                photos.setUrl(json.getJSONArray("items").getJSONObject(i).getString("link"));
                                images.add(photos);
                            }
                            PhotoAdapter adapter = new PhotoAdapter(v.getContext(),images);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("city photos error: " +error);

            }

        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("city", city);
                return params;
            }
        };
        queue.add(stringRequest);
        return v;
    }
}