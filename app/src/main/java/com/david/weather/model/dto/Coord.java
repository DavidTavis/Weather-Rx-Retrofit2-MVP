package com.david.weather.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    @Expose
    public float lon;
    @SerializedName("lat")
    @Expose
    public float lat;

    /**
     * No args constructor for use in serialization
     *
     */
    public Coord() {
    }

    /**
     *
     * @param lon
     * @param lat
     */
    public Coord(float lon, float lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}