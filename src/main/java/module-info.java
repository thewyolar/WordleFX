module com.wordlefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.wordlefx to javafx.fxml;
    exports com.wordlefx;
    exports com.wordlefx.controllers;
    opens com.wordlefx.controllers to javafx.fxml;
}