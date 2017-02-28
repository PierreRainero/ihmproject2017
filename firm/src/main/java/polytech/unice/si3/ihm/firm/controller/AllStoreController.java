package polytech.unice.si3.ihm.firm.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import polytech.unice.si3.ihm.firm.model.Firm;

public class AllStoreController extends BasicController {

    private Firm firm;

    public void setFirm(Firm firm){
        this.firm = firm;
    }

    @FXML
    private Button exit;

    @FXML
    private ListView<HBox> stores = new ListView<HBox>();


    public void changeListView(){
    	
    }




}
