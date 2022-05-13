package com.wordle.controller;

import com.wordle.HelpWindow;
import com.wordle.SettingsWindow;
import com.wordle.StatisticsWindow;
import com.wordle.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    private final MainHandler mainHandler = new MainHandler();

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vboxTop;

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

    @FXML
    private VBox vboxBottom;

    public BorderPane getBorderPane() { return borderPane; }

    public GridPane getGridPane() { return gridPane; }

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

    public void onKeyPressed(KeyEvent keyEvent) throws IOException {
        mainHandler.onKeyPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    public void mainWindowLight() {

    }

    public void mainWindowDark() {

    }
}