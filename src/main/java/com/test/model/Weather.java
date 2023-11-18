package com.test.model;

public class Weather {

    private final double currentTemperature;
    private final String time;
    private final String iconWeatherCode;

    private final String descriptionWeather;

    private final double feelsLikeTemperature;

    public Weather(double currentTemperature, String time, String iconWeatherCode, String descriptionWeather, double feelsLikeTemperature) {
        this.currentTemperature = currentTemperature;
        this.time = time;
        this.iconWeatherCode = iconWeatherCode;
        this.descriptionWeather = descriptionWeather;
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getTime() {
        return time;
    }

    public String getIconWeatherCode() {
        return iconWeatherCode;
    }

    public String getDescriptionWeather() {
        return descriptionWeather;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

}
