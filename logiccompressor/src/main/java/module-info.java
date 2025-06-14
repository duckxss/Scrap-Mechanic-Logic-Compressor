module com.logiccompressor {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;

    opens com.logiccompressor to javafx.fxml;
    exports com.logiccompressor;
    exports com.blueprinthierarchy to com.fasterxml.jackson.databind;

}
