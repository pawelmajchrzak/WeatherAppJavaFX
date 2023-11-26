package com.test;

import com.test.controller.persistance.CountryAndCity;

import java.util.ArrayList;
import java.util.List;

public class CityManager {

    private List<CountryAndCity> cityData = new ArrayList<>();
    public List<CountryAndCity> getDataToSave() {
        return cityData;
    }
    public void setCityData(List<CountryAndCity> cityData) {
        this.cityData = cityData;
    }
    public List<CountryAndCity> getCityData() {
        return cityData;
    }
    public void addCityData(CountryAndCity countryAndCity) {
        cityData.add(countryAndCity);
    }

    public void removeLastCityData() {
        if (!cityData.isEmpty()) {
            cityData.remove(cityData.size() - 1);
        }
    }
}
