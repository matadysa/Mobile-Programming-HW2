package com.example.weatherapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
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

        Context contextThemeWrapper;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Dark);
        } else {
            contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Theme_Light);
        }

        LayoutInflater layoutInflater = inflater.cloneInContext(contextThemeWrapper);

        View view = layoutInflater.inflate(R.layout.fragment_search, container, false);

//        radioGroup = view.findViewById(R.id.radio_button_group);
//        fiAndLRadioButton = view.findViewById(R.id.fi_and_L_radio_button);
//        cityNameRadioButton = view.findViewById(R.id.city_name_radio_button);
//
//        fiInputText = view.findViewById(R.id.fi_input_text);
//        LInputText = view.findViewById(R.id.L_input_text);
//        cityNameInputText = view.findViewById(R.id.city_name_input_text);
//
//        searchButton = view.findViewById(R.id.search_button);

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                switch (checkedId){
//                    case R.id.fi_and_L_radio_button:
//                        cityNameInputText.setVisibility(View.GONE);
//                        fiInputText.setVisibility(View.VISIBLE);
//                        LInputText.setVisibility(View.VISIBLE);
//                        break;
//
//                    case R.id.city_name_radio_button:
//                        cityNameInputText.setVisibility(View.VISIBLE);
//                        fiInputText.setVisibility(View.GONE);
//                        LInputText.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });

        return view;
    }
}