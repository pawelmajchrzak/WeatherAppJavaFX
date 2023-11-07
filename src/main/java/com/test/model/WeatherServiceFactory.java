package com.test.model;

import com.test.model.client.ExampleWeatherClient;
import com.test.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        return new WeatherService((WeatherClient) createWeatherService());
    }
    private static WeatherClient createWeatherClient() {
        return new ExampleWeatherClient();
    }
}
