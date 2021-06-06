package com.example.tabletapp.Bluetooth;

import android.content.Intent;

public interface BluetoothReadyListener {
    void isEnabled();
    void isDisabled(String text);
    void turnOn(Intent intent);
}
