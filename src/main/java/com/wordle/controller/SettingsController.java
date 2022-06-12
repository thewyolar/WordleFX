package com.wordle.controller;

import com.wordle.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.ToggleSwitch;

/** Класс контроллера для взаимодействия окна c настройками приложения с FXML */
public class SettingsController {

    /** mainController - ссылка на контроллер {@link MainController} */
    MainController mainController = MainApplication.getController();

    /** borderPane - контейнер со всеми элементами окна с настройками приложения */
    @FXML
    private BorderPane borderPane;

    /** darkThemeLabel - метка с темной темой */
    @FXML
    private Label darkThemeLabel;

    /** settingsTitle - метка с названием окна */
    @FXML
    private Label settingsTitle;

    /** contactLabel - метка с контактами */
    @FXML
    private Label contactLabel;

    /** darkThemeSwitcher - переключатель между темами приложения */
    @FXML
    private ToggleSwitch darkThemeSwitcher;

    /** emailHyperLink - гиперссылка на email */
    @FXML
    private Hyperlink emailHyperlink;

    /** telegramHyperlink - гиперссылка на telegram */
    @FXML
    private Hyperlink telegramHyperlink;

    /** wordleHyperlink - гиперссылка на оригинальную версию wordle */
    @FXML
    private Hyperlink wordleHyperlink;

    /**
     * Возвращает значение поля {@link SettingsController#emailHyperlink}
     * @return возвращает гиперссылку на email
     */
    public Hyperlink getEmailHyperlink() {
        return emailHyperlink;
    }

    /**
     * Возвращает значение поля {@link SettingsController#telegramHyperlink}
     * @return возвращает гиперссылку на telegram
     */
    public Hyperlink getTelegramHyperlink() {
        return telegramHyperlink;
    }

    /**
     * Возвращает значение поля {@link SettingsController#wordleHyperlink}
     * @return возвращает гиперссылку на оригинальную версию wordle
     */
    public Hyperlink getWordleHyperlink() {
        return wordleHyperlink;
    }

    /** Устанавливает стили для отображения светлой темы окна с настройками приложения */
    public void settingsWindowLight() {
        borderPane.setStyle("-fx-background-color: #212529");
        darkThemeLabel.setStyle("-fx-text-fill: white");
        settingsTitle.setStyle("-fx-text-fill: white");
        contactLabel.setStyle("-fx-text-fill: white");
    }

    /** Устанавливает стили для отображения темной темы окна с настройками приложения */
    public void settingsWindowDark() {
        borderPane.setStyle("-fx-background-color: white");
        darkThemeLabel.setStyle("-fx-text-fill: black");
        settingsTitle.setStyle("-fx-text-fill: black");
        contactLabel.setStyle("-fx-text-fill: black");
    }

    /** Переключает тему окна с настройками приложения */
    public void switchSettingsWindowTheme(MouseEvent mouseEvent) {
        if (darkThemeSwitcher.isSelected()) {
            settingsWindowLight();
            mainController.mainWindowLight();
        }
        else {
            settingsWindowDark();
            mainController.mainWindowDark();
        }
    }
}
