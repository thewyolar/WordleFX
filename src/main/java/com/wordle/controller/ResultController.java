package com.wordle.controller;

import com.wordle.MainApplication;
import com.wordle.model.MainHandler;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getGridPane(), gridPane);
    }
}
