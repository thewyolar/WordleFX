package com.wordle.controller;

import com.wordle.MainApplication;
import com.wordle.model.MainHandler;
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
    private Label winningWordLabel;

    public BorderPane getBorderPane() { return borderPane; }

    public Label getWinningWordLabel() { return winningWordLabel; }

    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getGridPane(), gridPane);
    }
}
