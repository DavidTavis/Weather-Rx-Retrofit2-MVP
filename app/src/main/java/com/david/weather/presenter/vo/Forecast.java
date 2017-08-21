package com.david.weather.presenter.vo;

import java.io.Serializable;

public class Forecast implements Serializable {
    private String location;
    private long day;
    private String description;
    private double maxTemp;
    private double minTemp;

    public Forecast(String location, long day, String description, double maxTemp, double minTemp) {
        this.location = location;
        this.day = day;
        this.description = description;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "location='" + location + '\'' +
                ", day=" + day +
                ", description='" + description + '\'' +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
}
