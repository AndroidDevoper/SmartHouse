package com.example.tabletapp.Tablet.Weather;

import com.example.tabletapp.R;

public enum WeatherRes {
    CLEAR(R.mipmap.weather_clear),
    MOON(R.mipmap.weather_moon),
    SNOW(R.mipmap.weather_snow),
    FOG(R.mipmap.weather_fog),
    MIN_CLOUDS(R.mipmap.weather_min_clouds),
    MAX_CLOUDS(R.mipmap.weather_max_clouds),
    MOON_CLOUDS(R.mipmap.weather_clouds_moon),
    THUNDERSHTORM(R.mipmap.weather_thunderstorm),
    LIGHT_RAIN(R.mipmap.weather_light_rain),
    HEAVY_RAIN(R.mipmap.weather_heavy_rain);

    private int res_id;
    WeatherRes(int id) {
        res_id = id;
    }

    public int get() {
        return res_id;
    }
}
