package com.test.model;

import com.test.model.client.WeatherClient;

import java.util.List;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityName, String countryName) {
        return weatherClient.getWeather(cityName, countryName);
    }
    public boolean isCityAndCountryValid(String cityName, String countryName) {
        return weatherClient.isCityAndCountryValid(cityName, countryName);
    }

    public List<Forecast> getForecast(String cityName, String countryName) {
        return weatherClient.getForecast(cityName, countryName);
    }
}
