package com.example.tabletapp.Tablet.Weather.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.tabletapp.R;
import com.example.tabletapp.RestApi.JSONLoader;
import com.example.tabletapp.RestApi.ReaderJSON;

import org.json.JSONObject;

public class DownloadWeather implements LoaderManager.LoaderCallbacks<JSONObject> {

    private Context context;
    private LoaderManager loaderManager;
    private DownloadListener listener;

    public DownloadWeather(Context context, LoaderManager loaderManager, DownloadListener listener) {
        this.context = context;
        this.loaderManager = loaderManager;
        this.listener = listener;
    }

    public void downLoad(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        loaderManager.initLoader(
                R.integer.weaher_loader,
                    bundle,
                       this);
    }

    @NonNull
    @Override
    public Loader<JSONObject> onCreateLoader(int i, @Nullable Bundle bundle) {
        JSONLoader loader = new JSONLoader(context);
        loader.setUrl(bundle.getString("url"));
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONObject> loader, JSONObject jsonObject) {
        listener.onLoad(ReaderJSON.weather(jsonObject));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<JSONObject> loader) {

    }
}
