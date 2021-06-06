package com.example.tabletapp.Tablet.Weather.Fragment;

public interface WeatherFragmentContract {
    void setImage(int id);
    void setDate(String temp,
                    String pressure,
                        String humidity,
                            String clouds,
                                String wind_speed);
    void loading();
    void onLoad();
}
