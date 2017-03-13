package polytech.unice.si3.ihm.firm.managing.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

public class ModifyStoreWindowController extends ModifyWindowController {

    @FXML
    private TextField storeName;

    @FXML
    private Label storeNameMissing;

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


    @FXML
    public void initialize(){
        storeNameMissing.setVisible(false);

    }

    @FXML
    void cancelTheWindow(MouseEvent event) {

    }

    @FXML
    void resetAllFields(MouseEvent event) {

    }

    @FXML
    void saveModifications(MouseEvent event) {
        if (hasAName()){

        }
        else{
            storeNameMissing.setVisible(true);
        }
    }

    @FXML
    void searchComputer(MouseEvent event) {

    }

    private boolean hasAName(){
        return !storeName.getText().trim().isEmpty()|| !storeName.getText().trim().equals("");
    }

    public void setStore(Store store){
        this.store=store;
    }



}
