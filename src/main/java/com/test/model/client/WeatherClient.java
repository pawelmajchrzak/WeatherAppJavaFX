package com.test.model.client;

import com.test.model.Forecast;
import com.test.model.Weather;

import java.util.List;

public interface WeatherClient {
    Weather getWeather(String cityName);

    List<Forecast> getForecast(String cityName);

    boolean isCityValid(String cityName);
}
