package com.test.model.client;

import com.test.model.Weather;

public interface WeatherClient {
    Weather getWeather(String cityName);
}
