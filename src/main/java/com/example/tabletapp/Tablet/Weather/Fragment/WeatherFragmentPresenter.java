package com.example.tabletapp.Tablet.Weather.Fragment;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.example.tabletapp.RestApi.InternetAccess;
import com.example.tabletapp.RestApi.RequestURL;
import com.example.tabletapp.Tablet.Weather.Weather;
import com.example.tabletapp.Tablet.Weather.WeatherListener;

public class WeatherFragmentPresenter implements DownloadListener, InternetAccess.OnListener {

    private WeatherFragmentContract view;
    private WeatherListener listener;
    private Context context;
    private DownloadWeather download;
    private InternetAccess internetAccess;
    private LoaderManager loaderManager;

    public WeatherFragmentPresenter(Context context, LoaderManager loaderManager, WeatherFragmentContract view, WeatherListener listener) {
        this.view = view;
        this.loaderManager = loaderManager;
        this.listener = listener;
        this.context = context;
        init();
    }

    private void init() {
        this.internetAccess = new InternetAccess(context, this);
        internetAccess.execute();
    }


    public void detach() {
        view = null;
    }


    @Override
    public void onLoad(Weather weather) {
        view.setImage(weather.getRes_image());
        view.onLoad();
        view.setDate(String.valueOf(weather.getTemp()),
                String.valueOf(weather.getPressure()),
                (weather.getHumidity()) + " %",
                (weather.getClouds()) + " %",
                (weather.getWind_speed() + " м/c"));
    }

    @Override
    public void available() {
        download = new DownloadWeather(context,
                loaderManager,
                this);
        download.downLoad(RequestURL.CURRENT_WEATHER.getUrl());
    }

    @Override
    public void notAvailable() {
        view.loading();
        view.setDate(" ", " ", " ", " ", " ");
        listener.showToast("Нет доступа к интернету");
        init();
    }

    public void upDate() {
        init();
    }
}
