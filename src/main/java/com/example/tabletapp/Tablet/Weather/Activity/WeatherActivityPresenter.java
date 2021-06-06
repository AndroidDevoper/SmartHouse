package com.example.tabletapp.Tablet.Weather.Activity;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.example.tabletapp.RestApi.InternetAccess;
import com.example.tabletapp.Tablet.Weather.Weather;

import java.util.List;

public class WeatherActivityPresenter implements InternetAccess.OnListener, DownloadListener{

    private WeatherActivityContract view;
    private Activity activity;
    private InternetAccess access;
    private DownloadWeather downloadWeather;
    private WeatherAdapter adapter;

    public WeatherActivityPresenter(WeatherActivityContract view, Activity activity) {
        this.view = view;
        this.activity = activity;
        access = new InternetAccess(activity.getBaseContext(),this);
        access.execute();
    }

    public void onClickBack() {
        activity.finish();
    }

    public void detach() {
        view = null;
    }

    @Override
    public void available() {
        downloadWeather = new DownloadWeather(this,
                activity.getBaseContext(),
                ((FragmentActivity)activity).getSupportLoaderManager());
    }

    @Override
    public void notAvailable() {
        view.showToast("Нет сети");
    }

    @Override
    public void onLoad(List<Weather> list) {
        adapter = new WeatherAdapter(list, activity.getLayoutInflater());
        for (int i = 0; i < list.size(); i++) {
            view.setView(adapter.OnCreateView(i));
        }
    }
}
