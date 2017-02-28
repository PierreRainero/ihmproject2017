package polytech.unice.si3.ihm.firm.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import polytech.unice.si3.ihm.firm.model.Firm;
import polytech.unice.si3.ihm.firm.model.Store;

public class AllStoreController extends BasicController {

    private Firm firm;

    public void setFirm(Firm firm){
        this.firm = firm;
    }

    @FXML
    private Button exit;

    @FXML
    private ListView<String> stores = new ListView<String>();


    public void changeListView(){
        stores.setOrientation(Orientation.VERTICAL);
        ObservableList<String> storesInListView = FXCollections.observableArrayList();
        for (Store store : firm.getStores()){
            storesInListView.add(store.getName());
        }
        stores.setItems(storesInListView);


    }




}
