package com.david.weather.view.fragment;

import com.david.weather.presenter.vo.Forecast;
import com.david.weather.presenter.vo.Weather;

import java.util.List;

public interface WeatherView extends View {

    void showForecastList(List<Forecast> vo);

    void showCurrentWeather(String location, String temp);

    void showEmptyList();

    void stopRefreshing();

    void updateWeather();

}
