package com.example.tabletapp.Tablet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.tabletapp.Tablet.Notes.NotesAdapter;
import com.example.tabletapp.Tablet.Notes.NotesContract;
import com.example.tabletapp.Tablet.Timer.TimerListener;
import com.example.tabletapp.Tablet.Timer.TimerTablet;
import com.example.tabletapp.Tablet.Weather.Activity.WeatherActivity;
import com.example.tabletapp.Tablet.Weather.Fragment.FragmentWeather;
import com.example.tabletapp.Tablet.Weather.WeatherListener;


public class TabletPresenter implements WeatherListener, TimerListener, NotesContract {

    private TabletContract view;
    private FragmentWeather fragmentWeather;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private TimerTablet timer;
    private DateTablet date;
    private Handler handler;

    private Activity activity;

    private NotesAdapter adapter;

    private int countMin = 0;

    public TabletPresenter(TabletContract view, Activity activity) {
        this.view = view;
        this.activity = activity;
        init();
    }

    private void init() {
        initHandler();
        fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentWeather = FragmentWeather.newInstance(this);
        fragmentTransaction.add(view.getIdFragmentContainers(),
                fragmentWeather);
        fragmentTransaction.commit();

        timer = new TimerTablet(this);
        date = new DateTablet();
        adapter = new NotesAdapter(activity, this);
        view.setNoteAdapter(adapter);
    }

    @Override
    public void showToast(String text) {
        view.showToast(text);
    }


    @Override
    public void minutePassed() {
        handler.sendEmptyMessage(0);
        if (countMin == 15) {
            countMin = 0;
            fragmentWeather.upData();
        }
        countMin++;
    }

    public void detach() {
        view = null;
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    view.setDate(date.getTime(), date.getDate());
                    if (adapter != null) adapter.minutePassed();
                }
            }
        };
    }

    public void onClickWeather() {
        activity.startActivity(new Intent(activity.getBaseContext(),
                WeatherActivity.class));
    }
}
