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

        String weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
        String weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);

//        String responseForecast = restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName}&appid={apiKey}&units=metric&lang=pl",
//                String.class, typeOfWeather, cityName, Config.API_KEY);


        System.out.println(weatherNow);
        System.out.println(weatherForecast);

        return new Weather(cityName, 10, LocalDate.now());
    }

    private String callGetMethod (Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                String.class, objects);
    }


}
