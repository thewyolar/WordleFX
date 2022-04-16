package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ResultController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label winnnigWordLabel;

    public Label getWinnnigWordLabel() { return winnnigWordLabel; }

    public void fillResultGrid() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                Label label = new Label();
                label.getStyleClass().add("default-result-tile");
                gridPane.add(label, i, j);
            }
        }
    }
}
