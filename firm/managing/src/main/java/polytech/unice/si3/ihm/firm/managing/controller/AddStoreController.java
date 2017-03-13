package polytech.unice.si3.ihm.firm.managing.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.common.util.Log;
import polytech.unice.si3.ihm.firm.common.util.Regex;
import polytech.unice.si3.ihm.firm.managing.content.RegionEnum;
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
    private Label adressShopError;

    @FXML
    private TextField shopCity;

    @FXML
    private Label cityShopError;

    @FXML
    private TextField shopCodePostal;

    @FXML
    private TextField shopDepart;

    @FXML
    private ComboBox<String> shopRegion;

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
    private Label postalShopError;
    
    @FXML
    private Label departShopError;
    
    @FXML
    private Label mallShopError;

    @FXML
    /**
     * {@inheritDoc}
     */
    public void initialize() {
    	jsonToGenerate = new JsonGeneratorStore();
    	
    	initializeCombobox();
    	
    	nameShopError.setVisible(false);
    	adressShopError.setVisible(false);
    	cityShopError.setVisible(false);
    	postalShopError.setVisible(false);
    	departShopError.setVisible(false);
    	mallShopError.setVisible(false);
    	
    	Log.info(this.getClass(), "Content charged");
    }
    
    /**
     * Allow to populate the combobox of regions
     */
    private void initializeCombobox(){
        ObservableList<String> regionsName = FXCollections.observableArrayList();
        for (RegionEnum sorting : RegionEnum.values()){
        	regionsName.add(sorting.getRegionName());
        }
        shopRegion.setItems(regionsName);
        shopRegion.setPromptText(RegionEnum.AUVERGNE_RHONE_ALPES.getRegionName());
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
    private void validNewShop(ActionEvent event) throws FileNotFoundException, IOException, ParseException {
    	if(!checkMini())
    		return;
    	
    	jsonToGenerate.setShopRegion(shopRegion.getValue());
    	jsonToGenerate.setShopDescription(shopDescription.getText());
    	jsonToGenerate.generate();

    }
    
    private boolean checkMini(){
    	boolean name, adress, city, postalCode, depart, mallName;
    	
    	name = isNamePresent();
    	adress = isAdressPresent();
    	city = isCityPresent();
    	postalCode = isPostalCorrect();
    	depart = isDepartPresent();
    	mallName = isMallNamePresent();
    	
    	return name && adress && city && postalCode && depart && mallName;
    }
    
    private boolean isNamePresent(){
    	if(shopName.getText().isEmpty()){
    		nameShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopName(shopName.getText());
    	nameShopError.setVisible(false);
    	return true;
    }
    
    private boolean isAdressPresent(){
    	if(shopAddress.getText().isEmpty()){
    		adressShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopAdress(shopAddress.getText());
    	adressShopError.setVisible(false);
    	return true;
    }
    
    private boolean isCityPresent(){
    	if(shopCity.getText().isEmpty()){
    		cityShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopCity(shopCity.getText());
    	cityShopError.setVisible(false);
    	return true;
    }
    
    private boolean isPostalCorrect(){
    	if(shopCodePostal.getText().isEmpty() || !Regex.isPostalCode((shopCodePostal.getText()))){
    		postalShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopPostal(shopCodePostal.getText());
    	postalShopError.setVisible(false);
    	return true;
    }
    
    private boolean isDepartPresent(){
    	if(shopDepart.getText().isEmpty()){
    		departShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopCity(shopDepart.getText());
    	departShopError.setVisible(false);
    	return true;
    }
    
    private boolean isMallNamePresent(){
    	if(shopMallName.getText().isEmpty()){
    		mallShopError.setVisible(true);
    		return false;
    	}
    	jsonToGenerate.setShopMallName(shopMallName.getText());
    	mallShopError.setVisible(false);
    	return true;
    }

}