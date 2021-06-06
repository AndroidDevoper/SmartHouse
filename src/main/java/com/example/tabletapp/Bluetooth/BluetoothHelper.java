package com.example.tabletapp.Bluetooth;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class BluetoothHelper {

    private BluetoothAdapter adapter;
    private BluetoothReadyListener listener;
    private Activity activity;


    public BluetoothHelper(Activity activity, BluetoothReadyListener listener) {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
        this.listener = listener;
        this.activity = activity;

        if (!isPermission()) applyPermission();
    }

    public void checkReady() {
        if (adapter.isEnabled()) {
            if (isPermission()) listener.isEnabled();
            else {
                listener.isDisabled("Необходимо разрешение");
                applyPermission();
            }
        }
        else {
            listener.turnOn(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
            listener.isDisabled("Блютуз выключен");
        }
    }

    private boolean isPermission() {
        if (ContextCompat.checkSelfPermission(activity.getBaseContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else return false;
    }

    private void applyPermission() {
        ActivityCompat.requestPermissions(activity,
                new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                RequestCode.APPLY_PERMISSION_BLUETOOTH.getCode());
    }

}
