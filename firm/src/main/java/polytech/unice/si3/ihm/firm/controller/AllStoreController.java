package polytech.unice.si3.ihm.firm.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.model.commercial.Store;
import polytech.unice.si3.ihm.firm.model.sorting.SortingEnum;

public class AllStoreController extends BasicController {

    private Firm firm;

    public void setFirm(Firm firm){
        this.firm = firm;
    }

    @FXML
    private Button exit;

    @FXML
    private ListView<Store> stores;

    @FXML
    private ComboBox<String> sortingMethods;




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
                                    new Label(store.getAddress()),
                                    new Label(store.getCity()),
                                    new Label(store.getCityNumber()));
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

    /**
     * Allow to populate the combobox of sorting methods
     */
    public void populateCombobox(){
        ObservableList<String> sortingMethodsList=FXCollections.observableArrayList();
        for (SortingEnum sorting : SortingEnum.values()){
            sortingMethodsList.add(sorting.getSortingName());
        }
        sortingMethods.setItems(sortingMethodsList);
    }


    /**
     * Method that will call the corresponding sorting method depending on what has been chosen in the comboBox
     * @param event the event
     */
    public void sortWithTheSelectedSortingMethod(MouseEvent event){
        //TODO
    }



}
