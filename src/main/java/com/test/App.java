package com.test;

import com.test.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainView();

    }

}
