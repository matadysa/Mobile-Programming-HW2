package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class SearchFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton fiAndLRadioButton, cityNameRadioButton;
    EditText longitudeInputText, latitudeInputText, cityNameInputText;
    Button searchButton;
    Handler handler = new Handler();

    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?";
    //https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
    private final String coordinateApiUrl = "https://api.openweathermap.org/data/2.5/onecall?";
    //http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}
    private final String geocodingApiUrl = "http://api.openweathermap.org/geo/1.0/direct?";
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
                handler.removeCallbacksAndMessages(null);

                if (cityNameRadioButton.isChecked()) {
                    String cityName = Objects.requireNonNull(cityNameInputText.getText()).toString();

                    if(!cityName.equals("")){
                        getWeatherInformation(cityName, null, null);
                    }
                } else if (fiAndLRadioButton.isChecked()) {
                    String longitude = Objects.requireNonNull(longitudeInputText.getText()).toString().trim();
                    String latitude = Objects.requireNonNull(latitudeInputText.getText()).toString().trim();

                    if(!longitude.equals("") && !latitude.equals("")){
                        getWeatherInformation(null, Double.valueOf(longitude), Double.valueOf(latitude));
                    }

                } else {
                    Toast.makeText(getActivity(), "select a radio button", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cityNameInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                if (cityNameRadioButton.isChecked()) {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            String cityName = Objects.requireNonNull(cityNameInputText.getText()).toString();
                            if(!cityName.equals("")){
                                getWeatherInformation(cityName, null, null);
                            }
                        }
                    };
                    handler.postDelayed(runnable, 5000);


                }
            }
        });

        latitudeInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                if (fiAndLRadioButton.isChecked()) {
                    Runnable runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            String longitude = Objects.requireNonNull(longitudeInputText.getText()).toString().trim();
                            String latitude = Objects.requireNonNull(latitudeInputText.getText()).toString().trim();

                            if(!longitude.equals("") && !latitude.equals("")){
                                getWeatherInformation(null, Double.valueOf(longitude), Double.valueOf(latitude));
                            }
                        }
                    };
                    handler.postDelayed(runnable2, 5000);
                }
            }
        });

        return view;
    }

    private void getWeatherInformation(String cityName, Double longitude, Double latitude) {
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        if (cityName != null) {
            String cityUrl = weatherApiUrl +
                    "q=" + cityName +
                    "&appid=" + appId;
            StringRequest request = new StringRequest(Request.Method.POST, cityUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    Log.d("response", response);
                    double lon = 0, lat = 0;
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject jsonObjectCoord = jsonResponse.getJSONObject("coord");
                        lon = jsonObjectCoord.getDouble("lon");
                        lat = jsonObjectCoord.getDouble("lat");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    weatherApiHandler(lon, lat, cityName);
                }
            }, error -> Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show());
            requestQueue.add(request);
        } else weatherApiHandler(longitude, latitude, null);
    }

    private void weatherApiHandler(double longitude, double latitude, String cityName) {
        String url;
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        url = coordinateApiUrl +
                "lat=" + df.format(latitude) +
                "&lon=" + df.format(longitude) +
                "&units=metric" +
                "&exclude=current,minutely,hourly,alerts" +
                "&appid=" + appId;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.d("response", response);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray days = jsonResponse.getJSONArray("daily");
                    ArrayList<HashMap<String, Object>> daysData = new ArrayList<>();
                    for (int i = 0; i < 8; i++) {
                        daysData.add(jsonDataExtractor(days.getJSONObject(i)));
                    }
                    //Navigate to next activity
                    Intent intent = new Intent(getActivity(), WeatherActivity.class);
                    intent.putExtra("lon", longitude);
                    intent.putExtra("lat", latitude);
                    if (cityName == null) intent.putExtra("cityName", "");
                    else intent.putExtra("cityName", cityName);
                    intent.putExtra("data", daysData);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show());

        requestQueue.add(stringRequest);
    }

    private HashMap<String, Object> jsonDataExtractor(JSONObject jsonObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            //Date
            Date date = new Date(jsonObject.getLong("dt") * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            hashMap.put("date", sdf.format(date));
            //Actual Temp
            JSONObject actualTempObject = jsonObject.getJSONObject("temp");
            double actualTemp, actualTempMax, actualTempMin;
            actualTemp = actualTempObject.getDouble("day");
            actualTempMax = actualTempObject.getDouble("max");
            actualTempMin = actualTempObject.getDouble("min");
            hashMap.put("actualTemp", actualTemp);
            hashMap.put("actualTempMax", actualTempMax);
            hashMap.put("actualTempMin", actualTempMin);
            //Feels Like Temp
            JSONObject feelsLikeTempObject = jsonObject.getJSONObject("feels_like");
            double feelsLikeTemp;
            feelsLikeTemp = feelsLikeTempObject.getDouble("day");
            hashMap.put("feelsLikeTemp", feelsLikeTemp);
            //Humidity
            double humidity = jsonObject.getDouble("humidity");
            hashMap.put("humidity", humidity);
            //Wind Speed
            double windSpeed = jsonObject.getDouble("wind_speed");
            hashMap.put("windSpeed", windSpeed);
            //Overall Weather
            JSONArray overallWeatherArray = jsonObject.getJSONArray("weather");
            JSONObject overallWeatherObject = overallWeatherArray.getJSONObject(0);
            String overallCondition, detailedCondition;
            //Each group need different icons
            //Thunderstorm, Drizzle, Rain, Snow, Clear, Clouds, (Mist, Smoke, Haze, Dust, Fog, Sand, Dust, Ash, Squall, Tornado)
            overallCondition = overallWeatherObject.getString("main");
            detailedCondition = overallWeatherObject.getString("description");
            hashMap.put("overallCondition", overallCondition);
            hashMap.put("detailedCondition", detailedCondition);
            //Cloudiness
            double cloudiness = jsonObject.getDouble("clouds");
            hashMap.put("cloudiness", cloudiness);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

}