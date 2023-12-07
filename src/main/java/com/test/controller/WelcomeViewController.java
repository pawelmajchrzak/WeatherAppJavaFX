package com.test.controller;


import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.model.WeatherService;
import com.test.model.WeatherServiceFactory;
import com.test.model.client.WeatherClient;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label errorLabel, errorLabelR;

    List<CountryAndCity> countryAndCityList = new ArrayList<>();

    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    @FXML
    void checkWeatherAction() {

        String cityName= cityField.getText();
        String countryName = countryField.getText();

        String cityNameR= cityFieldR.getText();
        String countryNameR = countryFieldR.getText();

        boolean flagCityIsCorrect = true;

        if (!ValidationUtils.areFieldsValid(cityName,countryName,errorLabel)) {
            flagCityIsCorrect = false;
        } else if (!ValidationUtils.isCityCorrect(cityName,countryName,errorLabel)) {
            flagCityIsCorrect = false;
        }

        if (!ValidationUtils.areFieldsValid(cityNameR,countryNameR,errorLabelR)) {
            flagCityIsCorrect = false;
        } else if (!ValidationUtils.isCityCorrect(cityNameR,countryNameR,errorLabelR)) {
            flagCityIsCorrect = false;
        }

        if (flagCityIsCorrect) {

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

    public WelcomeViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
}
