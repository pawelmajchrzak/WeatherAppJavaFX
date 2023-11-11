package com.test.model.client;

import com.test.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExampleWeatherClient implements WeatherClient{


    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    double temperatureCelsius;

    @Override
    public Weather getWeather(String cityName) {

//        String weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
//        String weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);

        ResponseEntity<String> weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(weatherNow.getBody());
            JsonNode mainNode = jsonNode.get("main");
            temperatureCelsius = mainNode.get("temp").asDouble();

            System.out.println("Temperatura teraz w " + cityName + ": " + temperatureCelsius + " stopni Celsiusza");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);
        ObjectMapper objectMapperForecast = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapperForecast.readTree(weatherForecast.getBody());
            JsonNode forecastNode = jsonNode.get("list").get(0);
            double temperatureCelsiusForeCast = forecastNode.get("main").get("temp").asDouble();

            System.out.println("Prognozowana temperatura na kolejne godziny w " + cityName + ": " + temperatureCelsiusForeCast + " stopni Celsiusza");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(temperatureCelsius);
        //System.out.println(weatherForecast);

        return new Weather(cityName, temperatureCelsius, LocalDate.now());
    }

    private ResponseEntity<String> callGetMethod (Object...objects) {
        return restTemplate.getForEntity(WEATHER_URL + "{typeOfWeather}?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                String.class, objects);
    }


}
