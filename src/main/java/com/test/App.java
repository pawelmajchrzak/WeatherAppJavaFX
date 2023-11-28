package com.test;

import com.test.controller.persistance.CountryAndCity;
import com.test.controller.persistance.PersistenceAccess;
import com.test.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.List;

public class App extends Application {
    public static void main( String[] args ) {
        launch(args);
    }
    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private CityManager cityManager = new CityManager();

    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory viewFactory = new ViewFactory(cityManager);
        List<CountryAndCity> countryAndCityList = persistenceAccess.loadFromPersistence();

        if(countryAndCityList.size() > 0) {
            cityManager.setCityData(countryAndCityList);
            viewFactory.showMainView();
        } else {
            viewFactory.showWelcomeView();
        }

    }

    @Override
    public void stop() throws Exception {

        List<CountryAndCity> dataToSave = cityManager.getCityData();
        persistenceAccess.saveToPersistence(dataToSave);

    }
}
