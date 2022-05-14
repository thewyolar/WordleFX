package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class HelpController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label gameRulesLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView gameButton;

    public ImageView getGameButton() { return gameButton; }

    public void helpWindowLight() {
        borderPane.setStyle("-fx-background-color: white");
        gameRulesLabel.setStyle("-fx-text-fill: black");
    }

    public void helpWindowDark() {
        borderPane.setStyle("-fx-background-color: black");
        gameRulesLabel.setStyle("-fx-text-fill: white");
    }
}
