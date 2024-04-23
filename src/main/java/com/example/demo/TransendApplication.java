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


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransendApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1180, 600);
        try {
            Connection connection = Database.getConnection();

            TransendController controller = fxmlLoader.getController();

            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");

                try (Statement statement = connection.createStatement()) {
                    String sql = "select * from bus"; // Query de Teste

                    try (ResultSet resultSet = statement.executeQuery(sql)) {
                        while (resultSet.next()) {
                            int bus = resultSet.getInt("bus");
                            controller.Bus(bus);
                        }
                    }
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Image icon = new Image("file:src/main/resources/com/example/demo/assets/logo.png");







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