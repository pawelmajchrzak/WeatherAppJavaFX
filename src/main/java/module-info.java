module WeatherAppJavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.net.http;
    requires spring.web;

    opens com.test;
    opens com.test.view;
    opens com.test.controller;
    //opens com.test.model;
}