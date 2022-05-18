package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.HashMap;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView cityNameTextView = findViewById(R.id.city_or_location_detail_text);

        TextView dateTextView = findViewById(R.id.detail_dte_textView);

        TextView actualTempTextView = findViewById(R.id.detail_actual_temp_textView);
        TextView MaxTempTextView = findViewById(R.id.detail_max_temp_textView);
        TextView MinTempTextView = findViewById(R.id.detail_min_temp_textView);
        TextView FeelsLikeTempTextView = findViewById(R.id.detail_feelsLike_temp_textView);

        TextView humidityTextView = findViewById(R.id.detail_humidity_textView);

        TextView windSpeedTextView = findViewById(R.id.detail_wind_speed_textView);

        ImageView overallImageView = findViewById(R.id.details_imageView);
        TextView overallTextView = findViewById(R.id.detail_overall);

        TextView cloudinessTextView = findViewById(R.id.detail_cloudiness_textView);


        Intent intent = getIntent();
        HashMap<String, Object> data = (HashMap<String, Object>) intent.getSerializableExtra("fullData");

        //CityName String
        String cityName = WeatherActivity.getCityName();

        //Date Strings
        String dateValue = Objects.requireNonNull(data.get("date")).toString().trim();

        //Temp Strings
        String actualTemp = data.get("actualTemp").toString().trim() + "°C";
        String actualTempMin = data.get("actualTempMin").toString().trim() + "°C";
        String actualTempMax = data.get("actualTempMax").toString().trim() + "°C";
        String feelsLikeTemp = data.get("feelsLikeTemp").toString().trim() + "°C";

        //Humidity Strings
        String humidity = data.get("feelsLikeTemp").toString().trim() + "%";

        //Wind Strings
        String windSpeed = data.get("windSpeed").toString().trim() + "mps";

        //Overall Strings
        String overallCondition = data.get("overallCondition").toString().trim();

        //Cloudiness Strings
        String cloudiness = data.get("cloudiness").toString().trim();


        cityNameTextView.setText(cityName);
        dateTextView.setText(dateValue);

        actualTempTextView.setText(actualTemp);
        MaxTempTextView.setText(actualTempMax);
        MinTempTextView.setText(actualTempMin);
        FeelsLikeTempTextView.setText(feelsLikeTemp);
        humidityTextView.setText(humidity);
        windSpeedTextView.setText(windSpeed);
        //overallImageView
        switch (overallCondition) {
            case "Thunderstorm":
                overallImageView.setImageResource(R.drawable.thunderstorm);
                break;
            case "Drizzle":
                overallImageView.setImageResource(R.drawable.drizzle);
                break;
            case "Rain":
                overallImageView.setImageResource(R.drawable.rain);
                break;
            case "Snow":
                overallImageView.setImageResource(R.drawable.snow);
                break;
            case "Clear":
                overallImageView.setImageResource(R.drawable.clear);
                break;
            case "Clouds":
                overallImageView.setImageResource(R.drawable.cloudy);
                break;
            default:
                overallImageView.setImageResource(R.drawable.mist);
                break;
        }
        overallTextView.setText(overallCondition);
        cloudinessTextView.setText(cloudiness);
    }
}