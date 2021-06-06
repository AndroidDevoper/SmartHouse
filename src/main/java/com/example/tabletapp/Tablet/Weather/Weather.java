package com.example.tabletapp.Tablet.Weather;

public class Weather {

    private long dt;
    private int temp;
    private int pressure;
    private int humidity;
    private int clouds;
    private String wind_speed;
    private int id;
    private int res_image;

    public Weather(long dt, int temp, int pressure, int humidity, int clouds, String wind_speed, int id) {
        this.dt = dt*1000L;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds = clouds;
        this.wind_speed = wind_speed;
        this.id = id;
    }

    public Weather(long dt, int temp, int id) {
        this.dt = dt*1000L;
        this.temp = temp;
        this.id = id;
    }

    public void setRes_image(int res_image) {
        this.res_image = res_image;
    }

    public int getRes_image() {
        return res_image;
    }

    public long getDt() {
        return dt;
    }

    public int getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getClouds() {
        return clouds;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public int getId() {
        return id;
    }
}
