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
import polytech.unice.si3.ihm.firm.model.sorting.shop.SortListViewItemByCity;
import polytech.unice.si3.ihm.firm.model.sorting.shop.SortingEnumShop;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;

import java.util.List;

/**
 * 
 * Controler class for the view grouping all stores of a firm
 *
 */
public class AllStoreController extends BasicController {
    private Firm firm;
    private SortListViewItemByCity sorByCity;
    
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
        ObservableList<Store> storesList = FXCollections.observableArrayList();
        for (Store store : firm.getStores()){
        	storesList.addAll(store);
        }
        return storesList;
    }

    /**
     * Allow to populate the combobox of sorting methods
     */
    public void initializeCombobox(){
        ObservableList<String> sortingMethodsList=FXCollections.observableArrayList();
        for (SortingEnumShop sorting : SortingEnumShop.values()){
            sortingMethodsList.add(sorting.getSortingName());
        }
        sortingMethods.setItems(sortingMethodsList);
        sortingMethods.setPromptText(SortingEnumShop.CITY.getSortingName());
    }

    

    @Override
    /**
     * {@inheritDoc}
     */
    public void initContent(Object obj){
    	super.initContent(obj);
    	
    	if(obj instanceof Firm)
    		firm = (Firm) obj;
    	else
    		return;

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
        sorByCity = new SortListViewItemByCity(firm.getStores());
        changeListViewOrder(sorByCity.sort());
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

        if (convertStringToSortingEnum(value).equals(SortingEnumShop.CITY)){
            changeListViewOrder(sorByCity.sort());
        }
        else if (convertStringToSortingEnum(value).equals(SortingEnumShop.DEPARTMENT)){
            
        }
        else if (convertStringToSortingEnum(value).equals(SortingEnumShop.REGION)){
            
        }
    }

    /**
     * Method that converts a string in a SortingEnum object if the string is corresponding to one of the objects
     * @param selected the string to convert
     * @return the SortingEnum object corresponding to the string
     */
    public SortingEnumShop convertStringToSortingEnum(String selected){
        for (SortingEnumShop sortingEnum : SortingEnumShop.values()){
            if (selected.equals(sortingEnum.getSortingName())) 
            	return sortingEnum;
        }
        return SortingEnumShop.CITY;
    }


    @Override
    /**
     * {@inheritDoc}
     */
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
