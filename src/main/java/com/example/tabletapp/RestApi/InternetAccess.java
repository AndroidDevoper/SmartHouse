package com.example.tabletapp.RestApi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

public class InternetAccess extends AsyncTask<Void, Void, Boolean> {

    public interface OnListener {
        void available();

        void notAvailable();
    }

    private Context context;
    private OnListener listener;

    public InternetAccess(Context context, OnListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return isAccess();
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if (aVoid)
            listener.available();
        else
            listener.notAvailable();
    }

    private boolean isAccess() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null)
                return true;
            else {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    return false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

