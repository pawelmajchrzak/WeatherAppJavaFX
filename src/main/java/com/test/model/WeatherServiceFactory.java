package com.test.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.client.OpenWeatherMapClient;
import com.test.model.client.WeatherClient;
import org.springframework.web.client.RestTemplate;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }
    public static WeatherClient createWeatherClient() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        return new OpenWeatherMapClient(restTemplate,objectMapper);
    }
}
