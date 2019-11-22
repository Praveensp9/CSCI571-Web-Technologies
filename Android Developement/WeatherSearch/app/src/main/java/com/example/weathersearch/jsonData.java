package com.example.weathersearch;

import java.io.Serializable;
import java.text.DecimalFormat;

public class jsonData implements Serializable {

    public jsonData() {
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        double  h = Double.parseDouble(humidity);
        h = Math.round(h*100);
        int val = (int)h;
        humidity = (val)+"%";
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        double  w = Double.parseDouble(windSpeed);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        w = Double.valueOf(twoDForm.format(w));
        windSpeed = (w) + " mph";
        this.windSpeed = windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        double  p = Double.parseDouble(pressure);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        p = Double.valueOf(twoDForm.format(p));
        pressure = (p) + " mb";
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        double  v = Double.parseDouble(visibility);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        v = Double.valueOf(twoDForm.format(v));
        visibility = (v) + " km";
        this.visibility = visibility;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getCloudCover() {
        return CloudCover;
    }

    public void setCloudCover(String cloudCover) {
        CloudCover = cloudCover;
    }

    public String getOzone() {
        return Ozone;
    }

    public void setOzone(String ozone) {
        Ozone = ozone;
    }

    public String getWeeklySummary() {
        return weeklySummary;
    }

    public void setWeeklySummary(String weeklySummary) {
        this.weeklySummary = weeklySummary;
    }

    public String getWeeklyIcon() {
        return weeklyIcon;
    }

    public void setWeeklyIcon(String weeklyIcon) {
        this.weeklyIcon = weeklyIcon;
    }


    String temperature;
    String precipitation;
    String CloudCover;
    String Ozone;
    String humidity;
    String windSpeed;
    String pressure;
    String visibility;
    String summary;
    String City;
    String icon;
    String weeklySummary;
    String weeklyIcon;


}
