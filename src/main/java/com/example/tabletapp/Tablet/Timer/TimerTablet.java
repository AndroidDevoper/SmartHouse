package com.example.tabletapp.Tablet.Timer;


import java.util.Timer;
import java.util.TimerTask;

public class TimerTablet extends TimerTask{

    private TimerListener listener;
    private Timer timer;

    public TimerTablet(TimerListener listener) {
        this.listener = listener;
        timer = new Timer();
        timer.schedule(this,10,60000);
    }

    @Override
    public void run() {
        listener.minutePassed();
    }
}
