package com.test.model.client;

import com.test.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

public class ExampleWeatherClient implements WeatherClient{


    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    @Override
    public Weather getWeather(String cityName) {


        String response = restTemplate.getForObject(WEATHER_URL + "weather?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                String.class, cityName, Config.API_KEY);





        System.out.println(response);

        return new Weather(cityName, 10, LocalDate.now());
    }
}
