package com.test.model;

public class Weather {

    private double temperature;
    private String dateTime;
    private String iconWeatherCode;
    private String cityName;
    private String descriptionWeather;
    private double feelsLikeTemperature;
    private double probabilityRain;



    private double temperatureNight;

    public Weather(double temperature, String dateTime, String iconWeatherCode, String descriptionWeather, double feelsLikeTemperature, String cityName) {
        this.temperature = temperature;
        this.dateTime = dateTime;
        this.iconWeatherCode = iconWeatherCode;
        this.descriptionWeather = descriptionWeather;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.cityName = cityName;
    }

    public Weather(double temperature, String dateTime, String iconWeatherCode, double probabilityRain) {
        this.temperature = temperature;
        this.dateTime = dateTime;
        this.iconWeatherCode = iconWeatherCode;
        this.probabilityRain = probabilityRain;
    }

    public Weather(double temperature, double temperatureNight, String dateTime, String iconWeatherCode) {
        this.temperature = temperature;
        this.dateTime = dateTime;
        this.iconWeatherCode = iconWeatherCode;
        this.temperatureNight = temperatureNight;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDateTime() {
        return dateTime;
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

    public String getCityName() {
        return cityName;
    }
    public double getProbabilityRain() {
        return probabilityRain;
    }
    public double getTemperatureNight() {
        return temperatureNight;
    }


}
