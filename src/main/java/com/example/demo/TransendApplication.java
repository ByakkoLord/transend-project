package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransendApplication extends Application {

    Dotenv dotenv = Dotenv.load();
    Database database = new Database(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASS"));
    SPTransAPI api = new SPTransAPI(dotenv.get("SPTRANS_KEY"));
    FXMLLoader fxmlLoader = new FXMLLoader(TransendApplication.class.getResource("hello-view.fxml"));
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    TransendController controller;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransendApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1259, 720);

        List<BusPosicaoResult.Linha> buses = api.getAllBuses().l;
        int busCount = buses.stream().mapToInt(linha -> linha.vs.toArray().length).sum();

/*        for (BusPosicaoResult.Linha linha : buses) {
            for (BusPosicaoResult.Veiculo veiculo : linha.vs) {
                int idVeiculo = veiculo.p;
                String route_cod = linha.c;
                boolean pcd_v = veiculo.a;

                System.out.println("ID do veículo: " + idVeiculo);
            }
        }
*/


        TransendController controller = fxmlLoader.getController();

        controller.Bus(busCount);
        controller.setLinechart();
        controller.setLinechart1();
        controller = fxmlLoader.getController();

        controller.atualizarGrafico(0, 0, 0);
        //controller.setLinechart();

        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing application");
            database.closeConnection();
        }));

        Image icon = new Image("file:src/main/resources/com/example/demo/assets/logo.png");
        stage.getIcons().add(icon);

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

        stage.setMaxWidth(1259);
        stage.setMaxHeight(821);
        stage.setTitle("Transend");
        stage.setScene(scene);
        stage.show();

        inicializarBusCount();
        inicializarMainGraphic();
        inicializarPizzaGraphic();
    }

    public void inicializarBusCount() {
        executorService.scheduleWithFixedDelay(() -> {
            List<BusPosicaoResult.Linha> buses = api.getAllBuses().l;
            int busCount = buses.stream().mapToInt(linha -> linha.vs.toArray().length).sum();

            Platform.runLater(() -> {
                System.out.println("controller" + controller);
                controller.updateBusCount(busCount);
            });

            System.out.println("Contador atualizado, total onibus: " + busCount);
        }, 0, 20, TimeUnit.MINUTES);
    }

    public void inicializarMainGraphic() {

    }

    public void inicializarPizzaGraphic() {
        executorService.scheduleWithFixedDelay(() -> {
            List<BusPosicaoResult.Linha> buses = api.getAllBuses().l;
            int activeBuses = buses.stream().mapToInt(linha -> linha.vs.stream().filter(veiculo -> veiculo.a).toArray().length).sum();

            Platform.runLater(() -> {
                System.out.println("controller" + controller);
                controller.atualizarGrafico(activeBuses, activeBuses - activeBuses / 5, 0);
            });

            System.out.println("Grafico atualizado, onibus ativos: " + activeBuses);
        }, 0, 4, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        launch();
    }
}