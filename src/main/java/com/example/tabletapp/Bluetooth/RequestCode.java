package com.example.tabletapp.Bluetooth;

public enum RequestCode {
    APPLY_PERMISSION_BLUETOOTH(1100);

    private int code;

    RequestCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
