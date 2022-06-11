package com.wordle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/** Класс контроллера для взаимодействия окна приложения c правилами игры с FXML */
public class HelpController {

    /** borderPane - контейнер со всеми элементами окна приложения c правилами игры */
    @FXML
    private BorderPane borderPane;

    /** gameRulesLabel - метка с названием окна приложения */
    @FXML
    private Label gameRulesLabel;

    /** gameButton - кнопка для начала игры */
    @FXML
    private ImageView gameButton;

    /**
     * Возвращает значение поля {@link HelpController#gameButton}
     * @return возвращает ссылку на кнопку для начала игры
     */
    public ImageView getGameButton() { return gameButton; }

    /** Устанавливает стили для отображения светлой темы окна приложения с правилами игры */
    public void helpWindowLight() {
        borderPane.setStyle("-fx-background-color: white");
        gameRulesLabel.setStyle("-fx-text-fill: black");
    }

    /** Устанавливает стили для отображения темной темы окна приложения с правилами игры */
    public void helpWindowDark() {
        borderPane.setStyle("-fx-background-color: black");
        gameRulesLabel.setStyle("-fx-text-fill: white");
    }
}
