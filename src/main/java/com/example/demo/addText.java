package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class addText {

    @FXML
    private Text myText;

    public void initialize() {
        myText.setText("Hello, World!");

    }

}
