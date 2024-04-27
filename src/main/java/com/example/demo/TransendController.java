package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class TransendController {

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
    private Rectangle

    @FXML
    public void initialize(int bus) {
        Bus(bus);
}




    boolean pieGrafic = false;
    @FXML
    public void initializeGrafic() {

        if (!pieGrafic){
            graf.getData().add(new PieChart.Data("Bus in Traffic", 540));
            graf.getData().add(new PieChart.Data("Bus in Garage", 40));
            graf.getData().add(new PieChart.Data("Bus in Maintence", 10));
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

    public void setRoutes(){
        graphBox.setVisible(false);
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

        Rectangle busContainer = new Rectangle(100,50);
        busContainer.setStyle("-fx-fill: #084a83;");
        busContainer.setLayoutX(50);
        busContainer.setLayoutY(50);
    }


}