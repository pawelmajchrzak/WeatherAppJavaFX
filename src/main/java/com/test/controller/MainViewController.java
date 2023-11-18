package com.test.controller;

import com.test.CityManager;
import com.test.model.Forecast;
import com.test.model.Weather;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainViewController extends AbstractController{

    @FXML
    private Text DayOfWeek10;

    @FXML
    private Text DayOfWeek12;

    @FXML
    private Text DayOfWeek14;

    @FXML
    private Text DayOfWeek8;

    @FXML
    private Text NowTimeHourAndMinutes;

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private Label errorLabel;

    @FXML
    private Text forecastDayAndHour0;

    @FXML
    private Text forecastDayAndHour1;

    @FXML
    private Text forecastDayAndHour2;

    @FXML
    private Text forecastDayAndHour3;

    @FXML
    private Text forecastDayAndHour4;

    @FXML
    private Text forecastDayAndHour5;

    @FXML
    private Text forecastDayAndHour6;

    @FXML
    private Text forecastDayAndHour7;

    @FXML
    private ImageView forecastImage0;

    @FXML
    private ImageView forecastImage1;

    @FXML
    private ImageView forecastImage11;

    @FXML
    private ImageView forecastImage13;

    @FXML
    private ImageView forecastImage15;

    @FXML
    private ImageView forecastImage2;

    @FXML
    private ImageView forecastImage3;

    @FXML
    private ImageView forecastImage4;

    @FXML
    private ImageView forecastImage5;

    @FXML
    private ImageView forecastImage6;

    @FXML
    private ImageView forecastImage7;

    @FXML
    private ImageView forecastImage9;

    @FXML
    private Text forecastRain0;

    @FXML
    private Text forecastRain1;

    @FXML
    private Text forecastRain2;

    @FXML
    private Text forecastRain3;

    @FXML
    private Text forecastRain4;

    @FXML
    private Text forecastRain5;

    @FXML
    private Text forecastRain6;

    @FXML
    private Text forecastRain7;

    @FXML
    private Text forecastTemperature0;

    @FXML
    private Text forecastTemperature1;

    @FXML
    private Text forecastTemperature10;

    @FXML
    private Text forecastTemperature11;

    @FXML
    private Text forecastTemperature12;

    @FXML
    private Text forecastTemperature13;

    @FXML
    private Text forecastTemperature14;

    @FXML
    private Text forecastTemperature15;

    @FXML
    private Text forecastTemperature2;

    @FXML
    private Text forecastTemperature3;

    @FXML
    private Text forecastTemperature4;

    @FXML
    private Text forecastTemperature5;

    @FXML
    private Text forecastTemperature6;

    @FXML
    private Text forecastTemperature7;

    @FXML
    private Text forecastTemperature8;

    @FXML
    private Text forecastTemperature9;

    @FXML
    private Text weatherNowDescription;

    @FXML
    private Text weatherNowFeelsLike;

    @FXML
    private ImageView weatherNowImage;

    @FXML
    private Text weatherNowTemperature;
    private WeatherService weatherService;

    public MainViewController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        super(cityManager, viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        System.out.println("weatherChecked !!!");

        //Get Data input from user
        String cityName = "Gdańsk"; //get actual city name from text input

        //Invoke business logic
        weatherService = WeatherServiceFactory.createWeatherService();
        Weather weather = weatherService.getWeather(cityName);
        List<Forecast> forecast = weatherService.getForecast(cityName);

        //Display result from business logic
        displayWeather(weather);
        displayForecast(forecast);
    }


    private void displayForecast(List<Forecast> forecast) {

        List<Text> dayAndHourLabels = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7);
        List<ImageView> imageViews = List.of(forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7);
        List<Text> temperatureLabels = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7);
        List<Text> rainLabels = List.of(forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7);

        List<Text> dayOfWeekLabels = List.of(DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14);
        List<Text> dayTemperatureLabels = List.of(forecastTemperature9, forecastTemperature11, forecastTemperature13, forecastTemperature15);
        List<Text> nightTemperatureLabels = List.of(forecastTemperature8, forecastTemperature10, forecastTemperature12, forecastTemperature14);
        List<ImageView> dayImageViews = List.of(forecastImage9, forecastImage11, forecastImage13, forecastImage15);
        System.out.println(dayTemperatureLabels.get(0));
        System.out.println(dayTemperatureLabels.get(1));
        System.out.println(dayTemperatureLabels.get(2));
        System.out.println(dayTemperatureLabels.get(3));

        System.out.println(nightTemperatureLabels.get(0));
        System.out.println(nightTemperatureLabels.get(1));
        System.out.println(nightTemperatureLabels.get(2));
        System.out.println(nightTemperatureLabels.get(3));

        for (int i = 0; i < 8; i++) {
            Forecast currentForecast = forecast.get(i);

            // Uzyskaj dostęp do odpowiednich komponentów graficznych
            Text dayAndHourLabel = dayAndHourLabels.get(i);
            ImageView imageView = imageViews.get(i);
            Text temperatureLabel = temperatureLabels.get(i);
            Text rainLabel = rainLabels.get(i);

            // Ustaw wartości komponentów graficznych
            dayAndHourLabel.setText(currentForecast.getDateTime());
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + currentForecast.getIconWeatherCode() + "@2x.png");
            imageView.setImage(weatherImage);
            temperatureLabel.setText(String.format("%.0f", currentForecast.getTemperature()) + " °C");
            rainLabel.setText("Opady: " + String.format("%.0f", currentForecast.getProbabilityRain()) + " %");
        }
        int k=0;
        for (int i = 8; i < 16; i+=2) {
            Forecast currentForecast = forecast.get(i);
            DateTimeFormatter formatterFromApi = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(currentForecast.getDateTime(),formatterFromApi);
            DayOfWeek dayOfWeekName = dateTime.getDayOfWeek();

            // Uzyskaj dostęp do odpowiednich komponentów graficznych
            Text dayOfWeek = dayOfWeekLabels.get(k); //tu powinno być od 0 do 3
            ImageView dayImageView = dayImageViews.get(k);
            Text dayTemperature = dayTemperatureLabels.get(k);
            Text nightTemperature = nightTemperatureLabels.get(k);

            dayOfWeek.setText(""+dayOfWeekName);
            nightTemperature.setText(String.format("%.0f", currentForecast.getTemperature()) + " °C");
            currentForecast = forecast.get(i+1);
            Image weatherImage = new Image("https://openweathermap.org/img/wn/" + currentForecast.getIconWeatherCode() + "@2x.png");
            dayImageView.setImage(weatherImage);
            dayTemperature.setText(String.format("%.0f", currentForecast.getTemperature()) + " °C");
            k++;
        }


    }

    private void displayWeather(Weather weather) {
        //temperature.setVisible(true);
        //temperatureLabel.setVisible(true);
        weatherNowTemperature.setText("" + String.format("%.0f", weather.getCurrentTemperature()) + " °C");
        NowTimeHourAndMinutes.setText("Teraz " + weather.getTime());
        weatherNowDescription.setText("" + weather.getDescriptionWeather());
        weatherNowFeelsLike.setText("Temperatura odczuwalna " + String.format("%.0f", weather.getFeelsLikeTemperature()) + " °C");
        Image image = new Image("https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png");
        weatherNowImage.setImage(image);
    }
}
