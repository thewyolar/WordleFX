package com.wordlefx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Класс контроллера для взаимодействия окна приложения со статистикой игры с FXML */
public class StatisticsController {

    /** Метка с кол-вом сыгранных игр */
    @FXML
    private Label playedGamesLabel;

    /** Метка с номером загаданного слова */
    @FXML
    private Label statisticsLabel;

    /** Метка с кол-вом победных игр */
    @FXML
    private Label totalWinsLabel;

    /** Метка с кол-вом подряд сыгранных победных игр в последней попытке */
    @FXML
    private Label winsInRowNowLabel;

    /** Метка с максимальным кол-вом подряд сыгранных победных игр */
    @FXML
    private Label winsInRowMaxLabel;

    /** shareButton - кнопка для копирования статистики игры в буфер обмена устройства */
    @FXML
    private Button shareButton;

    /**
     * Возвращает значение поля {@link StatisticsController#shareButton}
     * @return возвращает ссылку на кнопку для копирования статистики игры в буфер обмена устройства
     */
    public Button getShareButton() {
        return shareButton;
    }

    /**
     * Устанавливает значение в качестве текста метки {@link StatisticsController#playedGamesLabel}
     * @param value - кол-во сыгранных игр
     */
    public void setPlayedGames(int value) {
        playedGamesLabel.setText(String.valueOf(value));
    }

    /**
     * Устанавливает значение в качестве текста метки {@link StatisticsController#totalWinsLabel}
     * @param value - кол-во победных игр
     */
    public void setTotalWins(int value) {
        totalWinsLabel.setText(String.valueOf(value));
    }

    /**
     * Устанавливает значение в качестве текста метки {@link StatisticsController#winsInRowNowLabel}
     * @param value - кол-во подряд сыгранных победных игр в последней попытке
     */
    public void setWinsInRowNow(int value) {
        winsInRowNowLabel.setText(String.valueOf(value));
    }

    /**
     * Устанавливает значение в качестве текста метки {@link StatisticsController#winsInRowMaxLabel}
     * @param value - максимальное кол-во подряд сыгранных победных игр
     */
    public void setWinsInRowMax(int value) {
        winsInRowMaxLabel.setText(String.valueOf(value));
    }

    /**
     * Устанавливает значение в качестве текста метки {@link StatisticsController#statisticsLabel}
     * @param value - номер загаданного слова
     */
    public void setStatisticsLabel(int value) {
        String s = statisticsLabel.getText();
        statisticsLabel.setText(s + value);
    }
}
