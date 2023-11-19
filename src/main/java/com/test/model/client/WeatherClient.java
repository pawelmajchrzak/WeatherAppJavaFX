package com.test.model.client;

import com.test.model.Forecast;
import com.test.model.Weather;

import java.util.List;

public interface WeatherClient {
    Weather getWeather(String cityName, String countryName);

    List<Forecast> getForecast(String cityName, String countryName);

    boolean isCityAndCountryValid(String cityName, String countryName);
}
