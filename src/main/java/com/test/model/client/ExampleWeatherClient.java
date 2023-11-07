package com.test.model.client;

import com.test.model.Weather;

import java.time.LocalDate;

public class ExampleWeatherClient implements WeatherClient{
    @Override
    public Weather getWeather(String cityName) {
        return new Weather(cityName,10, LocalDate.now());
    }
}
