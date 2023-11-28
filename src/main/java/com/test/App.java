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
    //private PersistenceAccess persistenceAccess = new PersistenceAccess();

    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory viewFactory = new ViewFactory();
        //List<CountryAndCity> countryAndCityList = persistenceAccess.loadFromPersistence();
        viewFactory.showWelcomeView();
//        if(countryAndCityList.size() > 0) {
//            viewFactory.showMainView();
//        } else {
//            viewFactory.showWelcomeView();
//        }
    }

    @Override
    public void stop() throws Exception {

        //List<CountryAndCity> dataToSave = cityManager.getCityData();
        //persistenceAccess.saveToPersistence(dataToSave);

    }
}
