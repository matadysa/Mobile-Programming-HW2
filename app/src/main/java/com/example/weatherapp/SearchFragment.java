package com.example.weatherapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class SearchFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton fiAndLRadioButton, cityNameRadioButton;
    TextInputEditText fiInputText, LInputText, cityNameInputText;
    Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radio_button_group);
        fiAndLRadioButton = view.findViewById(R.id.fi_and_L_radio_button);
        cityNameRadioButton = view.findViewById(R.id.city_name_radio_button);

        fiInputText = view.findViewById(R.id.fi_input_text);
        LInputText = view.findViewById(R.id.L_input_text);
        cityNameInputText = view.findViewById(R.id.city_name_input_text);

        searchButton = view.findViewById(R.id.search_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.fi_and_L_radio_button:
                        cityNameInputText.setVisibility(View.GONE);
                        fiInputText.setVisibility(View.VISIBLE);
                        LInputText.setVisibility(View.VISIBLE);
                        break;

                    case R.id.city_name_radio_button:
                        cityNameInputText.setVisibility(View.VISIBLE);
                        fiInputText.setVisibility(View.GONE);
                        LInputText.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
}