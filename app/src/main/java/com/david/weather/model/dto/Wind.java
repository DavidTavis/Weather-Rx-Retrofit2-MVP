package com.david.weather.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    public float speed;
    @SerializedName("deg")
    @Expose
    public float deg;

    /**
     * No args constructor for use in serialization
     *
     */
    public Wind() {
    }

    /**
     *
     * @param speed
     * @param deg
     */
    public Wind(float speed, float deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

}
