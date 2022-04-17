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
    private Text timer;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            LocalTime currentTime = LocalTime.now();
                            int hours, minutes, seconds;
                            long delta;
                            String elapsedTime;
                            if (currentTime.getHour() > 3) {
                                delta = ChronoUnit.SECONDS.between(currentTime, LocalTime.MAX);
                                hours = (int)Math.floor(delta / 3600);
                                minutes = (int)Math.floor((delta - hours * 3600) / 60);
                                seconds = (int)Math.floor(delta - hours * 3600 - minutes * 60);
                                elapsedTime = hours + 3 + ":" + minutes + ":" + seconds;
                            } else {
                                delta = ChronoUnit.SECONDS.between(currentTime, LocalTime.parse("03:00:00"));
                                hours = (int)Math.floor(delta / 3600);
                                minutes = (int)Math.floor((delta - hours * 3600) / 60);
                                seconds = (int)Math.floor(delta - hours * 3600 - minutes * 60);
                                elapsedTime = hours + ":" + minutes + ":" + seconds;
                            }
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
