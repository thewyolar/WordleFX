package com.wordle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpWindow {

    public static void display() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/help-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Правила игры");
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelpWindow.class.getResourceAsStream("image/icon.png")));
        stage.showAndWait();
    }

}
