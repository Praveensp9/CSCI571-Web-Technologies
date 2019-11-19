package com.example.weathersearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class NextActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        this.setTitle("Los Angeles, CA, USA");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_toolbar, menu);
        return true;
    }

    public void onComposeAction(MenuItem mi) {
        // handle click here
        System.out.println("Twitter button is clicked");
        String tweet = "https://twitter.com/intent/tweet?text=Check%20out%20Seattle%27s%20Weather!%20It%20is%2068.56%C2%B0%20F!%20&hashtags=CSCI571WeatherSearch";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet));
        startActivity(browserIntent);
    }
}
