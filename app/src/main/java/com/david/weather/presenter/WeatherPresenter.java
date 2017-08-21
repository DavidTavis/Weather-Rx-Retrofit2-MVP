package com.david.weather.presenter;

import android.os.Bundle;

import com.david.weather.helper.DayFormatter;
import com.david.weather.helper.TemperatureFormatter;
import com.david.weather.helper.Util;
import com.david.weather.other.App;
import com.david.weather.presenter.mapper.ForecastListMapper;
import com.david.weather.presenter.mapper.WeatherMapper;
import com.david.weather.presenter.vo.Forecast;
import com.david.weather.presenter.vo.Weather;
import com.david.weather.view.fragment.WeatherView;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

public class WeatherPresenter extends BasePresenter {

    private static final String BUNDLE_FORECAST_LIST_KEY = "BUNDLE_FORECAST_LIST_KEY";
    private static final String BUNDLE_CURRENT_WEATHER_KEY = "BUNDLE_CURRENT_WEATHER_KEY";

    public WeatherView view;

    private List<Forecast> forecastList;
    private Weather weather;

    public String timeUpdate;

    @Inject
    protected ForecastListMapper forecastListMapper;

    @Inject
    protected WeatherMapper weatherMapper;

    // for DI
    @Inject
    public WeatherPresenter() {
    }

    public WeatherPresenter(WeatherView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    public void fetchWeatherForecast(double lon, double lat, String lang) {

        if (lat == 0 || lon == 0 || lang.isEmpty()) return;

        Subscription subCurrentWeather = model.getCurrentWeather(lon,lat,lang)
                .map(weatherMapper)
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Weather weatherData) {
                        if(weatherData!=null){
                            weather = weatherData;
                            view.showCurrentWeather(weatherData.getLocation(), TemperatureFormatter.format(weatherData.getTemperature()));
                            timeUpdate = weatherData.getTime();
                        }
                    }
                });
        addSubscription(subCurrentWeather);

        Subscription subForecast = model.getGlobalWeather(lon, lat, lang)
                .map(forecastListMapper)
                .subscribe(new Observer<List<Forecast>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Forecast> list) {
                        if (list != null && !list.isEmpty()) {
                            forecastList = list;
                            view.showForecastList(list);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
        addSubscription(subForecast);
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            forecastList = (List<Forecast>) savedInstanceState.getSerializable(BUNDLE_FORECAST_LIST_KEY);
            weather = (Weather) savedInstanceState.getSerializable(BUNDLE_CURRENT_WEATHER_KEY);
        }

        if (!isForecastListEmpty())
            view.showForecastList(forecastList);

        if(weather!=null)
            view.showCurrentWeather(weather.getLocation(),TemperatureFormatter.format(weather.getTemperature()));
    }

    private boolean isForecastListEmpty() {
        return forecastList == null || forecastList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isForecastListEmpty())
            outState.putSerializable(BUNDLE_FORECAST_LIST_KEY, new ArrayList<>(forecastList));

        if(weather!=null)
            outState.putSerializable(BUNDLE_CURRENT_WEATHER_KEY, weather);
    }

}
