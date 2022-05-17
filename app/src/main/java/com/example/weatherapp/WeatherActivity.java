package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherActivity extends AppCompatActivity {

    //    public static ArrayList<WeatherData> weatherData;
    TextView cityNameText;

    ArrayList<String> dateValues = new ArrayList<>();
    ArrayList<Double> tempValues = new ArrayList<>();
    ArrayList<Double> feelsLikeValues = new ArrayList<>();
    ArrayList<Double> windValues = new ArrayList<>();
    ArrayList<String> overallValues = new ArrayList<>();
    ArrayList<HashMap<String, Object>> fullData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityNameText = findViewById(R.id.city_name_value);

        Bundle extras = getIntent().getExtras();
        String cityName = "";
        double longitude = 0, latitude = 0;
        if(extras != null) {
            fullData = (ArrayList<HashMap<String, Object>>) extras.get("data");
            cityName = extras.getString("cityName");
            longitude = extras.getDouble("lon");
            latitude = extras.getDouble("lat");
        }

        //city_name_value
        if (cityName.equals("")) {
            cityName = "Longitude=" + longitude + " , Latitude=" + latitude;
        }
        cityNameText.setText(cityName);

        for (HashMap<String, Object> map : fullData) {
            dateValues.add((String) map.get("date"));
            tempValues.add((Double) map.get("actualTemp"));
            feelsLikeValues.add((Double) map.get("feelsLikeTemp"));
            windValues.add((Double) map.get("windSpeed"));
            overallValues.add((String) map.get("overallCondition"));
        }
        initRecyclerView();
//        Intent intent = getIntent();
//
//        //get input from intent TODO
//
//        // String data = intent.getStringExtra(StartActivity.data)
//
//        //convert data to a array of weather data
//        ArrayList<WeatherData> weatherData = new ArrayList<>();
//
//        weatherData.add(new WeatherData("2.5", "34", "33.4", "cold"));
//        weatherData.add(new WeatherData("3.5", "44", "33.5", "sunny"));
//        weatherData.add(new WeatherData("4.5", "54", "33.6", "rainy"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//        weatherData.add(new WeatherData("5.5", "64", "33.7", "cold"));
//
//
//        //
//
//
//        weatherAdapter adapter = new weatherAdapter(this, weatherData);
//
//        weatherDataRecyclerView.setAdapter(adapter);
//        weatherDataRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,
                dateValues,
                tempValues,
                feelsLikeValues,
                windValues,
                overallValues,
                fullData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}