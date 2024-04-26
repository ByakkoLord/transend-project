package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TransendApplication extends Application {

    Database database = new Database("jdbc:postgresql://localhost:5432/test", "postgres", "11@12B20c");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransendApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1259, 821);

        int bus = database.getBus();
        TransendController controller = fxmlLoader.getController();

        controller.Bus(bus);
        controller.initializeGrafic();


        Image icon = new Image("file:src/main/resources/com/example/demo/assets/logo.png");
        stage.getIcons().add(icon);

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

        stage.setMaxWidth(1259);
        stage.setMaxHeight(821);
        stage.setTitle("Transend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}