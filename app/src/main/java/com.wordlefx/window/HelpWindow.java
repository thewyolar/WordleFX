package com.wordlefx.window;

import com.wordlefx.MainApplication;
import com.wordlefx.controller.HelpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/** Класс окна приложения с правилами игры */
public class HelpWindow {

    /** helpControllerReference - ссылка на контроллер {@link HelpController} */
    private static HelpController helpControllerReference;

    /**
     * Возвращает значение поля {@link HelpWindow#helpControllerReference}
     * @return возвращает ссылку на контроллер окна приложения с правилами игры
     */
    public static HelpController getController() {
        return helpControllerReference;
    }

    /**
     * Отображает окно приложения с правилами игры
     * @exception IOException - исключение ввода-вывода
     */
    public static void display() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/wordlefx/view/help-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HelpController helpController = fxmlLoader.getController();
        helpControllerReference = helpController;
        helpController.getGameButton().setOnMouseClicked(mouseEvent -> stage.close());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Правила игры");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(HelpWindow.class.getResourceAsStream("/com/wordlefx/image/icon.png"))));
        stage.showAndWait();
    }
}
