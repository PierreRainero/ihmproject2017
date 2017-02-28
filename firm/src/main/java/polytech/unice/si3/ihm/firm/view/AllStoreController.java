package polytech.unice.si3.ihm.firm.view;


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

import java.util.ArrayList;
import java.util.List;

public class AllStoreController extends BasicController {

    private Firm firm;

    public void setFirm(Firm firm){
        this.firm = firm;
    }

    @FXML
    private Button exit;

    @FXML
    private ListView<HBox> stores = new ListView<HBox>();


    /**
     * Method permitting to change the content of the listView
     */
    public void changeListView(){
        stores.setOrientation(Orientation.VERTICAL);
        ObservableList<HBox> storesInListView = FXCollections.observableArrayList();
        List<HBox> hBoxesOfStores = makeHboxOfStores();
        for (HBox hbox : hBoxesOfStores){
            storesInListView.add(hbox);
        }
        stores.setItems(storesInListView);
    }

    /**
     * Allow to create a list of HBox from the stores
     * @return the list of hboxes made from the stores
     */
    public List<HBox> makeHboxOfStores(){
        List<HBox> listViewItems = new ArrayList<HBox>();
        HBox hbox = new HBox();
        Label storeName;
        Label storeDescription;
        Label storeAddress;
        Label storeMallName;
        ImageView storeImage;
        for (Store store : firm.getStores()){
            storeName = new Label(store.getName());
            storeDescription = new Label(store.getDescription());
            storeMallName = new Label(store.getMallName());
            storeAddress = new Label(store.getAddress());

            if (store.getImage()==null){
                hbox.getChildren().addAll(storeName, storeMallName, storeAddress, storeDescription);
            }
            else {
                storeImage = new ImageView(store.getImage());
                hbox.getChildren().addAll(storeImage, storeName, storeMallName, storeAddress, storeDescription);
            }

            listViewItems.add(hbox);
        }
        return listViewItems;
    }




}
