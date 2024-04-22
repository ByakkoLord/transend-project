package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;


public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        try {
            Connection connection = Database.getConnection();

            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");

                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Image icon = new Image("file:src/main/resources/com/example/demo/assets/logo.png");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1180, 600);

        stage.getIcons().add(icon);

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

        stage.setMaxHeight(600);
        stage.setMaxWidth(1180);
        stage.setTitle("Transend");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}