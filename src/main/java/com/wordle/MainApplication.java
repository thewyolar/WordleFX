package com.wordle;

import com.wordle.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class MainApplication extends Application {

    private static final ArrayList<String> dictionaryWords = new ArrayList<>();
    private static Stage stageReference;

    @Override
    public void start(Stage stage) throws IOException {
        initializeWordList();
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController mainController = fxmlLoader.getController();
        mainController.getRandomWord();

        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.getProperties().put("hostServices", this.getHostServices());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icon.png"))));
        stage.show();

        scene.setOnKeyReleased(keyEvent -> mainController.onKeyPressed(keyEvent));
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stageReference; }

    public static ArrayList<String> getDictionaryWords() {
        return dictionaryWords; }

    public static void quit() {
        stageReference.close(); }

    public static void showAlert() {
        AlertWindow.display(stageReference); }

    public void initializeWordList() {
        InputStream dictionary = getClass().getResourceAsStream("dictionary.txt");
        Stream<String> dictionary_lines = new BufferedReader(new InputStreamReader(dictionary)).lines();
        dictionary_lines.forEach(dictionaryWords::add);
    }
}