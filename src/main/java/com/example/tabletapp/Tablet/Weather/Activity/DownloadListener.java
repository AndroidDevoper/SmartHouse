package com.example.tabletapp.Tablet.Weather.Activity;

import com.example.tabletapp.Tablet.Weather.Weather;

import java.util.List;

public interface DownloadListener {
    void onLoad(List<Weather> list);
}
