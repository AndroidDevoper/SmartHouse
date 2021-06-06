package com.example.tabletapp.Tablet.Weather.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tabletapp.R;

public class WeatherActivity extends AppCompatActivity implements WeatherActivityContract{

    private WeatherActivityPresenter presenter;
    private Button btn_back;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wether_activity);
        init();
    }

    private void init() {
        presenter = new WeatherActivityPresenter(this, this);
        btn_back = findViewById(R.id.activity_weather_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickBack();
            }
        });
        linearLayout = findViewById(R.id.weather_scroll_linerLayout);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setView(View view) {
        linearLayout.addView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
