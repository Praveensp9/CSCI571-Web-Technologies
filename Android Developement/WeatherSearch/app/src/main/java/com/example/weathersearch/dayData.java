package com.example.weathersearch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dayData{
    String dat;
    int icon;
    String minTemp;
    String maxTemp;

    public dayData(String dat,int icon,String minTemp,String maxTemp){
        long seconds = Long.parseLong(dat);
        Date date = new Date(seconds * 1000);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.dat = format.format(date);
        this.icon = icon;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}