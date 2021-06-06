package com.example.tabletapp.Tablet.Weather;

import android.util.Log;

import java.util.Date;

public class ImageSetter {

    public static void setImage(Weather weather) {
        String id = String.valueOf(weather.getId());
        Date date = new Date(weather.getDt());
        boolean morning;
        if (date.getHours() <= 6 || date.getHours() >= 23)
            morning = false;
        else
            morning = true;
        char startChar = id.charAt(0);
        char endChar = id.charAt(id.length() - 1);
        char secondChar = id.charAt(1);

        if (startChar == '8' && morning) {
            if (endChar == '0')
                weather.setRes_image(WeatherRes.CLEAR.get());
            else if (endChar == '1' || endChar == '2')
                weather.setRes_image(WeatherRes.MIN_CLOUDS.get());
            else
                weather.setRes_image(WeatherRes.MAX_CLOUDS.get());
        }
        else if (startChar == '8' && !morning) {
            if (endChar == '0')
                weather.setRes_image(WeatherRes.MOON.get());
            else
                weather.setRes_image(WeatherRes.MOON_CLOUDS.get());
        }

        else if (startChar == '7')
            weather.setRes_image(WeatherRes.FOG.get());

        else if (startChar == '6')
            weather.setRes_image(WeatherRes.SNOW.get());

        else if (startChar == '5') {
            if (secondChar == '1')
                weather.setRes_image(WeatherRes.SNOW.get());
            else {
                if (endChar == '0' || endChar == '1')
                    weather.setRes_image(WeatherRes.LIGHT_RAIN.get());
                else
                    weather.setRes_image(WeatherRes.HEAVY_RAIN.get());
            }
        }

        else if (startChar == '4')
            weather.setRes_image(WeatherRes.LIGHT_RAIN.get());

        else if (startChar == '2')
            weather.setRes_image(WeatherRes.THUNDERSHTORM.get());
    }
}
