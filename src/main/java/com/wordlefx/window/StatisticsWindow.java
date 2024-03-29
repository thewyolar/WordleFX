package com.wordlefx.window;

import com.wordlefx.MainApplication;
import com.wordlefx.controller.StatisticsController;
import com.wordlefx.statistics.Statistics;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** Класс окна приложения со статистикой игры */
public class StatisticsWindow {

    /**
     * Отображает окно приложения со статистикой игры
     * @exception IOException - исключение ввода-вывода
     */
    public static void display() throws IOException {
        int[] statistics = Statistics.getStatistics();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/wordlefx/view/statistics-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StatisticsController statisticsController = fxmlLoader.getController();
        statisticsController.setStatisticsLabel(statistics[0]);
        statisticsController.setPlayedGames(statistics[0]);
        statisticsController.setTotalWins(statistics[1]);
        statisticsController.setWinsInRowNow(statistics[2]);
        statisticsController.setWinsInRowMax(statistics[3]);
        statisticsController.getShareButton().setOnMouseClicked(e -> setStatisticsToClipboard());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Статистика");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(StatisticsWindow.class.getResourceAsStream("/com/wordlefx/image/icon.png"))));
        stage.showAndWait();
    }

    /** Копирует статистику в буфер обмена устройства */
    public static void setStatisticsToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Статистика Wordle\n\n");
        stringBuilder.append(String.format("\uD83D\uDE80 Игр сыграно - %d\n", Statistics.getStatistics()[0]));
        stringBuilder.append(String.format("\uD83C\uDFC6 Побед всего - %d (%d%%)\n", Statistics.getStatistics()[1], Math.round((double)Statistics.getStatistics()[1] * 100 / Statistics.getStatistics()[0])));
        stringBuilder.append(String.format("\uD83C\uDFAF Подряд максимум - %d\n", Statistics.getStatistics()[3]));
        stringBuilder.append("#вордли\n\n");
        stringBuilder.append("https://github.com/thewyolar/WordleFX");

        content.putString(stringBuilder.toString());
        clipboard.setContent(content);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText("Результаты статистики скопированы в буфер обмена!");
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Objects.requireNonNull(StatisticsWindow.class.getResourceAsStream("/com/wordlefx/image/icon.png"))));
        alert.showAndWait();
    }
}
