package com.test.controller;

import com.test.view.ViewFactory;
import javafx.fxml.FXML;

public class TestController extends AbstractController {

    @FXML NestedController leftNestedController;
    @FXML NestedController rightNestedController;


    public TestController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);

//        leftNestedController = new NestedController(viewFactory, "nestedView.fxml");
//        rightNestedController = new NestedController(viewFactory, "nestedView.fxml");

    }

    @FXML
    public void initalize() {
//        leftNestedController = new NestedController(viewFactory, "nestedView.fxml");
//        rightNestedController = new NestedController(viewFactory, "nestedView.fxml");
    }

    @FXML
    void checkWeatherAction() {
        System.out.println("testTest");
        leftNestedController.checkAction();
        rightNestedController.checkAction();
//        leftNestedController.checkWeatherAction();
//        //rightNestedController.checkWeatherAction();
    }
}
