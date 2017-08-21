package com.david.weather.other.di.view;

import com.david.weather.view.fragment.WeatherFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(WeatherFragment weatherFragment);

}
