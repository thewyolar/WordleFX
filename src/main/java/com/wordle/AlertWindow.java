package com.wordle;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;

public class AlertWindow {

    public static void display(Stage ownerStage) {
        Stage stage = new Stage();
        stage.initOwner(ownerStage);
        stage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text("В словаре игры нет такого слова, \nпопробуйте другое!");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 16px;");

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 3; " + "-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 10;");
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(750), root);
        fadeTransition.setFromValue(0.75);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(event -> stage.close());
        fadeTransition.play();
    }
}
