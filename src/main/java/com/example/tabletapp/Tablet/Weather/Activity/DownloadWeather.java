package com.example.tabletapp.Tablet.Weather.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.tabletapp.R;
import com.example.tabletapp.RestApi.JSONLoader;
import com.example.tabletapp.RestApi.ReaderJSON;
import com.example.tabletapp.RestApi.RequestURL;

import org.json.JSONObject;

public class DownloadWeather implements LoaderManager.LoaderCallbacks<JSONObject> {

    private DownloadListener listener;
    private LoaderManager loaderManager;
    private JSONLoader loader;

    public DownloadWeather(DownloadListener listener, Context context, LoaderManager loaderManager) {
        this.listener = listener;
        this.loaderManager = loaderManager;
        loader = new JSONLoader(context);
        loader.setUrl(RequestURL.FIVE_DAY_WEATHER_FORECAST.getUrl());
        loaderManager.initLoader(R.integer.weather_list_loader, Bundle.EMPTY, this);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, JSONObject o) {
        listener.onLoad(ReaderJSON.weatherList(o));
        loaderManager.destroyLoader(R.integer.weather_list_loader);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
