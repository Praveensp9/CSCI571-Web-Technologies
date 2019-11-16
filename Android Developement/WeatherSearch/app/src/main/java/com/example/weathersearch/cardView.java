package com.example.weathersearch;

public class cardView {
    private int img;
    private String text;
    private String text1;
    private String text2;
    private int humidity;
    private int windspeed;
    private int visibility;
    private int pressure;


    public cardView(int imgR,String text,String text1,String text2,int humidity,int windspeed,int visibility,int pressure){
        this.img = imgR;
        this.text = text;
        this.text2 = text2;
        this.text1 = text1;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.visibility = visibility;
        this.pressure = pressure;
    }

    public int getImg(){
        return img;
    }

    public String getText(){
        return text;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWindspeed() {
        return windspeed;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getPressure() {
        return pressure;
    }


}
