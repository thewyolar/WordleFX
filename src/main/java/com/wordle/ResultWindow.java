package com.wordle;

import com.wordle.controller.ResultController;
import com.wordle.statistics.Statistics;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ResultWindow {

    private static boolean resetGame = false;

    private static boolean quitApplication = false;

    private static ResultController resultControllerReference;

    public static boolean getResetGame() { return resetGame; }

    public static void setResetGame(boolean value) { resetGame = value; }

    public static boolean getQuitApplication() { return quitApplication; }

    public static void display(boolean guessed, String winningWord) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(ResultWindow.class.getResource("view/result-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ResultController resultController = fxmlLoader.getController();
        resultControllerReference = resultController;
        resultController.createResultGrid();
        resultController.getShareButton().setOnMouseClicked(e -> setResultsToClipboard());

        Label winningWordLabel = resultController.getWinningWordLabel();
        if (!guessed) {
            winningWordLabel.setVisible(true);
            winningWordLabel.setStyle("-fx-font-size: 16px;");
            winningWordLabel.setText("Загаданное слово: " + winningWord.toUpperCase());
        }

        resultController.getRestartButton().setOnMouseClicked(me -> {
            resetGame = true;
            stage.close();
        });

        resultController.getQuitButton().setOnMouseClicked(me -> {
            resetGame = false;
            quitApplication = true;
            stage.close();
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(ResultWindow.class.getResourceAsStream("image/icon.png"))));
        stage.showAndWait();
    }

    public static void setResultsToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Игра Wordle Слово #%d\n\n", Statistics.getStatistics()[0]));
        stringBuilder.append(resultControllerReference.readResultGrid());
        stringBuilder.append("\n#вордли\n\n");
        stringBuilder.append("https://github.com/thewyolar/WordleFX");

        content.putString(stringBuilder.toString());
        clipboard.setContent(content);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Уведомление");
        alert.setHeaderText(null);
        alert.setContentText("Текст результатов скопирован в буфер обмена!");

        alert.showAndWait();
    }
}
