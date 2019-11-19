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
    private String hu;
    private String ws;
    private String vi;
    private String pr;
    private dayData day0;
    private dayData day1;
    private dayData day2;
    private dayData day3;
    private dayData day4;
    private dayData day5;
    private dayData day6;
    private dayData day7;



    public cardView(int imgR,String text,String text1,String text2,int humidity,int windspeed,int visibility,int pressure,String hu,String ws,String vi,String pr,
                    dayData day0,dayData day1,dayData day2,dayData day3,dayData day4,dayData day5,dayData day6,dayData day7){
        this.img = imgR;
        this.text = text;
        this.text2 = text2;
        this.text1 = text1;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.visibility = visibility;
        this.pressure = pressure;
        this.hu = hu;
        this.ws = ws;
        this.vi = vi;
        this.pr = pr;
        this.day0 = day0;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
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
    public String getHu() {
        return hu;
    }

    public String getWs() {
        return ws;
    }

    public String getVi() {
        return vi;
    }

    public String getPr() {
        return pr;
    }

    public dayData getDay0() {
        return day0;
    }

    public dayData getDay1() {
        return day1;
    }

    public dayData getDay2() {
        return day2;
    }

    public dayData getDay3() {
        return day3;
    }

    public dayData getDay4() {
        return day4;
    }

    public dayData getDay5() {
        return day5;
    }

    public dayData getDay6() {
        return day6;
    }

    public dayData getDay7() {
        return day7;
    }

}
