package polytech.unice.si3.ihm.firm.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent a firm
 *
 * Created by SERRANO Simon on 18/02/2017.
 */
public class Firm {

    private String name;
    private String description;
    private String linkForMoreInfo;
    private File logo;
    private File banner;
    private List<Store> stores;

    /**
     * Default constructor for a firm
     */
    public Firm() {
        stores = new ArrayList<Store>();
    }

    /**
     * Constructor for a firm
     * @param name the name of the firm
     * @param description the description of the firm
     */
    public Firm(String name, String description) {
        this.name = name;
        this.description = description;
        stores = new ArrayList<Store>();
    }

    /**
     * Allow to change the name of a firm
     * @param newName the new name
     */
    public void changeName(String newName){
        name = newName;
    }

    /**
     * Allow to change the description of a firm
     * @param newDescription the new description
     */
    public void changeDecription(String newDescription){
        description = newDescription;
    }


    /**
     * allow to know if the firm has at least one store
     * @return true if the firm has 0 stores
     */
    public boolean hasNoStores(){
        return stores.isEmpty();
    }

    /**
     * Allow to add a store to the firm
     * @param store the store to add
     */
    public void addStore(Store store){
        stores.add(store);
    }


    /**
     * Make a ListView for all stores
     * @return the listView for all stores
     */
    public ListView<HBox>  showAllStoresInAListView(){
        ListView<HBox> allStores = new ListView<HBox>();
        HBox hbox;
        ObservableList<HBox> storesList = FXCollections.<HBox>observableArrayList();
        for (Store store : stores){
            hbox = makeHboxFromStore(store);
            storesList.add(hbox);
        }
        allStores.getItems().addAll(storesList);

        return allStores;
    }


    /**
     * method to make a HBox from on store adding information about the store name, its address, its description...
     * @param store the store to transform into hbox
     * @return the hbox made from the store
     */
    public HBox makeHboxFromStore(Store store){
        HBox hbox = new HBox();
        ObservableList<Label> labelsDescribingAStore =  FXCollections.<Label>observableArrayList(new Label(store.getName()),
                                                                                                new Label(store.getAddress()),
                                                                                                new Label(store.getDescription()),
                                                                                                new Label(store.getMallName()));
        hbox.getChildren().addAll(labelsDescribingAStore);
        return hbox;
    }

    /**
     * Getter for the name of the firm
     * @return the name of the firm
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of the firm
     * @return the description of the firm
     */
    public String getDescription() {
        return description;
    }
}
