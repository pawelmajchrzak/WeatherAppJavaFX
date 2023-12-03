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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MainViewController extends AbstractController{

    @FXML
    NestedController leftNestedController;
    @FXML
    NestedController rightNestedController;

    @FXML
    Button buttonCheckWeather;

//    private String leftCityName;
//    private String  leftCountryName;
//    private String rightCityName;
//    private String rightCountryName;



    String secondCityFromFile;
    String secondCountryFromFile;

    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    List<CountryAndCity> countryAndCityList = persistenceAccess.loadFromPersistence();


    @FXML
    private void initialize() {

        if(countryAndCityList.size() < 1) {
            Platform.runLater(() -> {
                Stage stage = (Stage) buttonCheckWeather.getScene().getWindow();
                stage.close();
                viewFactory.showWelcomeView();
            });
        }


        //weatherScreenController = new WeatherScreenController(imageViews, dayImageViews, fieldsForWeather, fieldsForWeatherR, allTextFieldsForecast, allTextFieldsForecastR);


        if (!countryAndCityList.isEmpty()) {
            CountryAndCity firstCity = countryAndCityList.get(0);
            leftNestedController.updateCountryField(firstCity.getCountry());
            leftNestedController.updateCityField(firstCity.getCity());

            CountryAndCity secondCity = countryAndCityList.get(1);

            rightNestedController.updateCountryField(secondCity.getCountry());
            rightNestedController.updateCityField(secondCity.getCity());

            secondCountryFromFile = secondCity.getCountry();
            secondCityFromFile = secondCity.getCity();
        }

        leftNestedController.checkAction();
        rightNestedController.checkAction();
    }

    private WeatherService weatherService;

    public MainViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        leftNestedController.checkAction();
        rightNestedController.checkAction();
//        leftCityName = leftNestedController.getCityFieldText();
//        leftCountryName = leftNestedController.getCountryFieldText();
//        System.out.println(leftCityName);
//        System.out.println(leftCountryName);
//        if (fieldsAreValid()) {
//
//            String cityName= cityField.getText();
//            String countryName = countryField.getText();
        if (leftNestedController.getflagCityIsCorrect()&& rightNestedController.getflagCityIsCorrect()) {
            CountryAndCity newCountryAndCityLeft = new CountryAndCity(leftNestedController.getCountryFieldText(), leftNestedController.getCityFieldText());
            CountryAndCity newCountryAndCityRight = new CountryAndCity(rightNestedController.getCountryFieldText(), rightNestedController.getCityFieldText());
            countryAndCityList.remove(1);
            countryAndCityList.remove(0);
            countryAndCityList.add(newCountryAndCityLeft);
            countryAndCityList.add(newCountryAndCityRight);
            persistenceAccess.saveToPersistence(countryAndCityList);
        }
//
//            String cityNameR= cityFieldR.getText();
//            String countryNameR = countryFieldR.getText();
           // CountryAndCity newCountryAndCity2 = new CountryAndCity(countryNameR, cityNameR);
//
//            weatherService = WeatherServiceFactory.createWeatherService();
//            boolean flagCityIsCorrect = isCityCorrect(cityName, countryName, errorCityLabel, errorCountryLabel);
//            boolean flagCityIsCorrectR = isCityCorrect(cityNameR, countryNameR, errorCityLabelR, errorCountryLabelR);
//
//            if (flagCityIsCorrect) {

//
//                Weather weather = weatherService.getWeather(cityName, countryName);
//                List<Forecast> forecast = weatherService.getForecast(cityName, countryName);
//
//                weatherScreenController.displayWeather(weather, weatherNowImage, fieldsForWeather);
//                weatherScreenController.displayForecast(forecast, allTextFieldsForecast, imageViews, dayImageViews);
//            }
//
//            if (flagCityIsCorrectR) {
//                countryAndCityList.remove(1);
//                countryAndCityList.add(newCountryAndCity2);
//
//                secondCountryFromFile = countryNameR;
//                secondCityFromFile = cityNameR;
//
//                Weather weatherR = weatherService.getWeather(cityNameR, countryNameR);
//                List<Forecast> forecastR = weatherService.getForecast(cityNameR, countryNameR);
//
//                weatherScreenController.displayWeather(weatherR, weatherNowImageR, fieldsForWeatherR);
//                weatherScreenController.displayForecast(forecastR, allTextFieldsForecastR, imageViewsR, dayImageViewsR);
//            }
//
//        }
    }

//    private boolean isCityCorrect(String cityName, String countryName, Label cityLabel, Label countryLabel) {
//        if (weatherService.isCityAndCountryValid(cityName, countryName)) {
//            cityLabel.setText("");
//            countryLabel.setText("");
//            return true;
//        } else {
//            countryLabel.setText("Dane dla podanego państwa ");
//            cityLabel.setText("lub miasta nie są dostępne!");
//        }
//        return false;
//    }
//
//    private boolean fieldsAreValid() {
//        if(countryField.getText().isEmpty()) {
//            errorCountryLabel.setText("Proszę wpisać państwo!");
//            return  false;
//        }
//        if(cityField.getText().isEmpty()) {
//            errorCityLabel.setText("Proszę wpisać miasto!");
//            return  false;
//        }
//        errorCityLabel.setText("");
//        return  true;
//    }

}
