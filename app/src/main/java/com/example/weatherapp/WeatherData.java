package com.example.weatherapp;

public class WeatherData {
    private String wind;
    private String temp;
    private String feelsLike;
    private String overall;

    public WeatherData(String wind, String temp, String feelsLike, String overall) {
        this.wind = wind;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.overall = overall;
    }


    public String getWind() {
        return wind;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getOverall() {
        return overall;
    }


    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }
}
