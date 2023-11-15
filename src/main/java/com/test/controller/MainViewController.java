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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewController extends AbstractController{

    @FXML
    private Text NextSecondDayDDandMM;

    @FXML
    private Text NextThirdDayDDandMM;

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
    private ImageView weatherNextSecondDay15ClockImage;

    @FXML
    private Text weatherNextSecondDay15ClockTemperature;

    @FXML
    private Text weatherNextSecondDay3ClockTemperature;

    @FXML
    private ImageView weatherNextThirdDay15ClockImage;

    @FXML
    private Text weatherNextThirdDay15ClockTemperature;

    @FXML
    private Text weatherNextThirdDay3ClockTemperature;

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
