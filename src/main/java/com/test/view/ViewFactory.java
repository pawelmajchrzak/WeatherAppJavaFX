package com.test.view;

import com.test.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public void showMainView(){
        AbstractController controller = new MainViewController(this, "MainView.fxml");
        initializeStage(controller);
    }

    public void showWelcomeView(){
        AbstractController controller = new WelcomeViewController(this, "WelcomeView.fxml");
        initializeStage(controller);
    }

//    public void showNestedView(){
//        AbstractController controller = new NestedController(this, "nestedView.fxml");
//        initializeStage(controller);
//    }

    public void testView(){
        AbstractController controller = new TestController(this, "test.fxml");
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
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Pogoda 24h w Twoim mieście i na wakacje");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("ikonaPogody.png")));

        stage.show();
    }
}
