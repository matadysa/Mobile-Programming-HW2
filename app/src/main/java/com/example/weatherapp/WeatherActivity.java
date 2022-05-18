package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherActivity extends AppCompatActivity {

    TextView cityNameText;

    ArrayList<String> dateValues = new ArrayList<>();
    ArrayList<Double> tempValues = new ArrayList<>();
    ArrayList<Double> feelsLikeValues = new ArrayList<>();
    ArrayList<Double> windValues = new ArrayList<>();
    ArrayList<String> overallValues = new ArrayList<>();
    ArrayList<HashMap<String, Object>> fullDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityNameText = findViewById(R.id.city_name_value);

        Bundle extras = getIntent().getExtras();
        String cityName = "";
        double longitude = 0, latitude = 0;
        if (extras != null) {
            fullDataList = (ArrayList<HashMap<String, Object>>) extras.get("data");
            cityName = extras.getString("cityName");
            longitude = extras.getDouble("lon");
            latitude = extras.getDouble("lat");
        }

        //city_name_value
        if (cityName.equals("")) {
            cityName = "Longitude=" + longitude + " , Latitude=" + latitude;
        }
        cityNameText.setText(cityName);

        for (HashMap<String, Object> map : fullDataList) {
            dateValues.add((String) map.get("date"));
            tempValues.add((Double) map.get("actualTemp"));
            feelsLikeValues.add((Double) map.get("feelsLikeTemp"));
            windValues.add((Double) map.get("windSpeed"));
            overallValues.add((String) map.get("overallCondition"));
        }
        dateValues.set(0, "Today");
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,
                dateValues,
                tempValues,
                feelsLikeValues,
                windValues,
                overallValues,
                fullDataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}