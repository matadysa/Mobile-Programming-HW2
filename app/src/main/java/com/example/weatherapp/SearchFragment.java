package com.example.weatherapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class SearchFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton fiAndLRadioButton, cityNameRadioButton;
    TextInputEditText fiInputText, LInputText, cityNameInputText;
    Button searchButton;

    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    private final String weatherApiUrl = "api.openweathermap.org/data/2.5/weather?";
    //https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
    private final String coordinateApiUrl = "https://api.openweathermap.org/data/2.5/onecall?";
    private final String appId = "b45229916cb8cfb3ec05d4579870f195";

    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context contextThemeWrapper;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Dark);
        } else {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Light);
        }

        LayoutInflater layoutInflater = inflater.cloneInContext(contextThemeWrapper);

        View view = layoutInflater.inflate(R.layout.fragment_search, container, false);


        return view;
    }

    private void getWeatherInformation(String cityName, Double longitude, Double latitude) {
        String url = "";
        if (cityName == null) {
            url = coordinateApiUrl +
                    "lat=" + df.format(latitude) +
                    "&lon=" + df.format(longitude) +
                    "&exclude=minutely,hourly,alerts" +
                    "&appid=" + appId;
        } else {
            url = weatherApiUrl + "q=" + cityName + "&appid=" + appId;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}