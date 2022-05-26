package com.wordle.controller;

import com.wordle.HelpWindow;
import com.wordle.SettingsWindow;
import com.wordle.StatisticsWindow;
import com.wordle.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Класс контроллера для взаимодействия графического окна приложения с FXML
 * @author Alexey Karabanov
 */
public class MainController {

    /**
     * mainHandler - содержит логику, необходимую для контроллера {@link MainController}
     * используется для сохранения "чистоты" в контроллере
     */
    private final MainHandler mainHandler = new MainHandler();

    @FXML
    private Label gameTitleLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vboxTop;

    @FXML
    private GridPane wordsGridPane;

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

    public GridPane getWordsGridPane() { return wordsGridPane; }

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
        mainHandler.onKeyPressed(wordsGridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    public void mainWindowLight() {
        borderPane.setStyle("-fx-background-color: #212529");
        gameTitleLabel.setStyle("-fx-text-fill: white");
    }

    public void mainWindowDark() {
        borderPane.setStyle("-fx-background-color: white");
        gameTitleLabel.setStyle("-fx-text-fill: black");
    }
}