package com.example.weathersearch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class SearchNextActivity extends AppCompatActivity{
    static final int NUMBER_OF_PAGES = 3;

    private jsonData jsondata;
    MyAdapter mAdapter;
    ViewPager mPager;

    public static class MyAdapter extends FragmentPagerAdapter {
        private Context mContext;
        private int numOfTabs;
        public MyAdapter(FragmentManager fm,Context context,int tabs) {
            super(fm);
            mContext = context;
            numOfTabs = tabs;

        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return SearchFirstFragment.newInstance(0);
                case 1:
                    return SearchSecondFragment.newInstance(1);
                case 2:
                    return SearchThirdFragment.newInstance(2);
                default:
                    return null;
            }
        }
    }

    public Bundle getMyData() {
        Bundle hm = new Bundle();
        hm.putString("windspeed",jsondata.getWindSpeed());
        hm.putString("pressure",jsondata.getPressure());
        hm.putString("precipitation",jsondata.getPrecipitation());
        hm.putString("temp",jsondata.getTemperature());
        hm.putString("cloud",jsondata.getCloudCover());
        hm.putString("ozone",jsondata.getOzone());
        hm.putString("humid",jsondata.getHumidity());
        hm.putString("visibility",jsondata.getVisibility());
        hm.putString("icon",jsondata.getIcon());
        hm.putString("weeklysummary",jsondata.getWeeklySummary());
        hm.putString("weeklyicon",jsondata.getWeeklyIcon());
        hm.putString("city",jsondata.getCity());
        return hm;
    }

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchnext);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Intent intent=getIntent();
        jsondata = (jsonData)  intent.getSerializableExtra("data");
        this.setTitle(jsondata.getCity());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mAdapter = new MyAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);

        tabLayout.setupWithViewPager(mPager);


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);
            if(i == 0) {
                tab_label.setText(getResources().getString(R.string.today));
                tab_icon.setImageResource(R.drawable.today);
                tab_label.setTextColor(getResources().getColor(R.color.white));
            }
            else if(i == 1){
                tab_label.setText(getResources().getString(R.string.week));
                tab_icon.setImageResource(R.drawable.weekly);
                tab_label.setTextColor(getResources().getColor(R.color.darkwhite));
            }
            else if(i == 2){
                tab_label.setText(getResources().getString(R.string.photo));
                tab_icon.setImageResource(R.drawable.photos);
                tab_label.setTextColor(getResources().getColor(R.color.darkwhite));
            }

            tabLayout.getTabAt(i).setCustomView(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // System.out.println("Tabselected"+tab.getPosition());
                // mPager.setCurrentItem(tab.getPosition());

                super.onTabSelected(tab);
                View tabView = tab.getCustomView();
                TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.white));
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                View tabView = tab.getCustomView();
                TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.darkwhite));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                System.out.println("tabseelected2");
//                mPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
        String mycity = jsondata.getCity().split(",")[0];
        String tweet = "https://twitter.com/intent/tweet?text=Check%20out%20"+mycity+"%27s%20Weather!%20It%20is%2068.56%C2%B0%20F!%20&hashtags=CSCI571WeatherSearch";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet));
        startActivity(browserIntent);
    }
}
