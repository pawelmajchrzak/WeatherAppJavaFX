package com.test.controller.persistance;

import java.io.Serializable;

public class CountryAndCity implements Serializable {
    private String countryName;
    private String cityName;

    public CountryAndCity(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return countryName;
    }

    public String getCity() {
        return cityName;
    }
}
