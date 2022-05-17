package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> dateValues = new ArrayList<>();
    ArrayList<Double> tempValues = new ArrayList<>();
    ArrayList<Double> feelsLikeValues = new ArrayList<>();
    ArrayList<Double> windValues = new ArrayList<>();
    ArrayList<String> overallValues = new ArrayList<>();
    ArrayList<String> overallIcons = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapter(ArrayList<String> dateValues,
                               ArrayList<Double> tempValues,
                               ArrayList<Double> feelsLikeValues,
                               ArrayList<Double> windValues,
                               ArrayList<String> overallValues,
                               ArrayList<String> overallIcons,
                               Context mContext) {
        this.dateValues = dateValues;
        this.tempValues = tempValues;
        this.feelsLikeValues = feelsLikeValues;
        this.windValues = windValues;
        this.overallValues = overallValues;
        this.overallIcons = overallIcons;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_table_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateValue.setText(dateValues.get(position));
        holder.tempValue.setText(String.valueOf(tempValues.get(position)));
        holder.feelsLikeValue.setText(String.valueOf(feelsLikeValues.get(position)));
        holder.windValue.setText(String.valueOf(windValues.get(position)));
        holder.overallValue.setText(overallValues.get(position));
        switch (overallValues.get(position)) {
            case "Thunderstorm":
                holder.overallIcon.setImageResource(R.drawable.thunderstorm);
                break;
            case "Drizzle":
                holder.overallIcon.setImageResource(R.drawable.drizzle);
                break;
            case "Rain":
                holder.overallIcon.setImageResource(R.drawable.rain);
                break;
            case "Snow":
                holder.overallIcon.setImageResource(R.drawable.snow);
                break;
            case "Clear":
                holder.overallIcon.setImageResource(R.drawable.clear);
                break;
            case "Clouds":
                holder.overallIcon.setImageResource(R.drawable.cloudy);
                break;
            default:
                holder.overallIcon.setImageResource(R.drawable.mist);
                break;
        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: call new activity for detailed page
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateValue, tempValue, feelsLikeValue, windValue, overallValue;
        ImageView overallIcon;
        TableLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateValue = itemView.findViewById(R.id.date_value);
            tempValue = itemView.findViewById(R.id.temp_value);
            feelsLikeValue = itemView.findViewById(R.id.feels_like_value);
            windValue = itemView.findViewById(R.id.wind_value);
            overallValue = itemView.findViewById(R.id.overall_value);
            overallIcon = itemView.findViewById(R.id.overall_icon);
            parentLayout = itemView.findViewById(R.id.parent_table_layout);
        }
    }
}
