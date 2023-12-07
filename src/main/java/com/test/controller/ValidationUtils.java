package com.test.controller;

import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.model.client.WeatherClient;
import javafx.scene.control.Label;

public class ValidationUtils {

    private static WeatherService weatherService;

    public static boolean areFieldsValid(String city, String country, Label errorLabel) {
        if(country.isEmpty()) {
            errorLabel.setText("Proszę wpisać państwo!");
            return  false;
        } else if(city.isEmpty()) {
            errorLabel.setText("Proszę wpisać miasto!");
            return  false;
        } else {
            errorLabel.setText("");
            return  true;
        }
    }

    public static boolean isCityCorrect(String cityName, String countryName, Label errorLabel) {
        weatherService = WeatherServiceFactory.createWeatherService();
        WeatherClient weatherClient = weatherService.getWeatherClient();
        if (weatherClient.isCityAndCountryValid(cityName, countryName)) {
            errorLabel.setText("");
            return true;
        } else {
            errorLabel.setText("Dane dla podanego państwa lub miasta nie są dostępne!");
            return false;
        }
    }
}
