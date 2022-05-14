package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsController {

    @FXML
    private Label winsInRowMaxLabel;

    @FXML
    private Label playedGamesLabel;

    @FXML
    private Label totalWinsLabel;

    @FXML
    private Label winsInRowNowLabel;

    public void setPlayedGames(int value) {
        this.playedGamesLabel.setText(String.valueOf(value));
    }

    public void setTotalWins(int value) {
        this.totalWinsLabel.setText(String.valueOf(value));
    }

    public void setWinsInRowNow(int value) {
        this.winsInRowNowLabel.setText(String.valueOf(value));
    }

    public void setWinsInRowMax(int value) {
        this.winsInRowMaxLabel.setText(String.valueOf(value));
    }
}
