package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public static ArrayList<WeatherData> weatherData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        RecyclerView weatherDataRecyclerView = findViewById(R.id.recycler_view);
        Intent intent = getIntent();

        //get input from intent TODO

        // String data = intent.getStringExtra(StartActivity.data)

        //convert data to a array of weather data
        ArrayList<WeatherData> weatherData = new ArrayList<>();

        weatherData.add(new WeatherData("2.5", "34", "33.4", "cold"));
        weatherData.add(new WeatherData("3.5", "44", "33.5", "sunny"));
        weatherData.add(new WeatherData("4.5", "54", "33.6", "rainy"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));


        //


        weatherAdapter adapter = new weatherAdapter(this, weatherData);

        weatherDataRecyclerView.setAdapter(adapter);
        weatherDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}