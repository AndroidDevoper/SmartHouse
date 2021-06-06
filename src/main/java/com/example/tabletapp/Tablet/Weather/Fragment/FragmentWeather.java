package com.example.tabletapp.Tablet.Weather.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tabletapp.R;
import com.example.tabletapp.Tablet.Weather.WeatherListener;


public class FragmentWeather extends Fragment implements WeatherFragmentContract {

    private TextView temp;
    private TextView pressure;
    private TextView humidity;
    private TextView clouds;
    private TextView wind_speed;
    private ImageView imageView;
    private WeatherFragmentPresenter presenter;
    private WeatherListener listener;
    private ProgressBar progressBar;

    public FragmentWeather() {

    }

    public static FragmentWeather newInstance(WeatherListener listener) {
        FragmentWeather fragment = new FragmentWeather();
        Bundle args = new Bundle();
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(WeatherListener listener) {
        this.listener = listener;
    }

    public void upData() {
        presenter.upDate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        temp = view.findViewById(R.id.weather_tv_temp);
        pressure = view.findViewById(R.id.weather_tv_pressureValue);
        humidity = view.findViewById(R.id.weather_tv_humidityValue);
        clouds = view.findViewById(R.id.weather_tv_cloudsValue);
        wind_speed = view.findViewById(R.id.weather_tv_windSpeedValue);
        imageView = view.findViewById(R.id.fragment_tablet_weather_icon);
        imageView.setVisibility(View.INVISIBLE);
        progressBar = view.findViewById(R.id.weather_progressBar);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        presenter.detach();
        super.onDetach();
    }

    @Override
    public void setImage(int id) {
        imageView.setImageResource(id);
    }

    @Override
    public void setDate(String temp, String pressure, String humidity, String clouds, String wind_speed) {
        this.temp.setText(temp);
        this.pressure.setText(pressure);
        this.humidity.setText(humidity);
        this.clouds.setText(clouds);
        this.wind_speed.setText(wind_speed);
    }

    @Override
    public void loading() {
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoad() {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        presenter.detach();
        super.onPause();
    }

    @Override
    public void onResume() {
        presenter = new WeatherFragmentPresenter(getContext(),
                getActivity().getSupportLoaderManager(),
                this,
                listener);
        super.onResume();
    }
}
