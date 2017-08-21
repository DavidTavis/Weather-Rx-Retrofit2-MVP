package com.david.weather.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityEntity {

    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("pressure")
    @Expose
    public float pressure;
    @SerializedName("humidity")
    @Expose
    public int humidity;
    @SerializedName("weather")
    @Expose
    public java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    public float speed;
    @SerializedName("deg")
    @Expose
    public int deg;
    @SerializedName("clouds")
    @Expose
    public int clouds;
    @SerializedName("rain")
    @Expose
    public float rain;

    /**
     * No args constructor for use in serialization
     *
     */
    public CityEntity() {
    }

    /**
     *
     * @param clouds
     * @param dt
     * @param humidity
     * @param pressure
     * @param speed
     * @param deg
     * @param weather
     * @param temp
     * @param rain
     */
    public CityEntity(int dt, Temp temp, float pressure, int humidity, java.util.List<Weather> weather, int speed, int deg, int clouds, float rain) {
        super();
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", weather=" + weather +
                ", speed=" + speed +
                ", deg=" + deg +
                ", clouds=" + clouds +
                ", rain=" + rain +
                '}';
    }
}
