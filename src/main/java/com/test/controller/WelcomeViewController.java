package com.test.controller;


import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class WelcomeViewController extends AbstractController {

    @FXML
    private TextField cityField, countryField;

    @FXML
    private TextField cityFieldR, countryFieldR;

    @FXML
    private Text errorLabel, errorLabelR;

    List<CountryAndCity> countryAndCityList = new ArrayList<>();

    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    @FXML
    void checkWeatherAction() {

        String cityName= cityField.getText();
        String countryName = countryField.getText();

        String cityNameR= cityFieldR.getText();
        String countryNameR = countryFieldR.getText();

        weatherService = WeatherServiceFactory.createWeatherService();
        if (fieldsAreValid()&&isCityCorrect(cityName,countryName,errorLabel)&&isCityCorrect(cityNameR,countryNameR,errorLabelR)) {

            CountryAndCity newCountryAndCity = new CountryAndCity(countryName, cityName);
            CountryAndCity newCountryAndCityR = new CountryAndCity(countryNameR, cityNameR);

            countryAndCityList.add(newCountryAndCity);
            countryAndCityList.add(newCountryAndCityR);

            persistenceAccess.saveToPersistence(countryAndCityList);
            Stage oldStage = (Stage) cityField.getScene().getWindow();
            oldStage.close();
            viewFactory.showMainView();
        }

    }

    WeatherService weatherService;

    private boolean isCityCorrect(String cityName, String countryName, Text errorLabel) {
            if (weatherService.isCityAndCountryValid(cityName, countryName)) {
                errorLabel.setText("");
                return true;
            } else {
                errorLabel.setText("Dane dla podanego miasta nie są dostępne ");
            }
            return false;
    }
    public WelcomeViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    private boolean fieldsAreValid() {
        if(countryField.getText().isEmpty()) {
            errorLabel.setText("Proszę wpisać państwo!");
            return  false;
        } else if (cityField.getText().isEmpty()){
            errorLabel.setText("Proszę wpisać miasto!");
            return  false;
        } else {
            errorLabel.setText("");
        }

        if(countryFieldR.getText().isEmpty()) {
            errorLabelR.setText("Proszę wpisać państwo!");
            return  false;
        } else if (cityFieldR.getText().isEmpty()){
            errorLabelR.setText("Proszę wpisać miasto!");
            return  false;
        } else {
            errorLabelR.setText("");
        }

        return  true;
    }

}
