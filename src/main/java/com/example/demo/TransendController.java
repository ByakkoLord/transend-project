package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class TransendController {
    @FXML
    private Label bus_state_one;

    @FXML
    private PieChart graf;

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
}