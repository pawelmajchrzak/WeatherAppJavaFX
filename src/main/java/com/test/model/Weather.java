package com.test.model;

import java.time.LocalDate;

public class Weather {

    private final String cityName;
    private final double currentTemperature;
    private final LocalDate date;
    private final String time;
    private final String iconWeatherCode;

    private final String descriptionWeather;

    private final double feelsLikeTemperature;

    public Weather(String cityName, double currentTemperature, LocalDate date, String time,
                   String iconWeatherCode, String descriptionWeather, double feelsLikeTemperature) {
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.date = date;
        this.time = time;
        this.iconWeatherCode = iconWeatherCode;
        this.descriptionWeather = descriptionWeather;
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public String getCityName() {
        return cityName;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public LocalDate getDate() {
        return date;
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
