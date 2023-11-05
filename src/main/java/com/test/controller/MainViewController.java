package com.test.controller;

import com.test.CityManager;
import com.test.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainViewController extends AbstractController{

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private Label errorLabel;

    public MainViewController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        super(cityManager, viewFactory, fxmlName);
    }

    @FXML
    void checkWeatherAction() {
        System.out.println("weatherChecked !!!");
    }
}
