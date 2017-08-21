package com.david.weather.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastWeather {

    @SerializedName("city")
    @Expose
    public City city;
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public float message;
    @SerializedName("cnt")
    @Expose
    public int cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<CityEntity> cityEntity = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ForecastWeather() {
    }

    /**
     *
     * @param message
     * @param cnt
     * @param cod
     * @param cityEntity
     * @param city
     */
    public ForecastWeather(City city, String cod, float message, int cnt, java.util.List<CityEntity> cityEntity) {
        super();
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.cityEntity = cityEntity;
    }

    @Override
    public String toString() {
        return "ForecastWeather{" +
                "city=" + city +
                ", cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", cityEntity=" + cityEntity +
                '}';
    }
}
