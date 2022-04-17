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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
                            LocalTime currentDateTime = LocalTime.now();
                            long delta = ChronoUnit.SECONDS.between(currentDateTime, LocalTime.MAX);
                            int hours = (int)Math.floor(delta / 3600);
                            int minutes = (int)Math.floor((delta - hours * 3600) / 60);
                            int seconds = (int)Math.floor(delta - hours * 3600 - minutes * 60);
                            String elapsedTime = hours + 3 + ":" + minutes + ":" + seconds;
                            timer.setText(elapsedTime);
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
