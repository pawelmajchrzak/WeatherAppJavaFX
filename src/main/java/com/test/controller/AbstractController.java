package com.test.controller;

import com.test.CityManager;
import com.test.view.ViewFactory;

public abstract class AbstractController {

    protected CityManager cityManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public AbstractController(CityManager cityManager, ViewFactory viewFactory, String fxmlName) {
        this.cityManager = cityManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return  fxmlName;
    }
}