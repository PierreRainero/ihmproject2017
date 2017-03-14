package polytech.unice.si3.ihm.firm.managing.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import polytech.unice.si3.ihm.firm.common.exceptions.ContentException;
import polytech.unice.si3.ihm.firm.common.json.JsonReceiver;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.common.util.Log;
import polytech.unice.si3.ihm.firm.managing.json.JsonGeneratorStore;

public class ModifyStoreWindowController extends FileChooserController {
	private static final String PATH = System.getProperties().get("user.dir")+"/../datas/content.json";
	private ModifyStoreController callerController;
	
    @FXML
    private Label shopName;

    @FXML
    private TextArea storeDescription;

    @FXML
    private TextField imagePath;

    @FXML
    private Button searchComputerButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    private Store store;

    /**
     * {@inheritDoc}
     */
    public void initContent(Object obj){
    	if(obj instanceof Store)
    		store = (Store) obj;
    	else
    		return;
    	
    	resetValues();
    }
    
    public void setCaller(ModifyStoreController caller){
    	callerController = caller;
    }
    
    private void resetValues(){
    	jsonToGenerate = new JsonGeneratorStore(store);
    	shopName.setText(store.getName());
    	storeDescription.setText(store.getDescription());
    	if(jsonToGenerate.getFileSelected().isPresent())
    		imageSelected.setText(jsonToGenerate.getFileSelected().get().getName());
    }

    @FXML
    private void resetAllFields(MouseEvent event) {
    	resetValues();
    }

    @FXML
    @SuppressWarnings("unchecked")
    void saveModifications(MouseEvent event) throws IOException, ParseException, ContentException {
    	JSONObject firmJson = JsonReceiver.getJsonFirm(PATH);
        JSONArray shops = (JSONArray)firmJson.get("shops");
        int indexToChange = findMeShopIndex(shops);
        
        shops.set(indexToChange, getStoreJson());
        
		File file = new File(PATH);
		if (!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(firmJson.toString());
		writer.flush();
		writer.close();
		
		Log.info(this.getClass(), "JSON modifie");
		callerController.populateListView();
    }
    
    private JSONObject getStoreJson(){
    	jsonToGenerate.setShopDescription(storeDescription.getText());
    	
    	return jsonToGenerate.getStoreJson();
    }
    
    private int findMeShopIndex(JSONArray shops){
    	for(int i=0; i<shops.size();i++)
    		if(((JSONObject) shops.get(i)).get("name").equals(shopName.getText()))
    			return i;
    	return -1;
    }

    @FXML
    void searchComputer(MouseEvent event) {

    }
}
