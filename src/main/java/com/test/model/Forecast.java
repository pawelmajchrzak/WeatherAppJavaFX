package com.test.model;

import java.time.LocalDate;

public class Forecast {

    private final String cityName;
    private final double temperature;
    private final String dateTime;
    private final String iconWeatherCode;
    private final double probabilityRain;

    public Forecast(String cityName, double temperature, String dateTime, String iconWeatherCode, double probabilityRain) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.dateTime = dateTime;
        this.iconWeatherCode = iconWeatherCode;
        this.probabilityRain = probabilityRain;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }



    public String getIconWeatherCode() {
        return iconWeatherCode;
    }

    public double getProbabilityRain() {
        return probabilityRain;
    }

    public String getDateTime() {
        return dateTime;
    }
}
