package com.example.tabletapp.RestApi;

public enum RequestURL {
    CURRENT_WEATHER("https://api.openweathermap.org/" +
            "data/2.5/" +
            "onecall?lat=56.384377&lon=44.016687&" +
            "units=metric&" +
            "exclude=minutely,hourly,daily,alerts&" +
            "appid=53ecd4bc8ab2385c5ba1387dfec943da&" +
            "lang=ru"),
    FIVE_DAY_WEATHER_FORECAST("https://api.openweathermap.org/" +
            "data/2.5/" +
            "forecast?" +
            "q=Bor&" +
            "units=metric&" +
            "exclude=minutely,hourly,daily,alerts&" +
            "appid=53ecd4bc8ab2385c5ba1387dfec943da&" +
            "lang=ru");

    private String url;

    RequestURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
