package com.test.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainViewController {

    @FXML
    private TextField cityField;

    @FXML
    private TextField countryField;

    @FXML
    private Label errorLabel;

    @FXML
    void checkWeatherAction() {
        System.out.println("weatherChecked !!!");
    }
}
