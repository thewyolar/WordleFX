module com.thewyolar.wordlefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.thewyolar.wordlefx to javafx.fxml;
    exports com.thewyolar.wordlefx;
    exports com.thewyolar.wordlefx.controllers;
    opens com.thewyolar.wordlefx.controllers to javafx.fxml;
}