package com.test.model.client;

import com.test.model.Weather;
import com.test.model.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class OpenWeatherMapClientTest{

    @Mock
    private WeatherClient weatherClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void shouldReturnWeatherMockInside() {
//
//    }

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

    private Weather createWeather() {
        return new Weather(10, "12:00", "10e", "Słonecznie", 11, "Gdańsk");
    }
    private Weather createForecastHourly() {
        return new Weather(10, "Jutro 09:00", "10e", 0.80);
    }
    private Weather createForecastDaily() {
        return new Weather(10, 2,"Sobota", "10e");
    }
}