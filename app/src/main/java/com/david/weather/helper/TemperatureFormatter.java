package com.david.weather.helper;

public class TemperatureFormatter {

    public static String format(float temperature) {
        return String.valueOf(Math.round(temperature)) + "°";
    }
}
