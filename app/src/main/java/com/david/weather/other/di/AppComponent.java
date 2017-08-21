package com.david.weather.other.di;


import com.david.weather.model.ModelImpl;
import com.david.weather.presenter.BasePresenter;
import com.david.weather.presenter.WeatherPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(ModelImpl model);

    void inject(BasePresenter basePresenter);

    void inject(WeatherPresenter weatherPresenter);

}
