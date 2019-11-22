package com.example.weathersearch;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AutoCompleteApiCall {

    private static AutoCompleteApiCall mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public AutoCompleteApiCall(Context ctx) {
        mCtx = ctx;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized AutoCompleteApiCall getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AutoCompleteApiCall(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public static void make(Context ctx, final String query, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url ="http://ec2-3-135-53-103.us-east-2.compute.amazonaws.com:8082/autocomplete";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,listener,errorListener){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("val",   query);
                return params;
            }
        };

        AutoCompleteApiCall.getInstance(ctx).addToRequestQueue(stringRequest);
    }
}
