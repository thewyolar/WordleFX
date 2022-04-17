package com.wordle.controller;

import com.wordle.MainApplication;
import com.wordle.model.MainHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label winningWordLabel;

    @FXML
    private Text timer;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            timer.setText(dateTimeFormatter.format(LocalDateTime.now()));
                        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public BorderPane getBorderPane() { return borderPane; }

    public Label getWinningWordLabel() { return winningWordLabel; }

    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getGridPane(), gridPane);
    }
}
