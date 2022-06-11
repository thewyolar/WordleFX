package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StatisticsController {

    @FXML
    private Label playedGamesLabel;

    @FXML
    private Label statisticsLabel;

    @FXML
    private Label totalWinsLabel;

    @FXML
    private Label winsInRowNowLabel;

    @FXML
    private Label winsInRowMaxLabel;

    @FXML
    private ImageView shareButton;

    public void setPlayedGames(int value) {
        this.playedGamesLabel.setText(String.valueOf(value));
    }

    public void setTotalWins(int value) { this.totalWinsLabel.setText(String.valueOf(value)); }

    public void setWinsInRowNow(int value) { this.winsInRowNowLabel.setText(String.valueOf(value)); }

    public void setWinsInRowMax(int value) { this.winsInRowMaxLabel.setText(String.valueOf(value)); }

    public void setStatisticsLabel(int value) {
        String s = statisticsLabel.getText();
        this.statisticsLabel.setText(s + String.valueOf(value));
    }

    public ImageView getShareButton() { return this.shareButton; }
}
