package com.david.weather.other.di.view;

import com.david.weather.presenter.WeatherPresenter;
import com.david.weather.view.fragment.WeatherView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private WeatherView view;

    public ViewDynamicModule(WeatherView view) {
        this.view = view;
    }

    @Provides
    WeatherPresenter provideWeatherPresenter() {
        return new WeatherPresenter(view);
    }

}
