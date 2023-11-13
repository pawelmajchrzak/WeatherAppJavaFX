package com.test.controller;

import com.test.CityManager;
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

public class MainViewController extends AbstractController{

    @FXML
    private Text NextFourthDayDDandMM;

    @FXML
    private Text NextSecondDayDDandMM;

    @FXML
    private Text NextThirdDayDDandMM;

    @FXML
    private Text NowTimeHourAndMinutes;

    @FXML
    private Text TodayDDAndMM;

    @FXML
    private Text TomorrowDDAndMM;

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView weatherNextFourthDay15ClockImage;

    @FXML
    private Text weatherNextFourthDay15ClockTemperature;

    @FXML
    private Text weatherNextFourthDay3ClockTemperature;

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

    @FXML
    private ImageView weatherToday15ClockImage;

    @FXML
    private Text weatherToday15ClockPOP;

    @FXML
    private Text weatherToday15ClockTemperature;

    @FXML
    private ImageView weatherToday21ClockImage;

    @FXML
    private Text weatherToday21ClockPOP;

    @FXML
    private Text weatherToday21ClockTemperature;

    @FXML
    private ImageView weatherToday3ClockImage;

    @FXML
    private Text weatherToday3ClockPOP;

    @FXML
    private Text weatherToday3ClockTemperature;

    @FXML
    private ImageView weatherToday9ClockImage;

    @FXML
    private Text weatherToday9ClockPOP;

    @FXML
    private Text weatherToday9ClockTemperature;

    @FXML
    private ImageView weatherTomorrow15ClockImage;

    @FXML
    private Text weatherTomorrow15ClockPOP;

    @FXML
    private Text weatherTomorrow15ClockTemperature;

    @FXML
    private ImageView weatherTomorrow21ClockImage;

    @FXML
    private Text weatherTomorrow21ClockPOP;

    @FXML
    private Text weatherTomorrow21ClockTemperature;

    @FXML
    private ImageView weatherTomorrow3ClockImage;

    @FXML
    private Text weatherTomorrow3ClockPOP;

    @FXML
    private Text weatherTomorrow3ClockTemperature;

    @FXML
    private ImageView weatherTomorrow9ClockImage;

    @FXML
    private Text weatherTomorrow9ClockPOP;

    @FXML
    private Text weatherTomorrow9ClockTemperature;


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

        //Display result from business logic
        displayWeather(weather);
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
