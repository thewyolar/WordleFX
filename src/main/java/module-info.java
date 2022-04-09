module com.wordle.wordlefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.wordle to javafx.fxml;
    exports com.wordle;
    exports com.wordle.controller;
    opens com.wordle.controller to javafx.fxml;
}