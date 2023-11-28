package com.test.controller;

import com.test.CityManager;
import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class WelcomeViewController extends AbstractController {

    @FXML
    private TextField cityField;

    @FXML
    private TextField cityFieldR;

    @FXML
    private TextField countryField;

    @FXML
    private TextField countryFieldR;
    @FXML
    private Text errorLabel;

    @FXML
    private Text errorLabelR;



    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    @FXML
    void checkWeatherAction() {

        String cityName= cityField.getText();
        String countryName = countryField.getText();

        String cityNameR= cityFieldR.getText();
        String countryNameR = countryFieldR.getText();

        weatherService = WeatherServiceFactory.createWeatherService();
        if (fieldsAreValid()&&isCityCorrect(cityName,countryName,errorLabel)&&isCityCorrect(cityNameR,countryNameR,errorLabelR)) {
            cityManager.addCityData(new CountryAndCity(countryName,cityName));
            cityManager.addCityData(new CountryAndCity(countryNameR,cityNameR));

            List<CountryAndCity> dataToSave = cityManager.getCityData();
            persistenceAccess.saveToPersistence(dataToSave);
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
    public WelcomeViewController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        super(cityManager, viewFactory, fxmlName);
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
