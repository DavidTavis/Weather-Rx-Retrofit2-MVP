package com.david.weather.model;

import com.david.weather.model.dto.CurrentWeather;
import com.david.weather.model.dto.ForecastWeather;

import rx.Observable;

public interface Model {

    Observable<ForecastWeather> getGlobalWeather(double lon, double lat, String lang);

    Observable<CurrentWeather> getCurrentWeather(double lon, double lat, String lang);

}
