package com.wordle.controller;

import com.wordle.HelpWindow;
import com.wordle.SettingsWindow;
import com.wordle.StatisticsWindow;
import com.wordle.model.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainController {

    private final MainHandler mainHandler = new MainHandler();

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView helpIcon;

    @FXML
    private GridPane keyboardRow1;

    @FXML
    private GridPane keyboardRow2;

    @FXML
    private GridPane keyboardRow3;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private ImageView statisticsIcon;

    public void showHelp() throws IOException {
        HelpWindow.display();
    }

    public void showStatistics() throws IOException {
        StatisticsWindow.display();
    }

    public void showSettings() throws IOException {
        SettingsWindow.display();
    }

    public void getRandomWord() {
        mainHandler.getRandomWord();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        mainHandler.onKeyPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }
}