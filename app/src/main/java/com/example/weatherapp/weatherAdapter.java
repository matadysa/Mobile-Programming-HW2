package com.example.weatherapp;

import android.annotation.SuppressLint;
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
import com.example.weatherapp.WeatherData;

public class weatherAdapter extends RecyclerView.Adapter<weatherAdapter.weatherHolder> {

    private ArrayList<WeatherData> weatherData = new ArrayList<>();
    private Context context;


    public weatherAdapter(Context context, ArrayList<WeatherData> weatherData) {
        this.context = context;
        this.weatherData = weatherData;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public weatherAdapter.weatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_table_layout_item, parent, false);
        weatherHolder holder = new weatherHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull weatherAdapter.weatherHolder holder, int position) {
        holder.textView1.setText(weatherData.get(position).getTemp() + "^C");
        holder.textView2.setText(weatherData.get(position).getFeelsLike() + "^C");
        holder.textView3.setText(weatherData.get(position).getWind() + " m/s");
        holder.textView4.setText(weatherData.get(position).getOverall());

        //set icon overall

    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }


    public class weatherHolder extends RecyclerView.ViewHolder{
        private TableLayout tableLayout;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private ImageView imageView4;
        private TextView date;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        public weatherHolder(@NonNull View itemView){
            super(itemView);
            tableLayout = itemView.findViewById(R.id.Table_layout);
            imageView1 = itemView.findViewById(R.id.temp_icon);
            imageView2 = itemView.findViewById(R.id.feels_like_icon);
            imageView3 = itemView.findViewById(R.id.wind_icon);
            imageView4 = itemView.findViewById(R.id.overall_icon);

            date = itemView.findViewById(R.id.text_date);

            textView1 = itemView.findViewById(R.id.temp_text);
            textView2 = itemView.findViewById(R.id.feels_like_text);
            textView3 = itemView.findViewById(R.id.wind_text);
            textView4 = itemView.findViewById(R.id.overall_text);
        }
    }
}
