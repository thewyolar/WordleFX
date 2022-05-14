package com.wordle;

import com.wordle.controller.HelpController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class HelpWindow {

    private static HelpController helpControllerReference;

    public static void display() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/help-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HelpController helpController = fxmlLoader.getController();
        helpControllerReference = helpController;
        helpController.getGameButton().setOnMouseClicked(mouseEvent -> stage.close());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Правила игры");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(HelpWindow.class.getResourceAsStream("image/icon.png"))));
        stage.showAndWait();
    }

    public static HelpController getController() { return helpControllerReference; }
}
