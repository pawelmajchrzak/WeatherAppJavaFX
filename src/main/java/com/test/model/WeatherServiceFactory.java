package com.test.model;

import com.test.model.client.OpenWeatherMapClient;
import com.test.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }
    private static WeatherClient createWeatherClient() {
        return new OpenWeatherMapClient();
    }
}
