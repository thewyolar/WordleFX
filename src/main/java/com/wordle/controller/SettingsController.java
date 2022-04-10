package com.wordle.controller;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;

public class SettingsController {
    @FXML
    private BorderPane borderPane;

    @FXML
    public Hyperlink emailHyperlink;

    @FXML
    public Hyperlink telegramHyperlink;

    @FXML
    public Hyperlink wordleHyperlink;
}
