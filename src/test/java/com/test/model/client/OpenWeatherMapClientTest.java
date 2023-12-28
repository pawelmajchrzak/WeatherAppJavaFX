package com.test.model.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Weather;
import com.test.model.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OpenWeatherMapClientTest{

    String cityName = "Gdańsk";
    String countryCode = "PL";
    String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";
    private OpenWeatherMapClient weatherClient;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherClient = new OpenWeatherMapClient(restTemplate, objectMapper);
    }

    @Test
    void shouldReturnWeatherMockInside() {
        //given

        String preparedAnswer = "{\"weather\":[{\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"main\":{\"temp\":2.78,\"feels_like\":-0.01}}";
        when(restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
                String.class, "weather",cityName,countryCode,Config.API_KEY))
                .thenReturn(preparedAnswer);

        //when
        Weather result = weatherClient.getWeather("Gdańsk","Polska");
        //then
        assertThat(result.getTemperature()).isEqualTo(2.78);
        assertThat(result.getIconWeatherCode()).isEqualTo("04n");
        assertThat(result.getDescriptionWeather()).isEqualTo("zachmurzenie duże");
        assertThat(result.getFeelsLikeTemperature()).isEqualTo(-0.01);
        assertThat(result.getCityName()).isEqualTo("Gdańsk");

    }


    /*
    @Mock
    private WeatherClient weatherClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void shouldReturnWeather() {
        //given
        Weather expectedCurrentWeather = createWeather();
        when(weatherClient.getWeather("Gdańsk","Polska"))
                .thenReturn(expectedCurrentWeather);
        //when
        Weather result = weatherClient.getWeather("Gdańsk","Polska");
        //then
        assertThat(result.getTemperature()).isEqualTo(expectedCurrentWeather.getTemperature());
        assertThat(result.getDateTime()).isEqualTo(expectedCurrentWeather.getDateTime());
        assertThat(result.getIconWeatherCode()).isEqualTo(expectedCurrentWeather.getIconWeatherCode());
        assertThat(result.getDescriptionWeather()).isEqualTo(expectedCurrentWeather.getDescriptionWeather());
        assertThat(result.getFeelsLikeTemperature()).isEqualTo(expectedCurrentWeather.getFeelsLikeTemperature());
        assertThat(result.getCityName()).isEqualTo(expectedCurrentWeather.getCityName());
    }

    @Test
    void shouldReturnForecastHourly() {
        //given
        List<Weather> expectedForecastList = List.of(createForecastHourly(),createForecastHourly());
        when(weatherClient.getForecastHourly("Gdańsk","Polska"))
                .thenReturn(expectedForecastList);
        //when
        List<Weather> resultForecastList  = weatherClient.getForecastHourly("Gdańsk","Polska");
        //then
        assertThat(resultForecastList).hasSize(expectedForecastList.size());

        for (int i = 0; i < expectedForecastList.size(); i++) {
            assertThat(resultForecastList.get(i).getTemperature()).isEqualTo(expectedForecastList.get(i).getTemperature());
            assertThat(resultForecastList.get(i).getDateTime()).isEqualTo(expectedForecastList.get(i).getDateTime());
            assertThat(resultForecastList.get(i).getIconWeatherCode()).isEqualTo(expectedForecastList.get(i).getIconWeatherCode());
            assertThat(resultForecastList.get(i).getProbabilityRain()).isEqualTo(expectedForecastList.get(i).getProbabilityRain());
        }
    }

    @Test
    void shouldReturnForecastDaily() {
        //given
        List<Weather> expectedForecastList = List.of(createForecastDaily(),createForecastDaily());
        when(weatherClient.getForecastDaily("Gdańsk","Polska"))
                .thenReturn(expectedForecastList);
        //when
        List<Weather> resultForecastList  = weatherClient.getForecastDaily("Gdańsk","Polska");
        //then
        assertThat(resultForecastList).hasSize(expectedForecastList.size());

        for (int i = 0; i < expectedForecastList.size(); i++) {
            assertThat(resultForecastList.get(i).getTemperature()).isEqualTo(expectedForecastList.get(i).getTemperature());
            assertThat(resultForecastList.get(i).getTemperatureNight()).isEqualTo(expectedForecastList.get(i).getTemperatureNight());
            assertThat(resultForecastList.get(i).getDateTime()).isEqualTo(expectedForecastList.get(i).getDateTime());
            assertThat(resultForecastList.get(i).getIconWeatherCode()).isEqualTo(expectedForecastList.get(i).getIconWeatherCode());
        }
    }

    @Test
    void shouldHandleFailureToRetrieveWeather() {
        // given
        when(weatherClient.getWeather("Gdańsk", "Polska"))
                .thenThrow(new RuntimeException("Failed to retrieve weather"));

        // when and then
        assertThrows(RuntimeException.class, () -> {
            weatherClient.getWeather("Gdańsk", "Polska");
        });
    }
    */
    private Weather createWeather() {
        return new Weather(10, "12:00", "10e", "Słonecznie", 11, "Gdańsk");
    }
    private Weather createForecastHourly() {
        return new Weather(10, "Jutro 09:00", "10e", 0.80);
    }
    private Weather createForecastDaily() {
        return new Weather(10, 2,"Sobota", "10e");
    }

    private String preparedAnswer() {
        String answer = "{\"coord\":{\"lon\":18.6464,\"lat\":54.3521},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":2.78,\"feels_like\":-0.01,\"temp_min\":2.27,\"temp_max\":3.13,\"pressure\":1018,\"humidity\":80},\"visibility\":10000,\"wind\":{\"speed\":2.82,\"deg\":226,\"gust\":4.49},\"clouds\":{\"all\":100},\"dt\":1703711685,\"sys\":{\"type\":2,\"id\":2089861,\"country\":\"PL\",\"sunrise\":1703660786,\"sunset\":1703687150},\"timezone\":3600,\"id\":3099434,\"name\":\"Gdańsk\",\"cod\":200}";
        return answer;
    }
}