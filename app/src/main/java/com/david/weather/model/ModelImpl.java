package com.david.weather.model;

import com.david.weather.model.api.ApiFactory;
import com.david.weather.model.api.ApiInterface;
import com.david.weather.model.dto.CurrentWeather;
import com.david.weather.model.dto.ForecastWeather;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiFactory.getApiInterface();

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
        ;
    }

    @Override
    public Observable<ForecastWeather> getGlobalWeather(double lon, double lat, String lang) {
        return apiInterface
                .getWeatherForecast(lon, lat, lang)
                .compose(applySchedulers());
    }

    @Override
    public Observable<CurrentWeather> getCurrentWeather(double lon, double lat, String lang) {
        return apiInterface
                .getCurrentWeather(lon, lat, lang)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
