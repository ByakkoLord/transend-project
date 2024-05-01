package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;


public class TransendController {
    @FXML
    public Text settingsTitle;

    @FXML
    public Text language;

    @FXML
    public AnchorPane settingsPane;

    @FXML
    public Rectangle graphBox1;

    @FXML
    public LineChart linechart;

    @FXML
    public LineChart linechart1;

    @FXML
    public AnchorPane anchorPane1;

    @FXML
    public Rectangle graphBox2;

    @FXML
    public Label bus_state_one1;

    @FXML
    public AnchorPane anchorPane2;

    @FXML
    public TextArea textArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox VboxScroll;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label bus_state_one;

    @FXML
    private PieChart graf;

    @FXML
    private Rectangle graphBox;

    @FXML
    private BarChart barChart;

    @FXML
    private Circle baricon;

    @FXML
    private Circle baricon1;

    @FXML
    private Circle baricon2;

    @FXML
    private Text barText;

    @FXML
    private Text barText1;

    @FXML
    private Text barText2;

    @FXML
    private Circle pieicon;

    @FXML
    private Circle pieicon1;

    @FXML
    private Circle pieicon2;

    @FXML
    private Text pieText;

    @FXML
    private Text pieText1;

    @FXML
    private Text pieText2;

    public void atualizarGrafico(int active, int garage, int maintenance) {
        if (graf.getData().isEmpty()) {
            graf.getData().add(0, new PieChart.Data("Bus in Traffic", active));
            graf.getData().add(1, new PieChart.Data("Bus in Garage", garage));
            graf.getData().add(2, new PieChart.Data("Bus in Maintence", maintenance));
        }

        graf.getData().set(0, new PieChart.Data("Bus in Traffic", active));
        graf.getData().set(1, new PieChart.Data("Bus in Garage", garage));
        graf.getData().set(2, new PieChart.Data("Bus in Maintence", maintenance));

        for (PieChart.Data data : graf.getData()) {
            String name = data.getName();
            switch (name) {
                case "Bus in Traffic":
                    data.getNode().setStyle("-fx-pie-color: #084a83;");
                    break;
                case "Bus in Garage":
                    data.getNode().setStyle("-fx-pie-color: #ff7f0e;");
                    break;
                case "Bus in Maintenance":
                    data.getNode().setStyle("-fx-pie-color: #2ca02c;");
                    break;
                default:
                    break;
            }
        }
    }
    int totalBus = 13000;
    public void updateBusCount(int bus) {

        bus_state_one.setText(Integer.toString(bus));
        bus_state_one1.setText(Integer.toString(totalBus - bus));
    }

    public void setClose() {
        System.exit(0);
    }

    public void setMinimize() {
        Stage stage = (Stage) bus_state_one.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setGraphs() {
        unshowContent();
        graphBox1.setVisible(true);
        graphBox.setVisible(true);
        graf.setVisible(true);
        linechart.setVisible(true);
        baricon.setVisible(true);
        baricon1.setVisible(true);
        baricon2.setVisible(true);
        barText.setVisible(true);
        barText1.setVisible(true);
        barText2.setVisible(true);
        pieicon.setVisible(true);
        pieicon1.setVisible(true);
        pieicon2.setVisible(true);
        pieText.setVisible(true);
        pieText1.setVisible(true);
        pieText2.setVisible(true);
    }

    public void unshowContent() {
        graphBox1.setVisible(false);
        settingsPane.setVisible(false);
        scrollPane.setVisible(false);
        graphBox.setVisible(false);
        graf.setVisible(false);
        graphBox2.setVisible(false);
        linechart.setVisible(false);
        linechart1.setVisible(false);
        anchorPane1.setVisible(false);
        anchorPane2.setVisible(false);
        baricon.setVisible(false);
        baricon1.setVisible(false);
        baricon2.setVisible(false);
        barText.setVisible(false);
        barText1.setVisible(false);
        barText2.setVisible(false);
        pieicon.setVisible(false);
        pieicon1.setVisible(false);
        pieicon2.setVisible(false);
        pieText.setVisible(false);
        pieText1.setVisible(false);
        pieText2.setVisible(false);
    }

    public void setHistory() {
        unshowContent();
        anchorPane2.setVisible(true);

    }


    public void setLinechart(List<XYChart.Data<String, Number>> dados) {
        // Criando uma série de dados
        XYChart.Series<String, Number> series = new XYChart.Series<>();

//        // Adicionando dados à série
//        series.getData().add(new XYChart.Data<>(1, 10));
//        series.getData().add(new XYChart.Data<>(2, 20));
//        series.getData().add(new XYChart.Data<>(3, 15));
//        series.getData().add(new XYChart.Data<>(4, 25));
//        series.getData().add(new XYChart.Data<>(5, 30));

        for (XYChart.Data<String, Number> data : dados) {
            series.getData().add(data);
        }

        linechart.getData().clear();

        // Adicionando a série ao LineChart
        linechart.getData().add(series);
    }

    public void addToLinechart(XYChart.Data<String, Number> data) {
        XYChart.Series<String, Number> series;

        if (linechart.getData().size() == 0) {
            series = new XYChart.Series<>();
        } else {
            series = (XYChart.Series<String, Number>) linechart.getData().get(0);
        }

        series.getData().add(data);

        linechart.getData().clear();

        linechart.getData().add(series);
    }

    public void setLinechart1(String day1 , String day2, String day3, String day4, String day5, String day6, String day7, Double dayPas1, Double dayPas2, Double dayPas3, Double dayPas4, Double dayPas5, Double dayPas6, Double dayPas7) {

        LineChart mylineChart1 = linechart1;

        // Criando uma série de dados
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Adicionando dados à série
        series.getData().add(new XYChart.Data<>(day1, dayPas1));
        series.getData().add(new XYChart.Data<>(day2, dayPas2));
        series.getData().add(new XYChart.Data<>(day3, dayPas3));
        series.getData().add(new XYChart.Data<>(day4, dayPas4));
        series.getData().add(new XYChart.Data<>(day5, dayPas5));
        series.getData().add(new XYChart.Data<>(day6, dayPas6));
        series.getData().add(new XYChart.Data<>(day7, dayPas7));




        // Adicionando a série ao LineChart
        mylineChart1.getData().clear();
        mylineChart1.getData().add(series);
    }


    public void setRoutes() {
        unshowContent();
        scrollPane.setVisible(true);
        linechart1.setVisible(true);
        graphBox2.setVisible(true);
        anchorPane1.setVisible(true);
    }
    int busStock = 0;
    public void busBot(Double passagers, int busPerRoute, String route) {
        int busLimit = 60;
        int busPassagersCount = busLimit * busPerRoute;
        System.out.println("Passageiros" + passagers
        );
        System.out.println("Antes "+busPassagersCount);

        int busOut = 0;
        int busIn = 0;
        for (; passagers < busPassagersCount && (busPerRoute > 1); busPerRoute--) {
            busPassagersCount = busLimit * busPerRoute;
            System.out.println("Retirou "+busPassagersCount);
            busStock++;
            busOut++;


        }
        System.out.println("BusStock "+busStock);

        for (; passagers > busPassagersCount && (busPerRoute > 1) && busStock > 0; busPerRoute++){
            busPassagersCount = busLimit * busPerRoute;
            System.out.println("Adicionou "+busPassagersCount);
            busStock--;
            busIn++;
        }

        StringBuilder sb = new StringBuilder(textArea.getText());
        sb.append("Retirar " + busOut + " ônibus --- " + "Adicionar " + busIn + " ônibus" + " LINHA (" + route + ")").append("\n");
        textArea.setText(sb.toString());

    }
    Dotenv dotenv = Dotenv.load();
    Database database = new Database(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
    SPTransAPI api = new SPTransAPI(dotenv.get("SPTRANS_KEY"));
    List<BusPosicaoResult.Linha> buses1 = api.getAllBuses().l;

    public void initBusBot(){


        for (BusPosicaoResult.Linha linha : buses1) {


            String route = linha.c;
            int busPerRoute = linha.qv;
            double min = 10;
            double max = 1000;
            double passagers = Math.round(Math.random() * (max - min)) + min;

            System.out.println("Rota: " + route + " Passageiros: " + passagers);

            Platform.runLater(() ->
                    busBot(passagers, busPerRoute, route)
            );

            Platform.runLater(() ->
                    setRouteChart(route, passagers)
            );




        }
    }



    public void setRouteChart(String route, Double passagers){
        Button busContainer = new Button("Route" + route + " - " + passagers + " Passagers");
        busContainer.setId(route);

        busContainer.setStyle("-fx-font: 20 arial; -fx-fill: #acacac");
        busContainer.setLayoutY(50);
        busContainer.setMinWidth(320);
        busContainer.setStyle("-fx-fill: #e8e8e8; ");
        busContainer.setLayoutX(50);

        VboxScroll.getChildren().add(busContainer);
        VboxScroll.setSpacing(10);
        System.out.println("Button Criado");

        busContainer.setOnAction(event -> {

            String clickedButtonId = ((Button) event.getSource()).getId();

            System.out.println("Botão " + clickedButtonId + " foi clicado!");

            if (clickedButtonId.equals(route)) {

                System.out.println("Ação específica para o botão " + clickedButtonId);
                String day1 = "Segunda";
                String day2 = "Terça";
                String day3 = "Quarta";
                String day4 = "Quinta";
                String day5 = "Sexta";
                String day6 = "Sábado";
                String day7 = "Domingo";

                LocalDate today = LocalDate.now();

                int dayOfMonth = today.getDayOfMonth();
                int daiOfWeek = today.getDayOfWeek().getValue();
                System.out.println("Dia do mês: " + dayOfMonth);
                System.out.println("Dia da semana: " + daiOfWeek);
                LocalDate currentDate = LocalDate.now();
                LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

                long sumTime = firstDayOfMonth.until(currentDate, java.time.temporal.ChronoUnit.WEEKS) + 1;
                System.out.println("Semanas: " + sumTime);

                String route_get = database.getBus(route);

                double sundayP = 0.0;
                double mondayP = 0.0;
                double tuesdayP = 0.0;
                double wednesdayP = 0.0;
                double thursdayP = 0.0;
                double fridayP = 0.0;
                double saturdayP = 0.0;

                switch (daiOfWeek){
                    case 1:
                        sundayP = passagers/sumTime;
                        break;
                    case 2:
                        mondayP = passagers/sumTime;
                        break;
                    case 3:
                        tuesdayP = passagers/sumTime;
                        break;
                    case 4:
                        wednesdayP = passagers/sumTime;
                        break;
                    case 5:
                        thursdayP = passagers/sumTime;
                        break;
                    case 6:
                        fridayP = passagers/sumTime;
                        break;
                    case 7:
                        saturdayP = passagers/sumTime;
                        break;
                }

                double finalSundayP = sundayP;
                double finalMondayP = mondayP;
                double finalTuesdayP = tuesdayP;
                double finalWednesdayP = wednesdayP;
                double finalThursdayP = thursdayP;
                double finalFridayP = fridayP;
                double finalSaturdayP = saturdayP;

                Thread thread = new Thread(() -> {
                    database.sendBus(1, finalSundayP, finalMondayP, finalTuesdayP, finalWednesdayP, finalThursdayP, finalFridayP, finalSaturdayP, route, true, "12:00");
                });
                thread.start();

                Double dayPas1 = Math.random() * 100;
                Double dayPas2 = Math.random() * 100;
                Double dayPas3 = Math.random() * 100;
                Double dayPas4 = Math.random() * 100;
                Double dayPas5 = Math.random() * 100;
                Double dayPas6 = Math.random() * 100;
                Double dayPas7 = Math.random() * 100;

                setLinechart1(day1, day2, day3, day4, day5, day6, day7, dayPas1, dayPas2, dayPas3, dayPas4, dayPas5, dayPas6, dayPas7);
            }
        });


    }
}