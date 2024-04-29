package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TransendApplication extends Application {

    Dotenv dotenv = Dotenv.load();
    Database database = new Database(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASS"));
    SPTransAPI api = new SPTransAPI(dotenv.get("SPTRANS_KEY"));

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransendApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1259, 821);

        List<BusPosicaoResult.Linha> buses = api.getAllBuses().l;

        int busCount = buses.stream().mapToInt(linha -> linha.vs.toArray().length).sum();
        for (BusPosicaoResult.Linha linha : buses) {
            for (BusPosicaoResult.Veiculo veiculo : linha.vs) {
                int idVeiculo = veiculo.p;
                String route_cod = linha.c;
                boolean pcd_v = veiculo.a;


                database.sendBus(idVeiculo, route_cod, pcd_v, 1, 1, 1, 1);
                System.out.println("ID do veículo: " + idVeiculo);
            }
        }





        TransendController controller = fxmlLoader.getController();

        controller.Bus(busCount);


        controller.initializeGrafic();


        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });


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