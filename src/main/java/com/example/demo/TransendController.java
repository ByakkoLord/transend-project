package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;


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

    @FXML
    public void initialize(int bus) {
        Bus(bus);
}




    boolean pieGrafic = false;
    @FXML
    public void initializeGrafic() {

        if (!pieGrafic){
            graf.getData().add(0, new PieChart.Data("Bus in Traffic", 0));
            graf.getData().add(1, new PieChart.Data("Bus in Garage", 0));
            graf.getData().add(2, new PieChart.Data("Bus in Maintence", 0));
            pieGrafic = true;

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


    }

    public void atualizarGrafico(int active, int garage, int maintenance) {
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
    public void Bus(int bus) {
        bus_state_one.setText(Integer.toString(bus));
    }

    public void setClose() {
        System.exit(0);
    }

    public void setMinimize() {
        Stage stage = (Stage) bus_state_one.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setGraphs(){
        unshowContent();
        graphBox1.setVisible(true);
        graphBox.setVisible(true);
        graf.setVisible(true);
        barChart.setVisible(true);
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

    public void unshowContent(){
        graphBox1.setVisible(false);
        settingsPane.setVisible(false);
        scrollPane.setVisible(false);
        graphBox.setVisible(false);
        graf.setVisible(false);
        barChart.setVisible(false);
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

    public void setSettings(){
        unshowContent();
        settingsPane.setVisible(true);

    }

    public void setRoutes(){
        unshowContent();
        scrollPane.setVisible(true);


        String id_bus = "11241";
        String Route = "1567-10";
        String status = "In Traffic";

        Text busName = new Text("     ID:  " + id_bus
                + "                                               Route:  "
                + Route +
                "                                  Status:  "
                + status);
        Rectangle busContainer = new Rectangle(910,5);

        busName.setStyle("-fx-font: 20 arial; -fx-fill: #acacac");

        busContainer.setLayoutY(50);
        busName.setLayoutX(600);
        busName.setLayoutY(700);
        busContainer.setStyle("-fx-fill: #e8e8e8; ");
        busContainer.setLayoutX(50);

        VboxScroll.getChildren().add(busName);
        VboxScroll.getChildren().add(busContainer);
        VboxScroll.setSpacing(10);

    }

}