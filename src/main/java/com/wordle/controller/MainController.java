package com.wordle.controller;

import com.wordle.HelpWindow;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void showHelp(MouseEvent event) {
        HelpWindow.display();
    }
}