package com.example.tabletapp.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.example.tabletapp.Bluetooth.BluetoothHelper;
import com.example.tabletapp.Bluetooth.BluetoothReadyListener;
import com.example.tabletapp.Bluetooth.RequestCode;
import com.example.tabletapp.Tablet.TabletActivity;

public class MainPresenter implements BluetoothReadyListener {

    private MainContract view;
    private BluetoothHelper bluetooth;
    private Activity activity;
    private boolean isReady;

    public MainPresenter(Activity activity, MainContract view) {
        this.activity = activity;
        this.view = view;
        bluetooth = new BluetoothHelper(activity,
                this);
    }

    public void onClickBtnTablet() {
        bluetooth.checkReady();
        if (isReady)
            activity.startActivity(new Intent(activity.getBaseContext(),
                TabletActivity.class));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestCode.APPLY_PERMISSION_BLUETOOTH.getCode()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                bluetooth.checkReady();
            else
                view.showToast("Отмена");
        }
    }


    @Override
    public void isEnabled() {
        isReady = true;
    }

    @Override
    public void isDisabled(String text) {
        isReady = false;
        view.showToast(text);
    }

    @Override
    public void turnOn(Intent intent) {
        activity.startActivity(intent);
    }

}
