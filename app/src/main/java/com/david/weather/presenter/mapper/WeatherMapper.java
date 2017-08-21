package com.david.weather.presenter.mapper;

import com.david.weather.helper.DayFormatter;
import com.david.weather.helper.Util;
import com.david.weather.model.dto.CurrentWeather;
import com.david.weather.presenter.vo.Weather;

import javax.inject.Inject;

import rx.functions.Func1;

/**
 * Created by TechnoA on 16.08.2017.
 */

public class WeatherMapper implements Func1<CurrentWeather,Weather> {

    @Inject
    public WeatherMapper() {
    }

    @Override
    public Weather call(CurrentWeather currentWeather) {
        Weather weather = new Weather(currentWeather.getName(),
                currentWeather.dt,
                currentWeather.weather.get(0).description,
                currentWeather.main.getTempMax(),
                currentWeather.main.getTempMin(),
                currentWeather.main.getTemp(), DayFormatter.timestampToTime(currentWeather.dt));
        return weather;
    }
}
