package com.wordlefx;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/** Класс окна приложения с предупреждением об отсутствии введенного слова в словаре игры */
public class AlertWindow {

    /**
     * Отображает окно с предупреждением
     * @param ownerStage - ссылка на родительское окно
     * @param message - сообщение, которое нужно отобразить
     */
    public static void display(Stage ownerStage, String message) {
        Stage stage = new Stage();
        stage.initOwner(ownerStage);
        stage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(message);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 16px;");

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 3; -fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 10;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(750), root);
        fadeTransition.setFromValue(0.75);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(event -> stage.close());
        fadeTransition.play();
    }
}
