package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainController {
    @FXML
    public GridPane gridPane;

    @FXML
    public ImageView helpIcon;

    @FXML
    public ImageView statisticsIcon;

    @FXML
    public ImageView settingsIcon;


    public void createGrid() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 5; j++) {
                Label label = new Label();
                label.getStyleClass().add("default-tile");
                gridPane.add(label, j, i);
            }
        }
    }
}