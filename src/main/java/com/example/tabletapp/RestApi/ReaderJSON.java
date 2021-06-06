package com.example.tabletapp.RestApi;

import com.example.tabletapp.Tablet.Weather.ImageSetter;
import com.example.tabletapp.Tablet.Weather.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReaderJSON {

    public static Weather weather(JSONObject object) {
        try {
            JSONObject current = object.getJSONObject("current");
            JSONObject weatherObj = current.
                    getJSONArray("weather").
                    getJSONObject(0);
            Weather weather = new Weather(current.getLong("dt"),
                    current.getInt("temp"),
                    current.getInt("pressure"),
                    current.getInt("humidity"),
                    current.getInt("clouds"),
                    current.getString("wind_speed"),
                    weatherObj.getInt("id")
                    );
            ImageSetter.setImage(weather);
            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    public static List<Weather> weatherList(JSONObject object) {
        List<Weather> list = new ArrayList<>();
        try {
            int count = object.getInt("cnt");
            JSONArray array = object.getJSONArray("list");
            for (int i = 0; i < count; i++) {
                JSONObject obj = array.getJSONObject(i);
                JSONObject main = obj.getJSONObject("main");
                JSONArray weathers = obj.getJSONArray("weather");
                JSONObject weatherID = (JSONObject) weathers.get(0);
                Weather weather = new Weather(obj.getLong("dt"),
                        main.getInt("temp"),
                        weatherID.getInt("id"));
                ImageSetter.setImage(weather);
                list.add(weather);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
