package com.wordle;

import com.wordle.controller.SettingsController;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsWindow {

    public static void display() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HostServices hostServices = (HostServices)MainApplication.getStage().getProperties().get("hostServices");
        SettingsController settingsController = fxmlLoader.getController();
        settingsController.getWordleHyperlink().setOnAction(actionEvent -> hostServices.showDocument("https://www.nytimes.com/games/wordle/index.html"));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Настройки");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelpWindow.class.getResourceAsStream("image/icon.png")));
        stage.showAndWait();
    }
}
