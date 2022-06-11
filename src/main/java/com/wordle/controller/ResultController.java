package com.wordle.controller;

import com.wordle.MainApplication;
import com.wordle.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ResultController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label winningWordLabel;

    @FXML
    private Button restartButton;

    @FXML
    private Button quitButton;

    @FXML
    private ImageView shareButton;

    public BorderPane getBorderPane() { return borderPane; }

    public Label getWinningWordLabel() { return winningWordLabel; }

    public Button getRestartButton() { return restartButton; }

    public Button getQuitButton() { return quitButton; }

    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getWordsGridPane(), gridPane);
    }

    public String readResultGrid() {
       return MainHandler.readResultGrid(gridPane);
    }

    public ImageView getShareButton() { return shareButton; }
}
