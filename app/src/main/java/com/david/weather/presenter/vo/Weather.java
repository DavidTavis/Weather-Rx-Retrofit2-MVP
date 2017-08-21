package com.david.weather.presenter.vo;

/**
 * Created by TechnoA on 16.08.2017.
 */

public class Weather extends Forecast {

    private final float mTemperature;  // Current temperature.
    private String time;

    public Weather(String location, long day, String description, double maxTemp, double minTemp, float temperature, String time) {
        super(location, day, description, maxTemp, minTemp);
        mTemperature = temperature;
        this.time = time;

    }

    public float getTemperature() {
        return mTemperature;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "mTemperature=" + mTemperature +
                '}';
    }
}
