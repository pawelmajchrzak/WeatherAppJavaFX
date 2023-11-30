package com.test.controller;

import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.model.Forecast;
import com.test.model.Weather;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.view.ViewFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MainViewController extends AbstractController{

    @FXML
    private Text DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14;

    @FXML
    private Text DayOfWeek8R, DayOfWeek10R, DayOfWeek12R, DayOfWeek14R;

    @FXML
    private Text NowTimeHourAndMinutes;

    @FXML
    private Text NowTimeHourAndMinutesR;

    @FXML
    private TextField cityField, countryField;

    @FXML
    private TextField cityFieldR, countryFieldR;

    @FXML
    private Label errorCityLabel, errorCountryLabel;

    @FXML
    private Label errorCityLabelR, errorCountryLabelR;

    @FXML
    private Text forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7;

    @FXML
    private Text forecastDayAndHour0R, forecastDayAndHour1R, forecastDayAndHour2R, forecastDayAndHour3R, forecastDayAndHour4R, forecastDayAndHour5R, forecastDayAndHour6R, forecastDayAndHour7R;

    @FXML
    private ImageView forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7, forecastImage9, forecastImage11, forecastImage13, forecastImage15;

    @FXML
    private ImageView forecastImage0R, forecastImage1R, forecastImage2R, forecastImage3R, forecastImage4R, forecastImage5R, forecastImage6R, forecastImage7R, forecastImage9R, forecastImage11R, forecastImage13R, forecastImage15R;

    @FXML
    private Text forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7;

    @FXML
    private Text forecastRain0R, forecastRain1R, forecastRain2R, forecastRain3R, forecastRain4R, forecastRain5R, forecastRain6R, forecastRain7R;

    @FXML
    private Text forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7;

    @FXML
    private Text forecastTemperature8, forecastTemperature9, forecastTemperature10, forecastTemperature11, forecastTemperature12, forecastTemperature13, forecastTemperature14, forecastTemperature15;

    @FXML
    private Text forecastTemperature0R, forecastTemperature1R, forecastTemperature2R, forecastTemperature3R, forecastTemperature4R, forecastTemperature5R, forecastTemperature6R, forecastTemperature7R;
    @FXML
    private Text forecastTemperature8R, forecastTemperature9R, forecastTemperature10R, forecastTemperature11R, forecastTemperature12R, forecastTemperature13R, forecastTemperature14R, forecastTemperature15R;

    @FXML
    private Text weatherNowDescription, weatherNowFeelsLike, weatherNowTemperature;

    @FXML
    private Text weatherNowDescriptionR, weatherNowFeelsLikeR, weatherNowTemperatureR;
    
    @FXML
    private ImageView weatherNowImage;

    @FXML
    private ImageView weatherNowImageR;



    private List<ImageView> imageViews;
    private List<ImageView> dayImageViews;
    private List<ImageView> imageViewsR;
    private List<ImageView> dayImageViewsR;

    private List<Text> fieldsForWeather;
    private List<Text> fieldsForWeatherR;

    List<List<Text>> allTextFieldsForecast = new ArrayList<>();
    List<List<Text>> allTextFieldsForecastR = new ArrayList<>();

    private WeatherScreenController weatherScreenController;

    String secondCityFromFile;
    String secondCountryFromFile;

    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    List<CountryAndCity> countryAndCityList = persistenceAccess.loadFromPersistence();


    @FXML
    private void initialize() {

        if(countryAndCityList.size() < 1) {
            Platform.runLater(() -> {
                Stage stage = (Stage) NowTimeHourAndMinutes.getScene().getWindow();
                stage.close();
                viewFactory.showWelcomeView();
            });
        }


        List<Text> dayAndHourTexts = List.of(forecastDayAndHour0, forecastDayAndHour1, forecastDayAndHour2, forecastDayAndHour3, forecastDayAndHour4, forecastDayAndHour5, forecastDayAndHour6, forecastDayAndHour7);
        List<Text> temperatureTexts = List.of(forecastTemperature0, forecastTemperature1, forecastTemperature2, forecastTemperature3, forecastTemperature4, forecastTemperature5, forecastTemperature6, forecastTemperature7);
        List<Text> rainTexts = List.of(forecastRain0, forecastRain1, forecastRain2, forecastRain3, forecastRain4, forecastRain5, forecastRain6, forecastRain7);
        imageViews = List.of(forecastImage0, forecastImage1, forecastImage2, forecastImage3, forecastImage4, forecastImage5, forecastImage6, forecastImage7);
        List<Text> dayOfWeekTexts = List.of(DayOfWeek8, DayOfWeek10, DayOfWeek12, DayOfWeek14);
        List<Text> dayTemperatureTexts = List.of(forecastTemperature9, forecastTemperature11, forecastTemperature13, forecastTemperature15);
        List<Text> nightTemperatureTexts = List.of(forecastTemperature8, forecastTemperature10, forecastTemperature12, forecastTemperature14);
        dayImageViews = List.of(forecastImage9, forecastImage11, forecastImage13, forecastImage15);
        allTextFieldsForecast.add(dayAndHourTexts);
        allTextFieldsForecast.add(temperatureTexts);
        allTextFieldsForecast.add(rainTexts);
        allTextFieldsForecast.add(dayOfWeekTexts);
        allTextFieldsForecast.add(dayTemperatureTexts);
        allTextFieldsForecast.add(nightTemperatureTexts);

        List<Text> dayAndHourTextsR = List.of(forecastDayAndHour0R, forecastDayAndHour1R, forecastDayAndHour2R, forecastDayAndHour3R, forecastDayAndHour4R, forecastDayAndHour5R, forecastDayAndHour6R, forecastDayAndHour7R);
        List<Text> temperatureTextsR = List.of(forecastTemperature0R, forecastTemperature1R, forecastTemperature2R, forecastTemperature3R, forecastTemperature4R, forecastTemperature5R, forecastTemperature6R, forecastTemperature7R);
        List<Text> rainTextsR = List.of(forecastRain0R, forecastRain1R, forecastRain2R, forecastRain3R, forecastRain4R, forecastRain5R, forecastRain6R, forecastRain7R);
        imageViewsR = List.of(forecastImage0R, forecastImage1R, forecastImage2R, forecastImage3R, forecastImage4R, forecastImage5R, forecastImage6R, forecastImage7R);
        List<Text> dayOfWeekTextsR = List.of(DayOfWeek8R, DayOfWeek10R, DayOfWeek12R, DayOfWeek14R);
        List<Text> dayTemperatureTextsR = List.of(forecastTemperature9R, forecastTemperature11R, forecastTemperature13R, forecastTemperature15R);
        List<Text> nightTemperatureTextsR = List.of(forecastTemperature8R, forecastTemperature10R, forecastTemperature12R, forecastTemperature14R);
        dayImageViewsR = List.of(forecastImage9R, forecastImage11R, forecastImage13R, forecastImage15R);
        allTextFieldsForecastR.add(dayAndHourTextsR);
        allTextFieldsForecastR.add(temperatureTextsR);
        allTextFieldsForecastR.add(rainTextsR);
        allTextFieldsForecastR.add(dayOfWeekTextsR);
        allTextFieldsForecastR.add(dayTemperatureTextsR);
        allTextFieldsForecastR.add(nightTemperatureTextsR);

        fieldsForWeather = List.of(weatherNowTemperature, NowTimeHourAndMinutes, weatherNowDescription, weatherNowFeelsLike);
        fieldsForWeatherR = List.of(weatherNowTemperatureR, NowTimeHourAndMinutesR, weatherNowDescriptionR, weatherNowFeelsLikeR);

        weatherScreenController = new WeatherScreenController(imageViews, dayImageViews, fieldsForWeather, fieldsForWeatherR, allTextFieldsForecast, allTextFieldsForecastR);


        if (!countryAndCityList.isEmpty()) {
            CountryAndCity firstCity = countryAndCityList.get(0);
            countryField.setText(firstCity.getCountry());
            cityField.setText(firstCity.getCity());
            CountryAndCity secondCity = countryAndCityList.get(1);
            countryFieldR.setText(secondCity.getCountry());
            cityFieldR.setText(secondCity.getCity());
            secondCountryFromFile = secondCity.getCountry();
            secondCityFromFile = secondCity.getCity();
        }

        checkWeatherAction();
    }

    private WeatherService weatherService;

    public MainViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        if (fieldsAreValid()) {

            String cityName= cityField.getText();
            String countryName = countryField.getText();
            CountryAndCity newCountryAndCity = new CountryAndCity(countryName, cityName);

            String cityNameR= cityFieldR.getText();
            String countryNameR = countryFieldR.getText();
            CountryAndCity newCountryAndCity2 = new CountryAndCity(countryNameR, cityNameR);

            weatherService = WeatherServiceFactory.createWeatherService();
            boolean flagCityIsCorrect = isCityCorrect(cityName, countryName, errorCityLabel, errorCountryLabel);
            boolean flagCityIsCorrectR = isCityCorrect(cityNameR, countryNameR, errorCityLabelR, errorCountryLabelR);

            if (flagCityIsCorrect) {
                countryAndCityList.remove(1);
                countryAndCityList.remove(0);
                countryAndCityList.add(newCountryAndCity);
                countryAndCityList.add(newCountryAndCity2);

                persistenceAccess.saveToPersistence(countryAndCityList);

                Weather weather = weatherService.getWeather(cityName, countryName);
                List<Forecast> forecast = weatherService.getForecast(cityName, countryName);

                weatherScreenController.displayWeather(weather, weatherNowImage, fieldsForWeather);
                weatherScreenController.displayForecast(forecast, allTextFieldsForecast, imageViews, dayImageViews);
            }

            if (flagCityIsCorrectR) {
                countryAndCityList.remove(1);
                countryAndCityList.add(newCountryAndCity2);

                secondCountryFromFile = countryNameR;
                secondCityFromFile = cityNameR;

                Weather weatherR = weatherService.getWeather(cityNameR, countryNameR);
                List<Forecast> forecastR = weatherService.getForecast(cityNameR, countryNameR);

                weatherScreenController.displayWeather(weatherR, weatherNowImageR, fieldsForWeatherR);
                weatherScreenController.displayForecast(forecastR, allTextFieldsForecastR, imageViewsR, dayImageViewsR);
            }

        }
    }

    private boolean isCityCorrect(String cityName, String countryName, Label cityLabel, Label countryLabel) {
        if (weatherService.isCityAndCountryValid(cityName, countryName)) {
            cityLabel.setText("");
            countryLabel.setText("");
            return true;
        } else {
            countryLabel.setText("Dane dla podanego państwa ");
            cityLabel.setText("lub miasta nie są dostępne!");
        }
        return false;
    }

    private boolean fieldsAreValid() {
        if(countryField.getText().isEmpty()) {
            errorCountryLabel.setText("Proszę wpisać państwo!");
            return  false;
        }
        if(cityField.getText().isEmpty()) {
            errorCityLabel.setText("Proszę wpisać miasto!");
            return  false;
        }
        errorCityLabel.setText("");
        return  true;
    }

}
