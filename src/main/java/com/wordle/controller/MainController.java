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

/** Класс контроллера для взаимодействия основного окна приложения с FXML */
public class MainController {

    /** mainHandler - содержит логику, необходимую для работы контроллера {@link MainController} */
    private final MainHandler mainHandler = new MainHandler();

    /** gameTitleLabel - метка с названием игры  */
    @FXML
    private Label gameTitleLabel;

    /** borderPane - контейнер со всеми элементами основного окна приложения */
    @FXML
    private BorderPane borderPane;

    /**
     * vboxTop - контейнер со всеми элементами верхней части окна приложения
     * располагает вложенные элементы в виде вертикального ряда
     */
    @FXML
    private VBox vboxTop;

    /** wordsGridPane - таблица со словами в центральной части окна приложения */
    @FXML
    private GridPane wordsGridPane;

    /** helpIcon - значок окна с правилами игры {@link HelpWindow} */
    @FXML
    private ImageView helpIcon;

    /**
     * keyboardRow1 - однострочная таблица в нижней части окна приложения
     * используется для отображения подсказок на первой строке экранной клавиатуры
     */
    @FXML
    private GridPane keyboardRow1;

    /**
     * keyboardRow2 - однострочная таблица в нижней части окна приложения
     * используется для отображения подсказок на средней строке экранной клавиатуры
     */
    @FXML
    private GridPane keyboardRow2;

    /**
     * keyboardRow3 - однострочная таблица в нижней части окна приложения
     * используется для отображения подсказок на последней строке экранной клавиатуры
     */
    @FXML
    private GridPane keyboardRow3;

    /** settingsIcon - значок окна настроек {@link SettingsWindow} */
    @FXML
    private ImageView settingsIcon;

    /** statisticsIcon - значок окна статистики {@link StatisticsWindow} */
    @FXML
    private ImageView statisticsIcon;

    /**
     * vboxTop - контейнер со всеми элементами нижней части окна приложения
     * располагает вложенные элементы в виде вертикального ряда
     */
    @FXML
    private VBox vboxBottom;

    /**
     * Возвращает значение поля {@link MainController#borderPane}
     * @return возвращает ссылку на контейнер элементов основного окна приложения
     */
    public BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     * Возвращает значение поля {@link MainController#wordsGridPane}
     * @return возвращает ссылку на таблицу для ввода слов в центральной части окна приложения
     */
    public GridPane getWordsGridPane() {
        return wordsGridPane;
    }

    /**
     * Возвращает значение поля {@link MainController#gameTitleLabel}
     * @return возвращает ссылку на метку с названием игры в верхней части окна приложения
     */
    public Label getGameTitleLabel() {
        return gameTitleLabel;
    }

    /**
     * Запускает метод {@link HelpWindow#display()}
     * @exception IOException - исключение ввода-вывода
     */
    public void showHelp() throws IOException {
        HelpWindow.display();
    }

    /**
     * Запускает метод {@link StatisticsWindow#display()}
     * @exception IOException - исключение ввода-вывода
     */
    public void showStatistics() throws IOException {
        StatisticsWindow.display();
    }

    /**
     * Запускает метод {@link SettingsWindow#display()}
     * @exception IOException - исключение ввода-вывода
     */
    public void showSettings() throws IOException {
        SettingsWindow.display();
    }

    /**
     * Запускает метод {@link MainHandler#getRandomWord()}
     */
    public void getRandomWord() {
        mainHandler.getRandomWord();
    }

    /**
     * Запускает метод {@link MainHandler#onKeyPressed(GridPane, GridPane, GridPane, GridPane, KeyEvent)}
     * @param keyEvent - событие ввода слова с клавиатуры
     * @exception IOException - исключение ввода-вывода
     */
    public void onKeyPressed(KeyEvent keyEvent) throws IOException {
        mainHandler.onKeyPressed(wordsGridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    /** Устанавливает стили для отображения светлой темы основного окна приложения */
    public void mainWindowLight() {
        borderPane.setStyle("-fx-background-color: #212529");
        gameTitleLabel.setStyle("-fx-text-fill: white");
    }

    /** Устанавливает стили для отображения темной темы основного окна приложения */
    public void mainWindowDark() {
        borderPane.setStyle("-fx-background-color: white");
        gameTitleLabel.setStyle("-fx-text-fill: black");
    }
}