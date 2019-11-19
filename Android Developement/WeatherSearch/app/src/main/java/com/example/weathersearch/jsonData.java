package com.example.weathersearch;

import java.text.DecimalFormat;

public class jsonData {
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    String temperature;

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

    String humidity;
    String windSpeed;
    String pressure;
    String visibility;
    String summary;
    String City;
    String icon;

}
