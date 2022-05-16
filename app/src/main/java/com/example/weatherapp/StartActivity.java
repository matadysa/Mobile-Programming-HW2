package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    private FragmentsStateAdapter fragmentsStateAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        fragmentsStateAdapter = new FragmentsStateAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager2 viewPager) {
        FragmentsStateAdapter adapter = new FragmentsStateAdapter(getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(new DarkModePageFragment());
        viewPager.setAdapter(adapter);
    }
}