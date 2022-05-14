package com.wordle.controller;

import com.wordle.MainApplication;
import com.wordle.MainHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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

    public BorderPane getBorderPane() { return borderPane; }

    public Label getWinningWordLabel() { return winningWordLabel; }

    public Button getRestartButton() { return restartButton; }

    public Button getQuitButton() { return quitButton; }

    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getWordsGridPane(), gridPane);
    }
}
