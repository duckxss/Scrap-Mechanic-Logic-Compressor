package com.logiccompressor;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class DownloadController {
    @FXML private ScrollPane copyBox;
    @FXML private Text jsonText;

    @FXML
    private void handleSaveFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("blueprint.json"); // or .txt, etc.
        fileChooser.setInitialDirectory(HomeController.selectedFile.getParentFile());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        // Get a handle to the current window (Stage)
        Stage stage = (Stage) copyBox.getScene().getWindow();

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, HomeController.orgBlueprint);

        }
    }

    @FXML
    private void handleCopyText() {
        jsonText.setText(HomeController.blueprintText);
        copyBox.setVisible(true);
        jsonText.setVisible(true);

    }
}
