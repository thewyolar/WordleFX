package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.ToggleSwitch;

public class SettingsController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Hyperlink emailHyperlink;

    @FXML
    private Hyperlink telegramHyperlink;

    @FXML
    private Hyperlink wordleHyperlink;

    @FXML
    private ToggleSwitch darkThemeSwitcher;

    public Hyperlink getEmailHyperlink() { return emailHyperlink; }

    public Hyperlink getTelegramHyperlink() { return telegramHyperlink; }

    public Hyperlink getWordleHyperlink() { return wordleHyperlink; }

    public ToggleSwitch getDarkThemeSwitcher() { return darkThemeSwitcher; }
}
