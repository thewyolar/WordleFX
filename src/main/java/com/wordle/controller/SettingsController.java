package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.ToggleSwitch;

public class SettingsController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label darkThemeLabel;

    @FXML
    private Label settingsTitle;

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

    public void switchTheme(MouseEvent mouseEvent) {
        if (darkThemeSwitcher.isSelected()) {
            borderPane.setStyle("-fx-background-color: #212529");

        }
        else {
            borderPane.setStyle("-fx-background-color: white");
        }
    }
}
