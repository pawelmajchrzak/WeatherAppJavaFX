package com.test.model.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Forecast;
import com.test.model.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OpenWeatherMapClient implements WeatherClient{

    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";
    private double temperature;
    private String iconWeatherCode;
    private String descriptionWeather;
    private double feelsLikeTemperature;
    private String hourAndMinutes;
    private double probabilityRain;
    private String forecastDateTime;


    @Override
    public Weather getWeather(String cityName, String countryName) {
        String countryCode = getCountryCode(countryName);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        hourAndMinutes = currentTime.format(timeFormat);

        ResponseEntity<String> weatherNow = callGetMethod("weather",cityName,countryCode,Config.API_KEY);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(weatherNow.getBody());
            descriptionWeather = jsonNode.get("weather").get(0).get("description").asText();
            iconWeatherCode = jsonNode.get("weather").get(0).get("icon").asText();
            temperature = jsonNode.get("main").get("temp").asDouble();
            feelsLikeTemperature = jsonNode.get("main").get("feels_like").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Weather(temperature, hourAndMinutes, iconWeatherCode, descriptionWeather, feelsLikeTemperature,cityName);
    }

    @Override
    public List<Forecast> getForecast(String cityName, String countryName) {
        String countryCode = getCountryCode(countryName);
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] formattedDateTimes = new String[5];

        for (int i = 0; i < 5; i++) {
            formattedDateTimes[i] = currentTime.format(formatter);
            currentTime = currentTime.plusDays(1);
        }

        ResponseEntity<String> weatherForecast = callGetMethod("forecast",cityName,countryCode,Config.API_KEY);
        ObjectMapper objectMapperForecast = new ObjectMapper();
        List<Forecast> forecastList = new ArrayList<>();

        try {
            JsonNode jsonNode = objectMapperForecast.readTree(weatherForecast.getBody());
            for (int i =0; i<8; i++) {
                JsonNode forecastNode = jsonNode.get("list").get(i);
                temperature = Math.round(forecastNode.get("main").get("temp").asDouble());
                iconWeatherCode = forecastNode.get("weather").get(0).get("icon").asText();
                probabilityRain = Math.round(forecastNode.get("pop").asDouble()*100);
                forecastDateTime = formatWithTodayYesterdayHourAndMinutes(forecastNode.get("dt_txt").asText());
                forecastList.add(new Forecast(temperature, forecastDateTime, iconWeatherCode, probabilityRain));
            }

            for (JsonNode forecastNode : jsonNode.get("list")) {
                forecastDateTime = forecastNode.get("dt_txt").asText();
                String polishDayOfWeekName = dayOfWeekFromDateTime(forecastDateTime);

                if (isNextDaysForecast(forecastDateTime, formattedDateTimes) && (forecastDateTime.contains("03:00:00") || forecastDateTime.contains("15:00:00"))) {
                    temperature = Math.round(forecastNode.get("main").get("temp").asDouble());
                    iconWeatherCode = forecastNode.get("weather").get(0).get("icon").asText();
                    probabilityRain = Math.round(forecastNode.get("pop").asDouble()*100);
                    forecastList.add(new Forecast(temperature, polishDayOfWeekName, iconWeatherCode, probabilityRain));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecastList;
    }

    @Override
    public boolean isCityAndCountryValid(String cityName, String countryName) {
        String countryCode = getCountryCode(countryName);
        try {
            // Wywołaj metodę zapytania o pogodę dla danej nazwy miasta
            ResponseEntity<String> response = callGetMethod("weather", cityName, countryCode, Config.API_KEY);

            // Sprawdź, czy odpowiedź jest poprawna
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            // Obsłuż ewentualne błędy
            e.printStackTrace();
            return false;
        }
    }

    private String dayOfWeekFromDateTime(String forecastDateTime) {
        DateTimeFormatter formatterFromApi = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(forecastDateTime,formatterFromApi);
        DayOfWeek dayOfWeekName = dateTime.getDayOfWeek();
        return PolishDayOfWeekConverter.convertToPolish(dayOfWeekName);
    }

    private ResponseEntity<String> callGetMethod (Object...objects) {
        return restTemplate.getForEntity(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
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

    private boolean isNextDaysForecast(String forecastDateTime, String[] formattedDateTimes) {
        for (String formattedDateTime : formattedDateTimes) {
            if (forecastDateTime.contains(formattedDateTime)) {
                return true;
            }
        }
        return false;
    }

    public static String getCountryCode(String countryName) {
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            if (locale.getDisplayCountry().equalsIgnoreCase(countryName)) {
                return countryCode;
            }
        }
        return null;
    }


}
