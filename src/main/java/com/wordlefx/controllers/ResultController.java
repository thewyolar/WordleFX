package com.wordlefx.controllers;

import com.wordlefx.MainApplication;
import com.wordlefx.helper.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/** Класс контроллера для взаимодействия окна приложения с результатами игры с FXML */
public class ResultController {

    /** borderPane - контейнер со всеми элементами окна приложения c правилами игры */
    @FXML
    private BorderPane borderPane;

    /** gridPane - таблица с результатами игры */
    @FXML
    private GridPane gridPane;

    /** winningWordLabel - метка с загаданным словом */
    @FXML
    private Label winningWordLabel;

    /** resultButton - кнопка для перезапуска игры */
    @FXML
    private Button restartButton;

    /** quitButton - кнопка для выхода из приложения */
    @FXML
    private Button quitButton;

    /** shareButton - кнопка для копирования результатов игры в буфер обмена устройства */
    @FXML
    private Button shareButton;

    /**
     * Возвращает значение поля {@link ResultController#winningWordLabel}
     * @return возвращает ссылку на метку с загаданным словом
     */
    public Label getWinningWordLabel() {
        return winningWordLabel;
    }

    /**
     * Возвращает значение поля {@link ResultController#restartButton}
     * @return возвращает ссылку на кнопку для перезапуска игры
     */
    public Button getRestartButton() {
        return restartButton;
    }

    /**
     * Возвращает значение поля {@link ResultController#quitButton}
     * @return возвращает ссылку на кнопку для выхода из приложения
     */
    public Button getQuitButton() {
        return quitButton;
    }

    /**
     * Возвращает значение поля {@link ResultController#shareButton}
     * @return возвращает ссылку на кнопку для копирования результатов игры в буфер обмена устройства
     */
    public Button getShareButton() {
        return shareButton;
    }

    /** Запускает метод {@link MainHandler#fillResultGrid(GridPane, GridPane)} */
    public void createResultGrid() {
        MainHandler.fillResultGrid(MainApplication.getController().getWordsGridPane(), gridPane);
    }

    /**
     * Возвращает результат метода {@link MainHandler#readResultGrid(GridPane)}
     * @return возвращает строку, полученную из метода readResultGrid
     */
    public String readResultGrid() {
        return MainHandler.readResultGrid(gridPane);
    }
}
