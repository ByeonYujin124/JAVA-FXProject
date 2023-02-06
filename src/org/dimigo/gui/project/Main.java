package org.dimigo.gui.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("project.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("네이버 검색");

        stage.centerOnScreen();
        stage.show();
    }

}