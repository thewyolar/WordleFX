module com.wordle.wordlefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.wordle to javafx.fxml;
    exports com.wordle;
}