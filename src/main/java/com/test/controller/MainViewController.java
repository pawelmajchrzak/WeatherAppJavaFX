package com.test.controller;

import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.view.ViewFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.List;


public class MainViewController extends AbstractController{

    @FXML
    NestedController leftNestedController;
    @FXML
    NestedController rightNestedController;
    @FXML
    Button buttonCheckWeather;
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

        if (!countryAndCityList.isEmpty()) {

            CountryAndCity firstCity = countryAndCityList.get(0);
            leftNestedController.updateCountryField(firstCity.getCountry());
            leftNestedController.updateCityField(firstCity.getCity());

            CountryAndCity secondCity = countryAndCityList.get(1);
            rightNestedController.updateCountryField(secondCity.getCountry());
            rightNestedController.updateCityField(secondCity.getCity());

        }

        leftNestedController.checkWeatherAction();
        rightNestedController.checkWeatherAction();
    }

    public MainViewController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        leftNestedController.checkWeatherAction();
        rightNestedController.checkWeatherAction();

        if (leftNestedController.getflagCityIsCorrect()&& rightNestedController.getflagCityIsCorrect()) {
            CountryAndCity newCountryAndCityLeft = new CountryAndCity(leftNestedController.getCountryFieldText(), leftNestedController.getCityFieldText());
            CountryAndCity newCountryAndCityRight = new CountryAndCity(rightNestedController.getCountryFieldText(), rightNestedController.getCityFieldText());
            countryAndCityList.remove(1);
            countryAndCityList.remove(0);
            countryAndCityList.add(newCountryAndCityLeft);
            countryAndCityList.add(newCountryAndCityRight);
            persistenceAccess.saveToPersistence(countryAndCityList);
        }
    }

}
