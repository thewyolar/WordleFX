package com.wordle.controller;

import com.wordle.HelpWindow;
import com.wordle.SettingsWindow;
import com.wordle.StatisticsWindow;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainController {
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

    public void showHelp(MouseEvent event) throws IOException {
        HelpWindow.display();
    }

    public void showStatistics(MouseEvent event) throws IOException {
        StatisticsWindow.display();
    }

    public void showSettings(MouseEvent event) throws IOException {
        SettingsWindow.display();
    }
}