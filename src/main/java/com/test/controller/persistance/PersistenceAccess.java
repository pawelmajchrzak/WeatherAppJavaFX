package com.test.controller.persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceAccess {

    private String COUNTRY_AND_CITY_LOCATION = System.getProperty("user.home") + File.separator + "countryAndCity.ser";

    public String getCOUNTRY_AND_CITY_LOCATION() {
        return COUNTRY_AND_CITY_LOCATION;
    }

    public List<CountryAndCity> loadFromPersistence() {
        List<CountryAndCity> resultList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(COUNTRY_AND_CITY_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<CountryAndCity> persistedList = (List<CountryAndCity>) objectInputStream.readObject();
            resultList.addAll(persistedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    public void saveToPersistence(List<CountryAndCity> validAccounts){
        try {
            File file = new File(COUNTRY_AND_CITY_LOCATION);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(validAccounts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
