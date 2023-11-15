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
    private ImageView forecastImage0;

    @FXML
    private ImageView forecastImage1;

    @FXML
    private ImageView forecastImage2;

    @FXML
    private Text forecastRain0;

    @FXML
    private Text forecastRain1;

    @FXML
    private Text forecastRain2;

    @FXML
    private Text forecastTemperature0;

    @FXML
    private Text forecastTemperature1;

    @FXML
    private Text forecastTemperature2;

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

//    List<Text> forecastDayAndHourTexts = Arrays.asList(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2);
//    List<ImageView> forecastImageViews = Arrays.asList(forecastImage0, forecastImage1, forecastImage2);
//    List<Text> forecastTemperatureTexts = Arrays.asList(forecastTemperature0, forecastTemperature1, forecastTemperature2);
//    List<Text> forecastRainTexts = Arrays.asList(forecastRain0, forecastRain1, forecastRain2);
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
//        Forecast firstForecast = forecast.get(0);
//        forecastDayAndHour0.setText("" + firstForecast.getDateTime());
//        Image image = new Image("https://openweathermap.org/img/wn/" + firstForecast.getIconWeatherCode()+"@2x.png");
//        forecastImage0.setImage(image);
//        forecastTemperature0.setText("" + firstForecast.getTemperature() + "st.C");
//        forecastRain0.setText("" + firstForecast.getProbabilityRain()*100 + "%");
//        Forecast secondForecast = forecast.get(1);
//        forecastDayAndHour1.setText("" + secondForecast.getDateTime());
//        Image image1 = new Image("https://openweathermap.org/img/wn/" + secondForecast.getIconWeatherCode()+"@2x.png");
//        forecastImage1.setImage(image);
//        forecastTemperature1.setText("" + secondForecast.getTemperature() + "st.C");
//        forecastRain1.setText("" + secondForecast.getProbabilityRain()*100 + "%");
//        Forecast thirdForecast = forecast.get(2);
//        forecastDayAndHour2.setText("" + thirdForecast.getDateTime());
//        Image image2 = new Image("https://openweathermap.org/img/wn/" + thirdForecast.getIconWeatherCode()+"@2x.png");
//        forecastImage2.setImage(image);
//        forecastTemperature2.setText("" + thirdForecast.getTemperature() + "st.C");
//        forecastRain2.setText("" + thirdForecast.getProbabilityRain()*100 + "%");
//
//        for (int i = 0; i<3; i++) {
//            Forecast eachForecast = forecast.get(i);
//            forecastDayAndHour{i}.setText("" + firstForecast.getDateTime());
//            Image image = new Image("https://openweathermap.org/img/wn/" + firstForecast.getIconWeatherCode()+"@2x.png");
//            forecastImage{i}.setImage(image);
//            forecastTemperature{i}.setText("" + firstForecast.getTemperature() + "st.C");
//            forecastRain{i}.setText("" + firstForecast.getProbabilityRain()*100 + "%");
//        }

        List<Text> dayAndHourLabels = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2);
        List<ImageView> imageViews = List.of(forecastImage0, forecastImage1, forecastImage2);
        List<Text> temperatureLabels = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2);
        List<Text> rainLabels = List.of(forecastRain0, forecastRain1, forecastRain2);

        for (int i = 0; i < 3; i++) {
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
            temperatureLabel.setText(currentForecast.getTemperature() + "st.C");
            rainLabel.setText(currentForecast.getProbabilityRain() * 100 + "%");
        }
    }

    private void displayWeather(Weather weather) {
        //temperature.setVisible(true);
        //temperatureLabel.setVisible(true);
        weatherNowTemperature.setText("" + weather.getCurrentTemperature());
        NowTimeHourAndMinutes.setText("Teraz " + weather.getTime());
        weatherNowDescription.setText("" + weather.getDescriptionWeather());
        weatherNowFeelsLike.setText("Temperatura odczuwalna " + weather.getFeelsLikeTemperature());
        String test = "https://openweathermap.org/img/wn/" + weather.getIconWeatherCode()+"@2x.png";
        System.out.println(test);
        Image image = new Image(test);
        weatherNowImage.setImage(image);
    }
}
