package com.test.model.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExampleWeatherClientTest implements WeatherClient{


    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    double temperatureCelsius;

    @Override
    public Weather getWeather(String cityName) {

//        String weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
//        String weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);

        String descriptionWeather = null;
        String iconWeatherCode = null;
        double feelsLikeTemperature = 0;
        String time = null;

        // Pobierz aktualny czas
        LocalDateTime currentTime = LocalDateTime.now();
        //System.out.println(currentTime);

        // Formatuj datę do postaci, która jest akceptowana przez OpenWeatherMap (RRRR-MM-DD HH:MM:SS)
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        time = currentTime.format(timeFormat);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentTime.format(formatter);
        currentTime = currentTime.plusDays(1);
        String formattedDateTimeNextFirstDay = currentTime.format(formatter);
        currentTime = currentTime.plusDays(1);
        String formattedDateTimeNextSecondDay = currentTime.format(formatter);
        currentTime = currentTime.plusDays(1);
        String formattedDateTimeNextThirdDay = currentTime.format(formatter);
        currentTime = currentTime.plusDays(1);
        String formattedDateTimeNextFourthDay = currentTime.format(formatter);

        ResponseEntity<String> weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(weatherNow.getBody());

            // Pobierz pola z api
            descriptionWeather = jsonNode.get("weather").get(0).get("description").asText();
            iconWeatherCode = jsonNode.get("weather").get(0).get("icon").asText();
            temperatureCelsius = jsonNode.get("main").get("temp").asDouble();
            feelsLikeTemperature = jsonNode.get("main").get("feels_like").asDouble();


            System.out.println("Opis pogody teraz w " + cityName + ": " + descriptionWeather);
            System.out.println("Temperatura teraz w " + cityName + ": " + temperatureCelsius + " stopni Celsiusza");
            System.out.println("Temperatura odczuwalna w " + cityName + ": " + feelsLikeTemperature + " stopni Celsiusza");
            System.out.println("Kod obrazka: " + iconWeatherCode);
            System.out.println("Temperature pobrano o: " + time);

            // mainNode = jsonNode.get("main");
            //temperatureCelsius = mainNode.get("temp").asDouble();

            //System.out.println("Temperatura teraz w " + cityName + ": " + temperatureCelsius + " stopni Celsiusza");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);
        ObjectMapper objectMapperForecast = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapperForecast.readTree(weatherForecast.getBody());
            for (JsonNode forecastNode : jsonNode.get("list")) {
                String forecastDateTime = forecastNode.get("dt_txt").asText();
                System.out.println(forecastDateTime);
                System.out.println(formattedDateTime);
                // Sprawdź, czy prognoza jest dla właściwego dnia i godziny
                if ((forecastDateTime.contains(formattedDateTime)||
                        forecastDateTime.contains(formattedDateTimeNextFirstDay)||
                        forecastDateTime.contains(formattedDateTimeNextSecondDay)||
                        forecastDateTime.contains(formattedDateTimeNextThirdDay)||
                        forecastDateTime.contains(formattedDateTimeNextFourthDay))
                        &&
                        (forecastDateTime.contains("03:00:00") ||
                                forecastDateTime.contains("09:00:00") ||
                                forecastDateTime.contains("15:00:00") ||
                                forecastDateTime.contains("21:00:00"))) {

                    // Pobierz temperaturę z prognozy
                    double temperatureCelsius = forecastNode.get("main").get("temp").asDouble();

                    // Pobierz opis pogody
                    String weatherDescription = forecastNode.get("weather").get(0).get("description").asText();

                    // Wypisz dane prognozy
                    System.out.println("Prognoza na " + forecastDateTime + ":");
                    System.out.println("Temperatura: " + temperatureCelsius + " stopni Celsiusza");
                    System.out.println("Opis pogody: " + weatherDescription);
                    System.out.println("-------------------------------------------");
                }
                //System.out.println("tak");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(temperatureCelsius);
        //System.out.println(weatherForecast);

        //return new Weather(cityName, temperatureCelsius, LocalDate.now(), time);
        return new Weather(cityName, temperatureCelsius, LocalDate.now(), time,
                iconWeatherCode, descriptionWeather, feelsLikeTemperature);
    }

    private ResponseEntity<String> callGetMethod (Object...objects) {
        return restTemplate.getForEntity(WEATHER_URL + "{typeOfWeather}?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                String.class, objects);
    }


}
