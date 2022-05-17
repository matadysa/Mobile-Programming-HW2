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
import java.util.HashMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> dateValues;
    ArrayList<Double> tempValues;
    ArrayList<Double> feelsLikeValues;
    ArrayList<Double> windValues;
    ArrayList<String> overallValues;
    ArrayList<HashMap<String, Object>> fullData;
    Context mContext;

    public RecyclerViewAdapter(Context mContext,
                               ArrayList<String> dateValues,
                               ArrayList<Double> tempValues,
                               ArrayList<Double> feelsLikeValues,
                               ArrayList<Double> windValues,
                               ArrayList<String> overallValues,
                               ArrayList<HashMap<String, Object>> fullData) {
        this.mContext = mContext;
        this.dateValues = dateValues;
        this.tempValues = tempValues;
        this.feelsLikeValues = feelsLikeValues;
        this.windValues = windValues;
        this.overallValues = overallValues;
        this.fullData = fullData;
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
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: call new activity for detailed page, pass the fulllData to next activity
//            }
//        });
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
