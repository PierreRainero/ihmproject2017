package polytech.unice.si3.ihm.firm.managing.controller;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.managing.json.JsonGeneratorStore;

public class AddStoreController {
	private JsonGeneratorStore jsonToGenerate;
	
    @FXML
    private TextField shopName;

    @FXML
    private Label nameShopError;

    @FXML
    private TextField shopAddress;

    @FXML
    private TextField shopCity;

    @FXML
    private TextField shopCodePostal;

    @FXML
    private TextField shopDepart;

    @FXML
    private ComboBox<?> shopRegion;

    @FXML
    private TextField shopMallName;

    @FXML
    private TextArea shopDescription;

    @FXML
    private Button shopImage;

    @FXML
    private Label imageSelected;

    @FXML
    private Button validShop;

    @FXML
    /**
     * {@inheritDoc}
     */
    public void initialize() {
    	jsonToGenerate = new JsonGeneratorStore();
    	nameShopError.setVisible(false);
    }
    
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
    
    @FXML
    private void validNewShop(ActionEvent event) {
    	if(shopName.getText().isEmpty()){
    		nameShopError.setVisible(true);
    		return;
    	}
    	jsonToGenerate.setShopName(shopName.getText());
    	nameShopError.setVisible(false);
    	
    	
    }

}