package polytech.unice.si3.ihm.firm.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
    private ListView<Store> stores;


    /**
     * Method permitting to change the content of the listView
     */
    public void changeListView(){
        ObservableList<Store> storesInListView = makeObservableList();
        stores.setPlaceholder(new Label("List is empty"));
        stores.setOrientation(Orientation.VERTICAL);
        stores.setItems(storesInListView);
        stores.setCellFactory(new Callback<ListView<Store>, ListCell<Store>>() {
            public ListCell<Store> call(ListView<Store> param) {
                return new ListCell<Store>(){

                    @Override
                    protected void updateItem(Store store, boolean empty){
                        super.updateItem(store, empty);
                        if (!empty){
                            VBox vbox = new VBox(new Label(store.getName()),
                                    new Label(store.getDescription()),
                                    new Label(store.getMallName()),
                                    new Label(store.getAddress()));
                            HBox hBox;
                            if (store.getImage()==null){
                                hBox = new HBox(new Label("No image"), vbox);
                            }
                            else{
                                ImageView storeImage = new ImageView(store.getImage());
                                storeImage.setPreserveRatio(true);
                                storeImage.setFitHeight(50);
                                storeImage.setFitWidth(50);
                                hBox = new HBox(storeImage, vbox);
                            }
                            hBox.setSpacing(30.0);
                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

    }


    /**
     * Method allowing to make an observable list of stores
     * @return the observable list of stores
     */
    public ObservableList<Store> makeObservableList(){
        ObservableList<Store> stores = FXCollections.observableArrayList();
        for (Store store : firm.getStores()){
            stores.addAll(store);
        }
        return stores;
    }




}
