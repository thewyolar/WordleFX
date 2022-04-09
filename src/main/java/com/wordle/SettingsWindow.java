package com.wordle;

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
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Настройки");
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelpWindow.class.getResourceAsStream("image/icon.png")));
        stage.showAndWait();
    }
}