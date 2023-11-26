package com.test.model;

public class Forecast {
    private final double temperature;
    private final String dateTime;
    private final String iconWeatherCode;
    private final double probabilityRain;

    public Forecast(double temperature, String dateTime, String iconWeatherCode, double probabilityRain) {

        this.temperature = temperature;
        this.dateTime = dateTime;
        this.iconWeatherCode = iconWeatherCode;
        this.probabilityRain = probabilityRain;
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
