package com.test.view;

import com.test.CityManager;
import com.test.controller.AbstractController;
import com.test.controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private CityManager cityManager;

    public ViewFactory(CityManager cityManager) {
        this.cityManager = cityManager;
    }

    public void showMainView(){
        System.out.println("show Main View");
        AbstractController controller = new MainViewController(cityManager, this, "MainView.fxml");
        initializeStage(controller);
    }

    public void initializeStage(AbstractController abstractController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(abstractController.getFxmlName()));
        fxmlLoader.setController(abstractController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);

        // Dodaj plik CSS do sceny
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
