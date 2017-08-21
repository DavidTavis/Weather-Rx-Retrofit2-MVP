package com.david.weather.model.api;

import com.david.weather.model.dto.CurrentWeather;
import com.david.weather.model.dto.ForecastWeather;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    String APP_ID = "f8c690ef9002bb5fac771397f582ce12";
    String BASE_URL = "http://api.openweathermap.org";

    @Headers({"Accept: application/json"})
    @GET("/data/2.5/forecast/daily?units=metric&cnt=7&apikey=" + APP_ID)
    Observable<ForecastWeather> getWeatherForecast(@Query("lon") double longitude, @Query("lat") double latitude, @Query("lang") String lang);

    @Headers({"Accept: application/json"})
    @GET("/data/2.5/weather?units=metric&apikey=" + APP_ID)
    Observable<CurrentWeather> getCurrentWeather(@Query("lon") double longitude, @Query("lat") double latitude, @Query("lang") String lang);
}
