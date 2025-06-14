package com.logiccompressor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blueprinthierarchy.Blueprint;
import com.blueprinthierarchy.Body;
import com.blueprinthierarchy.Child;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class HomeController {
    @FXML private CheckBox sortCounterBlocks;
    @FXML private CheckBox sortSmartEngines;
    @FXML private CheckBox sortButtonsSwitches;
    @FXML private CheckBox toggleInputs;
    @FXML private TextField xInput;
    @FXML private TextField yInput;
    @FXML private TextField zInput;
    @FXML private TextField filePathField;
    @FXML private GridPane boundsGrid;
    @FXML private Label warningText;
    
    private final int MINcoord = 1;
    private final int MAXcoord = 1000;

    private static Scene scene;

    public static File selectedFile;
    public static String blueprintText;
    public static Blueprint orgBlueprint;

    @FXML
    public void initialize() {
        boundInput(xInput);
        boundInput(yInput);
        boundInput(zInput);

        boundsGrid.visibleProperty().bind(toggleInputs.selectedProperty());
        boundsGrid.managedProperty().bind(toggleInputs.selectedProperty());

        warningText.visibleProperty().bind(toggleInputs.selectedProperty());
        warningText.managedProperty().bind(toggleInputs.selectedProperty());
    }

    public void boundInput(TextField numberField){
        // Allow only digits
        numberField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                numberField.setText(oldVal); // revert to old value
            }
        });

        // Clamp value on focus loss
        numberField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                String text = numberField.getText();
                if (!text.isEmpty()) {
                    int value = Integer.parseInt(text);
                    value = Math.max(MINcoord, Math.min(MAXcoord, value)); // clamp
                    numberField.setText(String.valueOf(value));
                }
            }
        });
    }

    @FXML
    private void handleBrowseAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        
        // You can set filters if needed
        fileChooser.getExtensionFilters().add(
           new FileChooser.ExtensionFilter("Blueprint JSON files", "*.json")
        );
        
        File file = fileChooser.showOpenDialog(getStage());
        if (file != null) {
            selectedFile = file;
            filePathField.setText(file.getPath());
        }
    }
    
    @FXML
    private void handleSubmitAction() throws IOException {
        try {
            
            
            
            boolean sortCounters = sortCounterBlocks.isSelected();
            boolean sortEngines = sortSmartEngines.isSelected();
            boolean sortButtonsAndSwitches = sortButtonsSwitches.isSelected();
        
            // Process the form data here
            ObjectMapper mapper = new ObjectMapper();
            
            try {
                orgBlueprint = mapper.readValue(selectedFile, Blueprint.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            List<Child> counters = new ArrayList<>();
            List<Child> buttonSwitch = new ArrayList<>();
            List<Child> nonCounters = new ArrayList<>();
            List<Child> smartEng = new ArrayList<>();

            Body body = orgBlueprint.getBodies().get(0);

            Collections.sort(body.childs, (c1, c2) -> (c1.color.compareTo(c2.color)));

            for(Child c : body.getChilds()){
                if(c.bounds == null){
                    if((c.shapeId.equals("7cf717d7-d167-4f2d-a6e7-6b2c70aa3986") || c.shapeId.equals("1e8d93a4-506b-470d-9ada-9c0a321e2db5")) && sortButtonsAndSwitches)
                        buttonSwitch.add(c);
                    else if(c.shapeId.equals("d8296109-2ffb-4efb-819a-54bd8cadf549") && sortCounters)
                        counters.add(c);
                    else if(c.shapeId.equals("fe8978ec-5798-4b24-bdb2-8375387f171b") && sortEngines)
                        smartEng.add(c);
                    else
                        nonCounters.add(c);
                }
            }

            body.childs.clear();
            body.childs.addAll(smartEng);
            body.childs.addAll(nonCounters);
            body.childs.addAll(counters);
            body.childs.addAll(buttonSwitch);

            body.getChilds().get(0).setPos(0, 0, 0);
            
            int cbrtSize = (int)Math.ceil(Math.cbrt(body.getChilds().size()));
            int userX = cbrtSize;
            int userY = cbrtSize;
            int userZ = cbrtSize;
            if(toggleInputs.isSelected()){
                userX = Integer.parseInt(xInput.getText());
                userY = Integer.parseInt(yInput.getText());
                userZ = Integer.parseInt(zInput.getText());
            }

            int index = 0;
            for(int y = 0; y < userY && index < body.getChilds().size(); y++){
                for(int z = 0; z < userZ && index < body.getChilds().size(); z++){
                    for(int x = 0; x < userX && index < body.getChilds().size(); x++){
                        Child child = body.getChilds().get(index++);
                        child.setPos(x, y, z);
                        child.setXaxis(0);
                        child.setZaxis(0);
                    }
                }
            }

            int count = 0;
            for(Child c : orgBlueprint.getBodies().get(0).getChilds()){
                count++;
                System.out.println(count + " " + c.getPos().toString());
            }
            
            blueprintText = mapper.writeValueAsString(orgBlueprint);
                
        } catch (NumberFormatException e) {
            // Handle invalid number input
            System.err.println("Please enter valid integer values for X, Y, and Z");
        }
        newStage("download", 640, 480, "blueprint.json");
    }

    private void newStage(String fxml, int width, int height, String title) throws IOException{
        scene = new Scene(App.loadFXML(fxml), width, height);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    private Stage getStage() {
        // This is a helper method to get the current stage
        return (Stage) xInput.getScene().getWindow();
    }
}
