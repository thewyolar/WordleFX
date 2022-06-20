package com.thewyolar.wordlefx;

import com.thewyolar.wordlefx.controllers.ResultController;
import com.thewyolar.wordlefx.statistics.Statistics;
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

/** Класс окна приложения с результатами игры */
public class ResultWindow {

    /** Флаг, указывающий на перезапуск игры */
    private static boolean resetGame = false;

    /** Флаг, указывающий на выход из приложения */
    private static boolean quitApplication = false;

    /** resultControllerReference - ссылка на контроллер {@link ResultController} */
    private static ResultController resultControllerReference;

    /**
     * Возвращает значение поля {@link ResultWindow#resetGame}
     * @return возвращает значение флага для перезапуска игры
     */
    public static boolean getResetGame() {
        return resetGame;
    }

    /**
     * Устанавливает значение полю {@link ResultWindow#resetGame}
     * @param value - логическое значение true или false
     */
    public static void setResetGame(boolean value) {
        resetGame = value;
    }

    /**
     * Возвращает значение поля {@link ResultWindow#quitApplication}
     * @return возвращает значение флага для выхода из приложения
     */
    public static boolean getQuitApplication() {
        return quitApplication;
    }

    /**
     * Отображает окно приложения с результатами игры
     * @param guessed - флаг, указывающий угадано ли слово (угадано - true, иначе - false)
     * @param winningWord - загаданное слово
     * @exception  IOException - исключение ввода-вывода
     */
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

    /** Копирует результаты в буфер обмена устройства */
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
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(StatisticsWindow.class.getResourceAsStream("image/icon.png")));
        alert.showAndWait();
    }
}
