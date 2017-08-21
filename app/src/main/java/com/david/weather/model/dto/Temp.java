package com.david.weather.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("day")
    @Expose
    public float day;
    @SerializedName("min")
    @Expose
    public float min;
    @SerializedName("max")
    @Expose
    public float max;
    @SerializedName("night")
    @Expose
    public float night;
    @SerializedName("eve")
    @Expose
    public float eve;
    @SerializedName("morn")
    @Expose
    public float morn;

    /**
     * No args constructor for use in serialization
     *
     */
    public Temp() {
    }

    /**
     *
     * @param min
     * @param eve
     * @param max
     * @param morn
     * @param night
     * @param day
     */
    public Temp(float day, float min, float max, float night, float eve, float morn) {
        super();
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "day=" + day +
                ", min=" + min +
                ", max=" + max +
                ", night=" + night +
                ", eve=" + eve +
                ", morn=" + morn +
                '}';
    }
}