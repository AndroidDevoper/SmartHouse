package com.example.tabletapp.Tablet.Weather.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabletapp.R;
import com.example.tabletapp.Tablet.Weather.Weather;

import java.util.Date;
import java.util.List;

public class WeatherAdapter {

    private List<Weather> list;
    private LayoutInflater layoutInflater;

    public WeatherAdapter(List<Weather> list, LayoutInflater inflater) {
        layoutInflater = inflater;
        this.list = list;
    }

    public View OnCreateView(int position) {
        Weather weather = list.get(position);
        View view = layoutInflater.inflate(R.layout.item_weather, null, false);
        init(view, weather);
        return view;
    }
    private void init(View view, Weather weather) {
        TextView tv_date = view.findViewById(R.id.item_weater_date);
        tv_date.setText(createDate(weather));
        TextView tv_time = view.findViewById(R.id.item_weater_time);
        tv_time.setText(createTime(weather));
        TextView tv_temp = view.findViewById(R.id.item_weater_temp);
        tv_temp.setText(String.valueOf(weather.getTemp()));
        ImageView imageView = view.findViewById(R.id.item_weater_image);
        imageView.setImageResource(weather.getRes_image());
    }

    private String createDate(Weather weather) {
        Date dateObj = new Date(weather.getDt());
        int day = dateObj.getDate();
        int month = dateObj.getMonth();
        month++;
        StringBuilder builder = new StringBuilder();
        if (day < 10)
            builder.append("0" + day + ".");
        else
            builder.append(day + ".");
        if (month < 10)
            builder.append("0" + month);
        else
            builder.append(month);
        return builder.toString();
    }

    private String createTime(Weather weather) {
        Date dateObj = new Date(weather.getDt());
        int hours = dateObj.getHours();
        int minutes = dateObj.getMinutes();
        StringBuilder builder = new StringBuilder();
        if (hours < 10)
            builder.append("0" + hours + ":");
        else
            builder.append(hours + ":");
        if (minutes < 10)
            builder.append("0" + minutes);
        else
            builder.append(minutes);
        return builder.toString();
    }
}
