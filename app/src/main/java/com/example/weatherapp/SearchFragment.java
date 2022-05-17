package com.example.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Objects;

public class SearchFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton fiAndLRadioButton, cityNameRadioButton;
    EditText longitudeInputText, latitudeInputText, cityNameInputText;
    Button searchButton;

    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?";
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

        cityNameRadioButton = view.findViewById(R.id.city_name_radio_button);
        fiAndLRadioButton = view.findViewById(R.id.fi_and_l_radio_button);

        cityNameInputText = view.findViewById(R.id.city_edittext);
        longitudeInputText = view.findViewById(R.id.longitude_edittext);
        latitudeInputText = view.findViewById(R.id.latitude_edittext);

        searchButton = view.findViewById(R.id.button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cityNameRadioButton.isChecked()) {
                    String cityName = Objects.requireNonNull(cityNameInputText.getText()).toString();
                    getWeatherInformation(cityName, null, null);
                } else if (fiAndLRadioButton.isChecked()) {
                    Double longitude = Double.valueOf(Objects.requireNonNull(longitudeInputText.getText()).toString().trim());
                    Double latitude = Double.valueOf(Objects.requireNonNull(latitudeInputText.getText()).toString().trim());
                    getWeatherInformation(null, longitude, latitude);
                } else {
                    Toast.makeText(getActivity(), "select a radio button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void getWeatherInformation(String cityName, Double longitude, Double latitude) {
        String url;
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
        }, error -> Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);
    }

}