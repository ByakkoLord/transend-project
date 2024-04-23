package com.example.demo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransendController {
    @FXML
    private Label bus_state_one;


    @FXML
    public void initialize(int bus) {
        Bus(bus);

}

    public void Bus(int bus) {
        bus_state_one.setText(Integer.toString(bus));
    }
}