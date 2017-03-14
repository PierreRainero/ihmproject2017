package polytech.unice.si3.ihm.firm.managing.controller;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.common.controller.BasicController;
import polytech.unice.si3.ihm.firm.managing.json.JsonGeneratorStore;

public class FileChooserController extends BasicController {
	protected JsonGeneratorStore jsonToGenerate;

    @FXML
    protected Label imageSelected;
	
    @FXML
    private void choseImage(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Sélectionner une image");
    	
    	FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Images (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
    	fileChooser.getExtensionFilters().add(imageFilter);
   
    	File tempo = fileChooser.showOpenDialog(new Stage());
    	
    	if(tempo!=null)
    		jsonToGenerate.setFileSelected(Optional.of(tempo));
    	
    	if(jsonToGenerate.getFileSelected().isPresent())
    		imageSelected.setText(jsonToGenerate.getFileSelected().get().getName());
    }
}
