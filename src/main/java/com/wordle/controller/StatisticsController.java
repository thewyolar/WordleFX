package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsController {

    @FXML
    private Label consecutiveMaximum;

    @FXML
    private Label playedGamesLabel;

    @FXML
    private Label totalWinsLabel;

    @FXML
    private Label winsInARowNow;

    public void setPlayedGames(int value) {
        this.playedGamesLabel.setText(String.valueOf(value));
    }

    public void setTotalWins(int value) {
        this.totalWinsLabel.setText(String.valueOf(value));
    }

    public void setWinsInARowNow(int value) {
        this.winsInARowNow.setText(String.valueOf(value));
    }

    public void setConsecutiveMaximum(int value) {
        this.consecutiveMaximum.setText(String.valueOf(value));
    }
}
