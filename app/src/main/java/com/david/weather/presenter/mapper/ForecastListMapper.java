package com.david.weather.presenter.mapper;

import com.david.weather.model.dto.ForecastWeather;
import com.david.weather.presenter.vo.Forecast;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class ForecastListMapper implements Func1<ForecastWeather, List<Forecast>> {

    @Inject
    public ForecastListMapper() {
    }

    @Override
    public List<Forecast> call(ForecastWeather forecastWeather) {
        List<Forecast> forecastList = Observable.from(forecastWeather.cityEntity)
                .map(cityEntity -> new Forecast(forecastWeather.city.name, cityEntity.dt, cityEntity.weather.get(0).description, cityEntity.temp.max,cityEntity.temp.min))
                .toList()
                .toBlocking()
                .first();
        return forecastList;
    }

}
