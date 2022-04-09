package com.wordle.controller;

import com.wordle.HelpWindow;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainController {
    @FXML
    public GridPane gridPane;
    @FXML
    public ImageView helpIcon;
    @FXML
    public ImageView statisticsIcon;
    @FXML
    public ImageView settingsIcon;
    @FXML
    public GridPane keyboardRow1;
    @FXML
    public GridPane keyboardRow2;
    @FXML
    public GridPane keyboardRow3;

    public void showHelp() {
        HelpWindow.display();
    }
}