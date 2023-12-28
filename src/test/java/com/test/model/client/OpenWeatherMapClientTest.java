package com.test.model.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void shouldReturnWeather() {
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

    @Test
    void shouldReturnForecastHourly() {
        //given
        String preparedAnswer = createForecast();
        when(restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
                String.class, "forecast",cityName,countryCode,Config.API_KEY))
                .thenReturn(preparedAnswer);
        //when
        List<Weather> result = weatherClient.getForecastHourly("Gdańsk", "Polska");
        //then
        assertThat(result.get(0).getTemperature()).isEqualTo(8.0);
        assertThat(result.get(0).getIconWeatherCode()).isEqualTo("03n");
        assertThat(result.get(0).getProbabilityRain()).isEqualTo(8);
        assertThat(result.get(2).getTemperature()).isEqualTo(7.0);
        assertThat(result.get(2).getIconWeatherCode()).isEqualTo("04n");
        assertThat(result.get(2).getProbabilityRain()).isEqualTo(0);
        assertThat(result.get(5).getTemperature()).isEqualTo(8.0);
        assertThat(result.get(5).getIconWeatherCode()).isEqualTo("10d");
        assertThat(result.get(5).getProbabilityRain()).isEqualTo(78);
    }

    @Test
    void shouldReturnForecastDaily() {
        //given
        OpenWeatherMapClient weatherClientDaily = new OpenWeatherMapClient(restTemplate, objectMapper);
        OpenWeatherMapClient weatherClientSpy = spy(weatherClientDaily);
        String preparedAnswer = createForecast();

        doReturn(new String[]{"2023-12-29", "2023-12-30", "2023-12-31", "2024-01-01"})
                .when(weatherClientSpy)
                .generateFormattedDates(4);
        doReturn(preparedAnswer)
                .when(weatherClientSpy)
                .callGetMethodString("forecast",cityName,countryCode,Config.API_KEY);

        //when
        List<Weather> result = weatherClientSpy.getForecastDaily("Gdańsk", "Polska");
        //then
        assertThat(result.get(0).getTemperature()).isEqualTo(8.0);
        assertThat(result.get(0).getIconWeatherCode()).isEqualTo("04n");
        assertThat(result.get(0).getTemperatureNight()).isEqualTo(7.0);
        assertThat(result.get(2).getTemperature()).isEqualTo(2.0);
        assertThat(result.get(2).getIconWeatherCode()).isEqualTo("04n");
        assertThat(result.get(2).getTemperatureNight()).isEqualTo(3.0);
    }

    @Test
    void shouldThrowExceptionWhenWeatherNotAvailable() {
        //given
        when(restTemplate.getForObject(WEATHER_URL + "{typeOfWeather}?q={cityName},{countryCode}&appid={apiKey}&units=metric&lang=pl",
                String.class, "weather", cityName, countryCode, Config.API_KEY))
                .thenReturn(null);

        //when, then
        assertThrows(RuntimeException.class, () -> {
            weatherClient.getWeather("Gdańsk", "Polska");
        });
    }

    @Test
    void shouldReturnNextFourDays() {
        //given
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentTime = currentTime.plusDays(1);
        String firstFormattedData = currentTime.format(formatter);
        //when
        String[] result = weatherClient.generateFormattedDates(4);
        //then
        assertThat(result[0]).isEqualTo(firstFormattedData);
    }

    private String createForecast() {
        return "{\"cod\":\"200\",\"message\":0,\"cnt\":40,\"list\":[{\"dt\":1703797200,\"main\":{\"temp\":7.64,\"feels_like\":3.62,\"temp_min\":7.64,\"temp_max\":8,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1006,\"humidity\":79,\"temp_kf\":-0.36},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"zachmurzenie małe\",\"icon\":\"03n\"}],\"clouds\":{\"all\":40},\"wind\":{\"speed\":7.96,\"deg\":235,\"gust\":14.54},\"visibility\":10000,\"pop\":0.08,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-28 21:00:00\"},{\"dt\":1703808000,\"main\":{\"temp\":7.52,\"feels_like\":3.78,\"temp_min\":7.28,\"temp_max\":7.52,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1006,\"humidity\":80,\"temp_kf\":0.24},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":60},\"wind\":{\"speed\":6.92,\"deg\":230,\"gust\":13.03},\"visibility\":10000,\"pop\":0.08,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 00:00:00\"},{\"dt\":1703818800,\"main\":{\"temp\":6.85,\"feels_like\":2.69,\"temp_min\":6.46,\"temp_max\":6.85,\"pressure\":1005,\"sea_level\":1005,\"grnd_level\":1004,\"humidity\":83,\"temp_kf\":0.39},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":7.63,\"deg\":223,\"gust\":14.32},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 03:00:00\"},{\"dt\":1703829600,\"main\":{\"temp\":6.85,\"feels_like\":2.71,\"temp_min\":6.85,\"temp_max\":6.85,\"pressure\":1004,\"sea_level\":1004,\"grnd_level\":1003,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":7.57,\"deg\":224,\"gust\":14.09},\"visibility\":10000,\"pop\":0.27,\"rain\":{\"3h\":0.23},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 06:00:00\"},{\"dt\":1703840400,\"main\":{\"temp\":7.39,\"feels_like\":3.13,\"temp_min\":7.39,\"temp_max\":7.39,\"pressure\":1004,\"sea_level\":1004,\"grnd_level\":1003,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":8.53,\"deg\":230,\"gust\":14.74},\"visibility\":10000,\"pop\":0.55,\"rain\":{\"3h\":0.25},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-29 09:00:00\"},{\"dt\":1703851200,\"main\":{\"temp\":7.72,\"feels_like\":3.46,\"temp_min\":7.72,\"temp_max\":7.72,\"pressure\":1003,\"sea_level\":1003,\"grnd_level\":1002,\"humidity\":81,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":8.91,\"deg\":256,\"gust\":16.28},\"visibility\":10000,\"pop\":0.78,\"rain\":{\"3h\":2.14},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-29 12:00:00\"},{\"dt\":1703862000,\"main\":{\"temp\":7.56,\"feels_like\":3.53,\"temp_min\":7.56,\"temp_max\":7.56,\"pressure\":1004,\"sea_level\":1004,\"grnd_level\":1003,\"humidity\":72,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":83},\"wind\":{\"speed\":7.88,\"deg\":254,\"gust\":14.31},\"visibility\":10000,\"pop\":0.24,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 15:00:00\"},{\"dt\":1703872800,\"main\":{\"temp\":6.68,\"feels_like\":2.85,\"temp_min\":6.68,\"temp_max\":6.68,\"pressure\":1003,\"sea_level\":1003,\"grnd_level\":1003,\"humidity\":81,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":67},\"wind\":{\"speed\":6.47,\"deg\":217,\"gust\":12.8},\"visibility\":10000,\"pop\":0.08,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 18:00:00\"},{\"dt\":1703883600,\"main\":{\"temp\":6.28,\"feels_like\":2.69,\"temp_min\":6.28,\"temp_max\":6.28,\"pressure\":1002,\"sea_level\":1002,\"grnd_level\":1002,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"umiarkowane opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":98},\"wind\":{\"speed\":5.58,\"deg\":243,\"gust\":11.15},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":5.9},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-29 21:00:00\"},{\"dt\":1703894400,\"main\":{\"temp\":6.45,\"feels_like\":1.89,\"temp_min\":6.45,\"temp_max\":6.45,\"pressure\":1002,\"sea_level\":1002,\"grnd_level\":1001,\"humidity\":74,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":78},\"wind\":{\"speed\":8.58,\"deg\":246,\"gust\":15.8},\"visibility\":10000,\"pop\":0.92,\"rain\":{\"3h\":0.1},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 00:00:00\"},{\"dt\":1703905200,\"main\":{\"temp\":6.42,\"feels_like\":1.92,\"temp_min\":6.42,\"temp_max\":6.42,\"pressure\":1002,\"sea_level\":1002,\"grnd_level\":1001,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"zachmurzenie małe\",\"icon\":\"03n\"}],\"clouds\":{\"all\":29},\"wind\":{\"speed\":8.33,\"deg\":248,\"gust\":14.99},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 03:00:00\"},{\"dt\":1703916000,\"main\":{\"temp\":5.83,\"feels_like\":2.25,\"temp_min\":5.83,\"temp_max\":5.83,\"pressure\":1002,\"sea_level\":1002,\"grnd_level\":1002,\"humidity\":80,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":65},\"wind\":{\"speed\":5.27,\"deg\":256,\"gust\":10.32},\"visibility\":10000,\"pop\":0.12,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 06:00:00\"},{\"dt\":1703926800,\"main\":{\"temp\":5.91,\"feels_like\":1.33,\"temp_min\":5.91,\"temp_max\":5.91,\"pressure\":1004,\"sea_level\":1004,\"grnd_level\":1003,\"humidity\":77,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04d\"}],\"clouds\":{\"all\":99},\"wind\":{\"speed\":8.07,\"deg\":264,\"gust\":14},\"visibility\":10000,\"pop\":0.16,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-30 09:00:00\"},{\"dt\":1703937600,\"main\":{\"temp\":5.04,\"feels_like\":-0.16,\"temp_min\":5.04,\"temp_max\":5.04,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1005,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":9.27,\"deg\":274,\"gust\":15.14},\"visibility\":10000,\"pop\":0.65,\"rain\":{\"3h\":0.56},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-30 12:00:00\"},{\"dt\":1703948400,\"main\":{\"temp\":5.18,\"feels_like\":0.16,\"temp_min\":5.18,\"temp_max\":5.18,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1007,\"humidity\":72,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04n\"}],\"clouds\":{\"all\":67},\"wind\":{\"speed\":8.81,\"deg\":276,\"gust\":13.96},\"visibility\":10000,\"pop\":0.09,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 15:00:00\"},{\"dt\":1703959200,\"main\":{\"temp\":4.39,\"feels_like\":-0.68,\"temp_min\":4.39,\"temp_max\":4.39,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1009,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"zachmurzenie małe\",\"icon\":\"03n\"}],\"clouds\":{\"all\":46},\"wind\":{\"speed\":8.19,\"deg\":266,\"gust\":13.83},\"visibility\":10000,\"pop\":0.09,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 18:00:00\"},{\"dt\":1703970000,\"main\":{\"temp\":3.63,\"feels_like\":-1.27,\"temp_min\":3.63,\"temp_max\":3.63,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1011,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"bezchmurnie\",\"icon\":\"01n\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":7.07,\"deg\":262,\"gust\":12.91},\"visibility\":10000,\"pop\":0.04,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-30 21:00:00\"},{\"dt\":1703980800,\"main\":{\"temp\":3.3,\"feels_like\":-1.19,\"temp_min\":3.3,\"temp_max\":3.3,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1012,\"humidity\":86,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"pochmurnie\",\"icon\":\"02n\"}],\"clouds\":{\"all\":11},\"wind\":{\"speed\":5.84,\"deg\":271,\"gust\":10.77},\"visibility\":10000,\"pop\":0.07,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 00:00:00\"},{\"dt\":1703991600,\"main\":{\"temp\":2.61,\"feels_like\":-1.27,\"temp_min\":2.61,\"temp_max\":2.61,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1012,\"humidity\":88,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"zachmurzenie małe\",\"icon\":\"03n\"}],\"clouds\":{\"all\":31},\"wind\":{\"speed\":4.32,\"deg\":279,\"gust\":7.68},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 03:00:00\"},{\"dt\":1704002400,\"main\":{\"temp\":2.18,\"feels_like\":-0.33,\"temp_min\":2.18,\"temp_max\":2.18,\"pressure\":1014,\"sea_level\":1014,\"grnd_level\":1013,\"humidity\":88,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"zachmurzenie małe\",\"icon\":\"03n\"}],\"clouds\":{\"all\":40},\"wind\":{\"speed\":2.39,\"deg\":276,\"gust\":2.74},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 06:00:00\"},{\"dt\":1704013200,\"main\":{\"temp\":2.62,\"feels_like\":1.34,\"temp_min\":2.62,\"temp_max\":2.62,\"pressure\":1014,\"sea_level\":1014,\"grnd_level\":1014,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04d\"}],\"clouds\":{\"all\":65},\"wind\":{\"speed\":1.43,\"deg\":178,\"gust\":1.58},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-31 09:00:00\"},{\"dt\":1704024000,\"main\":{\"temp\":2.95,\"feels_like\":0.29,\"temp_min\":2.95,\"temp_max\":2.95,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1012,\"humidity\":86,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"zachmurzenie umiarkowane\",\"icon\":\"04d\"}],\"clouds\":{\"all\":83},\"wind\":{\"speed\":2.7,\"deg\":140,\"gust\":3.63},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-12-31 12:00:00\"},{\"dt\":1704034800,\"main\":{\"temp\":2.39,\"feels_like\":-1.15,\"temp_min\":2.39,\"temp_max\":2.39,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1011,\"humidity\":89,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.7,\"deg\":121,\"gust\":6.33},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 15:00:00\"},{\"dt\":1704045600,\"main\":{\"temp\":2.33,\"feels_like\":-1.83,\"temp_min\":2.33,\"temp_max\":2.33,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1010,\"humidity\":87,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.69,\"deg\":117,\"gust\":8.84},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 18:00:00\"},{\"dt\":1704056400,\"main\":{\"temp\":2.45,\"feels_like\":-1.78,\"temp_min\":2.45,\"temp_max\":2.45,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1009,\"humidity\":87,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.87,\"deg\":126,\"gust\":9.92},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2023-12-31 21:00:00\"},{\"dt\":1704067200,\"main\":{\"temp\":2.38,\"feels_like\":-2.52,\"temp_min\":2.38,\"temp_max\":2.38,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1007,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":6.2,\"deg\":124,\"gust\":10.49},\"visibility\":10000,\"pop\":0.5,\"rain\":{\"3h\":0.98},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 00:00:00\"},{\"dt\":1704078000,\"main\":{\"temp\":2.45,\"feels_like\":-1.57,\"temp_min\":2.45,\"temp_max\":2.45,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1006,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.48,\"deg\":141,\"gust\":7.67},\"visibility\":8590,\"pop\":0.9,\"rain\":{\"3h\":1.42},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 03:00:00\"},{\"dt\":1704088800,\"main\":{\"temp\":2.72,\"feels_like\":-0.34,\"temp_min\":2.72,\"temp_max\":2.72,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1005,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.13,\"deg\":163,\"gust\":4.92},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":2.03},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 06:00:00\"},{\"dt\":1704099600,\"main\":{\"temp\":3.99,\"feels_like\":1.91,\"temp_min\":3.99,\"temp_max\":3.99,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1006,\"humidity\":90,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.28,\"deg\":234,\"gust\":4.21},\"visibility\":10000,\"pop\":0.39,\"rain\":{\"3h\":0.1},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-01 09:00:00\"},{\"dt\":1704110400,\"main\":{\"temp\":4.88,\"feels_like\":3.95,\"temp_min\":4.88,\"temp_max\":4.88,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1005,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.4,\"deg\":210,\"gust\":2.13},\"visibility\":10000,\"pop\":0.25,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-01 12:00:00\"},{\"dt\":1704121200,\"main\":{\"temp\":4.43,\"feels_like\":2.54,\"temp_min\":4.43,\"temp_max\":4.43,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1006,\"humidity\":88,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.16,\"deg\":206,\"gust\":2.66},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 15:00:00\"},{\"dt\":1704132000,\"main\":{\"temp\":4.32,\"feels_like\":2.52,\"temp_min\":4.32,\"temp_max\":4.32,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1006,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.05,\"deg\":218,\"gust\":3.17},\"visibility\":10000,\"pop\":0.27,\"rain\":{\"3h\":0.2},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 18:00:00\"},{\"dt\":1704142800,\"main\":{\"temp\":4.38,\"feels_like\":3.24,\"temp_min\":4.38,\"temp_max\":4.38,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1007,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.5,\"deg\":209,\"gust\":1.84},\"visibility\":10000,\"pop\":0.28,\"rain\":{\"3h\":0.37},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-01 21:00:00\"},{\"dt\":1704153600,\"main\":{\"temp\":4.12,\"feels_like\":2.52,\"temp_min\":4.12,\"temp_max\":4.12,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1008,\"humidity\":95,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.84,\"deg\":221,\"gust\":3.16},\"visibility\":10000,\"pop\":0.79,\"rain\":{\"3h\":0.86},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-02 00:00:00\"},{\"dt\":1704164400,\"main\":{\"temp\":4.12,\"feels_like\":2.09,\"temp_min\":4.12,\"temp_max\":4.12,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1008,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.25,\"deg\":196,\"gust\":3.39},\"visibility\":10000,\"pop\":0.73,\"rain\":{\"3h\":0.71},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-02 03:00:00\"},{\"dt\":1704175200,\"main\":{\"temp\":4.53,\"feels_like\":2.11,\"temp_min\":4.53,\"temp_max\":4.53,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1009,\"humidity\":94,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":2.78,\"deg\":203,\"gust\":4.78},\"visibility\":10000,\"pop\":0.89,\"rain\":{\"3h\":0.88},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-02 06:00:00\"},{\"dt\":1704186000,\"main\":{\"temp\":4.64,\"feels_like\":2.04,\"temp_min\":4.64,\"temp_max\":4.64,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1009,\"humidity\":92,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.05,\"deg\":185,\"gust\":4.41},\"visibility\":10000,\"pop\":0.43,\"rain\":{\"3h\":0.76},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-02 09:00:00\"},{\"dt\":1704196800,\"main\":{\"temp\":4.7,\"feels_like\":1.81,\"temp_min\":4.7,\"temp_max\":4.7,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1008,\"humidity\":90,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.48,\"deg\":168,\"gust\":5.2},\"visibility\":10000,\"pop\":0.31,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-02 12:00:00\"},{\"dt\":1704207600,\"main\":{\"temp\":4.29,\"feels_like\":0.46,\"temp_min\":4.29,\"temp_max\":4.29,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1008,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"zachmurzenie duże\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.98,\"deg\":154,\"gust\":7.83},\"visibility\":10000,\"pop\":0.03,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-02 15:00:00\"},{\"dt\":1704218400,\"main\":{\"temp\":4.09,\"feels_like\":-0.66,\"temp_min\":4.09,\"temp_max\":4.09,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1006,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"słabe opady deszczu\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":7.02,\"deg\":151,\"gust\":11.2},\"visibility\":6540,\"pop\":0.28,\"rain\":{\"3h\":0.41},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-02 18:00:00\"}],\"city\":{\"id\":3099434,\"name\":\"Gdańsk\",\"coord\":{\"lat\":54.3521,\"lon\":18.6464},\"country\":\"PL\",\"population\":461865,\"timezone\":3600,\"sunrise\":1703747194,\"sunset\":1703773599}}";

    }

}