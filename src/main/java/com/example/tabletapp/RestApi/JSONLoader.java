package com.example.tabletapp.RestApi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class JSONLoader extends AsyncTaskLoader<JSONObject> {

    private String url;

    public JSONLoader(@NonNull Context context) {
        super(context);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public JSONObject loadInBackground() {
        return load();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    private JSONObject load() {
        HttpsURLConnection con;
        try {
            con = (HttpsURLConnection) new URL(url).openConnection();

            con.setSSLSocketFactory(new TLSSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return session.getPeerHost().equals(hostname);
                }
            });
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                           con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            con.disconnect();
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        throw new NullPointerException();
    }
}


