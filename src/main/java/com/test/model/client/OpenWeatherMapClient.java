package com.test.model.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Weather;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class OpenWeatherMapClient implements WeatherClient{

    private final RestTemplate restTemplate;
    ObjectMapper objectMapper;
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    public OpenWeatherMapClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Weather getWeather(String cityName, String countryName) {
        double temperature=0;
        String hourAndMinutes;
        String iconWeatherCode="";
        String descriptionWeather="";
        double feelsLikeTemperature=0;
        String weatherNow = null;

        String countryCode = getCountryCode(countryName);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        hourAndMinutes = LocalDateTime.now().format(timeFormat);

        try {
            weatherNow = restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
                    String.class, "weather",cityName,countryCode,Config.API_KEY);
            if (weatherNow == null) {
                throw new NullPointerException("No weather found for this city");
            }
            JsonNode jsonNode = objectMapper.readTree(weatherNow);
            descriptionWeather = jsonNode.get("weather").get(0).get("description").asText();
            iconWeatherCode = jsonNode.get("weather").get(0).get("icon").asText();
            temperature = jsonNode.get("main").get("temp").asDouble();
            feelsLikeTemperature = jsonNode.get("main").get("feels_like").asDouble();
            System.out.println(temperature);
            System.out.println(hourAndMinutes);
            System.out.println(iconWeatherCode);
            System.out.println(iconWeatherCode);
            System.out.println(descriptionWeather);
            System.out.println(feelsLikeTemperature);
            System.out.println(cityName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NullPointerException("No weather found for this city");
        }
        return new Weather(temperature, hourAndMinutes, iconWeatherCode, descriptionWeather, feelsLikeTemperature,cityName);
    }
    @Override
    public List<Weather> getForecastHourly(String cityName, String countryName) {
        double temperature;
        String forecastDateTime;
        String iconWeatherCode;
        double probabilityRain;
        String weatherForecast=null;

        String countryCode = getCountryCode(countryName);
        List<Weather> forecastList = new ArrayList<>();

        try {
            weatherForecast = callGetMethod(String.class,"forecast",cityName,countryCode,Config.API_KEY);
            JsonNode jsonNode = objectMapper.readTree(weatherForecast);
            for (int i =0; i<8; i++) {
                JsonNode forecastNode = jsonNode.get("list").get(i);
                temperature = Math.round(forecastNode.get("main").get("temp").asDouble());
                iconWeatherCode = forecastNode.get("weather").get(0).get("icon").asText();
                probabilityRain = Math.round(forecastNode.get("pop").asDouble()*100);
                forecastDateTime = formatWithTodayYesterdayHourAndMinutes(forecastNode.get("dt_txt").asText());
                forecastList.add(new Weather(temperature, forecastDateTime, iconWeatherCode, probabilityRain));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecastList;
    }

    @Override
    public List<Weather> getForecastDaily(String cityName, String countryName) {
        double temperature;
        double temperatureNight;
        String dateTime;
        String iconWeatherCode;
        String weatherForecast;

        String countryCode = getCountryCode(countryName);
        String[] formattedDateTimes = generateFormattedDates(4);
        List<Weather> forecastList = new ArrayList<>();

        try {
            weatherForecast = callGetMethod(String.class,"forecast",cityName,countryCode,Config.API_KEY);

            JsonNode jsonNode = objectMapper.readTree(weatherForecast);
            Iterator<JsonNode> forecastNodeIterator = jsonNode.get("list").iterator();

            while (forecastNodeIterator.hasNext()) {
                JsonNode currentForecastNode = forecastNodeIterator.next();
                dateTime = currentForecastNode.get("dt_txt").asText();
                String polishDayOfWeekName = dayOfWeekFromDateTime(dateTime);
                if (isNextDaysForecast(dateTime, formattedDateTimes) && (dateTime.contains("03:00:00"))) {
                    temperatureNight = Math.round(currentForecastNode.get("main").get("temp").asDouble());
                    for (int i = 0; i < 4 && forecastNodeIterator.hasNext(); i++) {
                        currentForecastNode = forecastNodeIterator.next();
                    }
                    temperature = Math.round(currentForecastNode.get("main").get("temp").asDouble());
                    iconWeatherCode = currentForecastNode.get("weather").get(0).get("icon").asText();
                    forecastList.add(new Weather(temperature, temperatureNight, polishDayOfWeekName, iconWeatherCode));
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
            String responseBody = callGetMethod(String.class,"weather", cityName, countryCode, Config.API_KEY);
            return true;
        } catch (Exception e) {
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

    private <T> T callGetMethod (Class <T> responseType,Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
                responseType, objects);
//        String answer = "{\"weather\":[{\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"main\":{\"temp\":2.78,\"feels_like\":-0.01}}";
//        return answer;
    }

    public String callGetMethodString (Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
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

    private String[] generateFormattedDates(int days) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] formattedDateTimes = new String[days];

        for (int i = 0; i < days; i++) {
            currentTime = currentTime.plusDays(1);
            formattedDateTimes[i] = currentTime.format(formatter);
        }

        return formattedDateTimes;
    }

}