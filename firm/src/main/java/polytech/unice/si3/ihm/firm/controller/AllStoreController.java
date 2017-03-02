package polytech.unice.si3.ihm.firm.controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.model.commercial.Store;
import polytech.unice.si3.ihm.firm.model.sorting.SortListViewItemByCity;
import polytech.unice.si3.ihm.firm.model.sorting.SortingEnum;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;

import java.util.List;

public class AllStoreController extends BasicController {
    private Firm firm;
    
    @FXML
    private ImageView logo;

    @FXML
    private ImageView firmImageName;

    @FXML
    private Button exit;

    @FXML
    private ListView<Store> stores;

    @FXML
    private ComboBox<String> sortingMethods;
    
    @FXML
    private HBox botHbox;


    /**
     * Method allowing to make an observable list of stores
     * @return the observable list of stores
     */
    private ObservableList<Store> makeObservableList(){
        ObservableList<Store> stores = FXCollections.observableArrayList();
        for (Store store : firm.getStores()){
            stores.addAll(store);
        }
        return stores;
    }

    /**
     * Allow to populate the combobox of sorting methods
     */
    public void initializeCombobox(){
        ObservableList<String> sortingMethodsList=FXCollections.observableArrayList();
        for (SortingEnum sorting : SortingEnum.values()){
            sortingMethodsList.add(sorting.getSortingName());
        }
        sortingMethods.setItems(sortingMethodsList);
    }

    

    @Override
    public void initContent(Object obj){
    	super.initContent(obj);
    	
    	Firm firm = null;
    	if(obj instanceof Firm)
    		firm = (Firm) obj;
    	else
    		return;

    	this.firm = firm;
    	initializeListView();
        initializeCombobox();
    	updateLogo(firm.getLogo());
    	updateFirmImageName(firm.getBanner());
    	addResizeListener();
    }

    /**
     * Method permitting to change the content of the listView
     */
    private void initializeListView(){
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
                                ImageView storeImage = new ImageView();
                                storeImage.setImage(ImageBuilder.getImage(store.getImage()));
                                storeImage.setPreserveRatio(true);
                                storeImage.setFitHeight(120);
                                storeImage.setFitWidth(120);
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
     * Method that can change the order of the items in the list view
     * @param newOrder the new order of the items
     */
    private void changeListViewOrder(List<Store> newOrder){
        ObservableList<Store> storesInListView = FXCollections.observableArrayList(newOrder);
        stores.setItems(storesInListView);
    }

    /**
     * Allow to change the logo of the firm
     * @param url the url to the image logo
     */
    private void updateLogo(String url){
    	logo.setImage(ImageBuilder.getImage(url));
    }

    /**
     * Allow to change the image/banner of the firm
     * @param url the url to the image
     */
    private void updateFirmImageName(String url){
        firmImageName.setImage(ImageBuilder.getImage(url));
    }


    /**
     * Method that will call the corresponding sorting method depending on what has been chosen in the comboBox
     */
    @FXML
    public void sortWithTheSelectedSortingMethod(){
        String value = sortingMethods.getValue();

        if (convertStringToSortingEnum(value).equals(SortingEnum.CITY)){
            SortListViewItemByCity sort = new SortListViewItemByCity(firm.getStores());
            changeListViewOrder(sort.sortByCity());
        }
        else if (convertStringToSortingEnum(value).equals(SortingEnum.DEPARTMENT)){
            //TODO
        }
        else if (convertStringToSortingEnum(value).equals(SortingEnum.REGION)){
            //TODO
        }
    }

    /**
     * Method that converts a string in a SortingEnum object if the string is corresponding to one of the objects
     * @param selected the string to convert
     * @return the SortingEnum object corresponding to the string
     */
    public SortingEnum convertStringToSortingEnum(String selected){
        for (SortingEnum sortingEnum : SortingEnum.values()){
            if (selected.equals(sortingEnum.getSortingName())) return sortingEnum;
        }
        return SortingEnum.DEFAULT;
    }


    @Override
    protected void addResizeListener(){
    	initialeWidth = (int) scene.getWidth();
    	
    	scene.widthProperty().addListener(new ChangeListener<Number>() {
    	    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
    	    	double newSpace = 575 + newSceneWidth.doubleValue()-initialeWidth;
    	    	if(newSpace<10.0)
    	    		newSpace=10.;
    	        botHbox.setSpacing(newSpace);
    	    }
    	});
    }

}
