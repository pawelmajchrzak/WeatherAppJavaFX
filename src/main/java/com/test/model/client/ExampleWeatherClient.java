package com.test.model.client;

import com.test.model.Weather;

import java.time.LocalDate;
//import org.apache.http.client.HttpClient;

public class ExampleWeatherClient implements WeatherClient{
    @Override
    public Weather getWeather(String cityName) {

        //HttpClient httpClient = HttpClients.createDefault();






        return new Weather(cityName,10, LocalDate.now());
    }
}
