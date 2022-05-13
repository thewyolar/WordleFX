package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.ToggleSwitch;

public class SettingsController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label darkThemeLabel;

    @FXML
    private Label settingsTitle;

    @FXML
    private Label contactLabel;

    @FXML
    private ToggleSwitch darkThemeSwitcher;

    @FXML
    private Hyperlink emailHyperlink;

    @FXML
    private Hyperlink telegramHyperlink;

    @FXML
    private Hyperlink wordleHyperlink;

    public Hyperlink getEmailHyperlink() { return emailHyperlink; }

    public Hyperlink getTelegramHyperlink() { return telegramHyperlink; }

    public Hyperlink getWordleHyperlink() { return wordleHyperlink; }

    public void settingsWindowLight() {
        borderPane.setStyle("-fx-background-color: #212529");
        darkThemeLabel.setStyle("-fx-text-fill: white");
        settingsTitle.setStyle("-fx-text-fill: white");
        contactLabel.setStyle("-fx-text-fill: white");
    }

    public void settingsWindowDark() {
        borderPane.setStyle("-fx-background-color: white");
        darkThemeLabel.setStyle("-fx-text-fill: black");
        settingsTitle.setStyle("-fx-text-fill: black");
        contactLabel.setStyle("-fx-text-fill: black");
    }

    public void switchSettingsWindowTheme(MouseEvent mouseEvent) {
        if (darkThemeSwitcher.isSelected()) {
            settingsWindowLight();
        }
        else {
            settingsWindowDark();
        }
    }
}
