package com.test.model.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Forecast;
import com.test.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExampleWeatherClientTest implements WeatherClient{


    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    double temperatureCelsius;

    @Override
    public Weather getWeather(String cityName) {

        String descriptionWeather = null;
        String iconWeatherCode = null;
        double feelsLikeTemperature = 0;
        String time = null;

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        time = currentTime.format(timeFormat);

        ResponseEntity<String> weatherNow = callGetMethod("weather",cityName,Config.API_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(weatherNow.getBody());

            descriptionWeather = jsonNode.get("weather").get(0).get("description").asText();
            iconWeatherCode = jsonNode.get("weather").get(0).get("icon").asText();
            temperatureCelsius = jsonNode.get("main").get("temp").asDouble();
            feelsLikeTemperature = jsonNode.get("main").get("feels_like").asDouble();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Weather(cityName, temperatureCelsius, LocalDate.now(), time,
                iconWeatherCode, descriptionWeather, feelsLikeTemperature);
    }

    @Override
    public List<Forecast> getForecast(String cityName) {

        // Pobierz aktualny czas
        LocalDateTime currentTime = LocalDateTime.now();

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

        ResponseEntity<String> weatherForecast = callGetMethod("forecast",cityName,Config.API_KEY);
        ObjectMapper objectMapperForecast = new ObjectMapper();
        List<Forecast> forecastList = new ArrayList<>();
        double temperature = 0;
        String iconWeatherCode = null;
        double probabilityRain = 0;
        String forecastDateTime = null;
        try {
            JsonNode jsonNode = objectMapperForecast.readTree(weatherForecast.getBody());
            for (int i =0; i<8; i++) {
                JsonNode forecastNode = jsonNode.get("list").get(i);
                temperature = Math.round(forecastNode.get("main").get("temp").asDouble());
                iconWeatherCode = forecastNode.get("weather").get(0).get("icon").asText();
                probabilityRain = Math.round(forecastNode.get("pop").asDouble()*100);
                forecastDateTime = formatWithTodayYesterdayHourAndMinutes(forecastNode.get("dt_txt").asText());
                forecastList.add(new Forecast(cityName, temperature, forecastDateTime, iconWeatherCode, probabilityRain));
            }

            for (JsonNode forecastNode : jsonNode.get("list")) {
                forecastDateTime = forecastNode.get("dt_txt").asText();


                DateTimeFormatter formatterFromApi = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(forecastDateTime,formatterFromApi);
                DayOfWeek dayOfWeekName = dateTime.getDayOfWeek();
                String polishDayOfWeekName = PolishDayOfWeekConverter.convertToPolish(dayOfWeekName);


                if ((
                        forecastDateTime.contains(formattedDateTimeNextFirstDay)||
                        forecastDateTime.contains(formattedDateTimeNextSecondDay)||
                        forecastDateTime.contains(formattedDateTimeNextThirdDay)||
                        forecastDateTime.contains(formattedDateTimeNextFourthDay))
                        &&
                        (forecastDateTime.contains("03:00:00") || forecastDateTime.contains("15:00:00"))) {

                    temperature = Math.round(forecastNode.get("main").get("temp").asDouble());
                    iconWeatherCode = forecastNode.get("weather").get(0).get("icon").asText();
                    probabilityRain = Math.round(forecastNode.get("pop").asDouble()*100);

                    forecastList.add(new Forecast(cityName, temperature, polishDayOfWeekName, iconWeatherCode, probabilityRain));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return forecastList;
    }

    private ResponseEntity<String> callGetMethod (Object...objects) {
        return restTemplate.getForEntity(WEATHER_URL + "{typeOfWeather}?q={cityName}&appid={apiKey}&units=metric&lang=pl",
                String.class, objects);
    }

    public static String formatWithTodayYesterdayHourAndMinutes(String dateTime) {
        String todayYesterday = "Jutro ";
        String hourAndMinutes = dateTime.substring(11,16);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = currentTime.format(formatter);

        if (dateTime.contains(formattedDateTime)) {
            todayYesterday = "Dziś ";
        }

        return todayYesterday + hourAndMinutes;
    }


}
