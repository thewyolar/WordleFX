package com.wordle;

import com.wordle.controller.MainController;
import com.wordle.controller.ResultController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class ResultWindow {

    private static boolean resetGame = false;
    private static boolean quitApplication = false;

    public static boolean getResetGame() { return resetGame; }

    public static void setResetGame(boolean value) { resetGame = value; }

    public static boolean getQuitApplication() { return quitApplication; }

    public static void display(boolean guessed, String winningWord) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader fxmlLoader = new FXMLLoader(ResultWindow.class.getResource("view/result-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController mainController = MainApplication.getController();
        mainController.getBorderPane().setBackground(Background.fill(Color.rgb(17, 24, 39)));
        mainController.invisibleMainApplication();

        ResultController resultController = fxmlLoader.getController();
        resultController.createResultGrid();

        Label winningWordLabel = resultController.getWinningWordLabel();
        if (!guessed) {
            winningWordLabel.setVisible(true);
            winningWordLabel.setStyle("-fx-font-size: 16px;");
            winningWordLabel.setText("Загаданное слово: " + winningWord.toUpperCase());
        }

        /*Button playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.getStyleClass().setAll("btn", "btn-primary");
        playAgainButton.setOnMouseClicked(me -> {
            resetGame = true;
            stage.close();
        });

        Button quitButton = new Button("  QUIT");
        quitButton.getStyleClass().setAll("btn", "btn-warning");
        quitButton.setOnMouseClicked(me -> {
            resetGame = false;
            quitApplication = true;
            stage.close();
        });*/

        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(ResultWindow.class.getResourceAsStream("image/icon.png"))));
        stage.showAndWait();
    }
}
