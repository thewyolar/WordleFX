package com.wordle;

import com.wordle.controller.SettingsController;
import com.wordle.controller.StatisticsController;
import com.wordle.statistics.Statistics;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StatisticsWindow {

    public static void display() throws IOException {
        int[] statistics = Statistics.getStatistics();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/statistics-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StatisticsController statisticsController = fxmlLoader.getController();
        statisticsController.setPlayedGames(statistics[0]);
        statisticsController.setTotalWins(statistics[1]);
        statisticsController.setWinsInRowNow(statistics[2]);
        statisticsController.setWinsInRowMax(statistics[3]);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Статистика");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(StatisticsWindow.class.getResourceAsStream("image/icon.png")));
        stage.showAndWait();
    }
}
