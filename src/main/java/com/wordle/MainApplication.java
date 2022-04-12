package com.wordle;

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

    public static final ArrayList<String> dictionaryWords = new ArrayList<String>();
    private static Stage stageReference;

    public static Stage getStage() { return stageReference; }

    @Override
    public void start(Stage stage) throws IOException {
        initializeWordList();
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.getProperties().put("hostServices", this.getHostServices());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icon.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void quit() {
        stageReference.close();
    }

    public void initializeWordList() {
        InputStream dictionary = getClass().getResourceAsStream("dictionary.txt");
        Stream<String> dictionary_lines = new BufferedReader(new InputStreamReader(dictionary)).lines();
        dictionary_lines.forEach(dictionaryWords::add);
    }
}