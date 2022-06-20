package com.thewyolar.wordlefx;

import com.thewyolar.wordlefx.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/** Класс основного окна приложения */
public class MainApplication extends Application {

    /** dictionaryWords - список слов из словаря */
    private static final ArrayList<String> dictionaryWords = new ArrayList<>();

    /** mainControllerReference - ссылка на контроллер {@link MainController} */
    private static MainController mainControllerReference;

    /** stageReference - ссылка на основное окно приложения */
    private static Stage stageReference;

    /**
     * Метод для запуска приложения
     * @param stage - графическое окно приложения
     * @exception IOException - исключение ввода-вывода
     */
    @Override
    public void start(Stage stage) throws IOException {
        initializeWordList();
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController mainController = fxmlLoader.getController();
        mainControllerReference = mainController;
        mainController.getRandomWord();
        mainController.getGameTitleLabel().setOnMouseClicked(e -> getHostServices().showDocument("https://github.com/thewyolar/WordleFX"));

        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getProperties().put("hostServices", this.getHostServices());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icon.png"))));
        stage.show();

        scene.setOnKeyPressed(keyEvent -> {
            try {
                mainController.onKeyPressed(keyEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Возвращает значение поля {@link MainApplication#stageReference}
     * @return возвращает ссылку на основное окно приложения
     */
    public static Stage getStage() {
        return stageReference;
    }

    /**
     * Возвращает значение поля {@link MainApplication#mainControllerReference}
     * @return возвращает ссылку на контроллер основного окна приложения
     */
    public static MainController getController() {
        return mainControllerReference;
    }

    /**
     * Возвращает значение поля {@link MainApplication#dictionaryWords}
     * @return возвращает список слов из словаря
     */
    public static ArrayList<String> getDictionaryWords() {
        return dictionaryWords;
    }

    /** Метод для выхода из приложения */
    public static void quit() {
        stageReference.close();
    }

    /** Запускает метод {@link AlertWindow#display(Stage)} */
    public static void showAlert() {
        AlertWindow.display(stageReference);
    }

    /** Иницилизирует список слов {@link MainApplication#dictionaryWords} */
    public void initializeWordList() {
        try {
            File file = new File("src/main/resources/com/thewyolar/wordlefx/dictionary.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                dictionaryWords.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}