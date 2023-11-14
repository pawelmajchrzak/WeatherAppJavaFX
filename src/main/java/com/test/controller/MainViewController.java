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
        Forecast thirdForecast = forecast.get(2);
        forecastDayAndHour0.setText("" + thirdForecast.getDateTime());
        Image image = new Image("https://openweathermap.org/img/wn/" + thirdForecast.getIconWeatherCode()+"@2x.png");
        forecastImage0.setImage(image);
        forecastTemperature0.setText("" + thirdForecast.getTemperature() + "st.C");
        forecastRain0.setText("" + thirdForecast.getProbabilityRain()*100 + "%");

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
