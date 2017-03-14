package polytech.unice.si3.ihm.firm.managing.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.parser.ParseException;
import polytech.unice.si3.ihm.firm.common.exceptions.ContentException;
import polytech.unice.si3.ihm.firm.common.json.ContentParser;
import polytech.unice.si3.ihm.firm.common.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.common.model.sorting.shop.SortListViewItemByName;
import polytech.unice.si3.ihm.firm.common.util.ImageBuilder;
import polytech.unice.si3.ihm.firm.common.util.Log;
import polytech.unice.si3.ihm.firm.managing.json.JsonStoreEditor;

import java.io.IOException;
import java.util.List;

public class ModifyStoreController {

    @FXML
    private ListView<Store> modifyStoreListview;

    @FXML
    private Button modifyStoreButton;

    @FXML
    private Button deleteStoreButton;



    @FXML
    public void initialize() throws ParseException, ContentException, IOException {
        populateListView();
    }

    @FXML
    private void deleteStore(MouseEvent event) throws ParseException, ContentException, IOException {
        List<Store> stores = parseStores();
        Store selectedStore = modifyStoreListview.getSelectionModel().getSelectedItem();
        for (Store store : stores){
            if (store.getName().equals(selectedStore.getName())){
                JsonStoreEditor storeEditor = new JsonStoreEditor(store);
                storeEditor.remove();
            }
        }
        stores = parseStores();
        changeListViewOrder();
    }

    @FXML
    private void openModifyView(MouseEvent event) throws IOException {
        if (!modifyStoreListview.getSelectionModel().isEmpty()){
            Stage stage = new Stage();
            String fxmlFile = "/fxml/modify_store_window.fxml";
            Log.debug(this.getClass(), "Loading FXML for one store view from: {}", fxmlFile);
            FXMLLoader fxloader = new FXMLLoader();
            Parent rootNode = fxloader.load(getClass().getResourceAsStream(fxmlFile));
            stage.setMinWidth(600.0);
            stage.setMaxWidth(600.0);
            stage.setMinHeight(400.0);
            stage.setMaxHeight(400.0);

            Scene scene = new Scene(rootNode, 420, 450);
            scene.getStylesheets().add("/styles/main.css");
            stage.setTitle(modifyStoreListview.getSelectionModel().getSelectedItem().getName());
            stage.setScene(scene);

            Store store = modifyStoreListview.getSelectionModel().getSelectedItem();
            ModifyStoreWindowController controller = fxloader.getController();
            controller.setCurrentStage(stage);
            controller.setCaller(this);
            controller.initContent(store);
            stage.show();
        }


    }

    public void populateListView() throws ParseException, ContentException, IOException {
        List<Store> stores = parseStores();
        changeListViewOrder();
        modifyStoreListview.setPlaceholder(new Label("List is empty"));
        modifyStoreListview.setOrientation(Orientation.VERTICAL);
        modifyStoreListview.setCellFactory(new Callback<ListView<Store>, ListCell<Store>>() {
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
                                    new Label(store.getCityNumber()),
                                    new Label(store.getDepartment()),
                                    new Label(store.getRegion()));

                            HBox hBox;
                            if (store.getImage()==null){
                                hBox = new HBox(new Label("No image"), vbox);
                            }
                            else{
                                ImageView storeImage = new ImageView();
                                storeImage.setImage(ImageBuilder.getImage(store.getImage()));
                                storeImage.setPreserveRatio(true);
                                storeImage.setFitHeight(180);
                                storeImage.setFitWidth(180);
                                hBox = new HBox(storeImage, vbox);
                            }
                            hBox.setSpacing(30.0);
                            setGraphic(hBox);
                        }
                        else{
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        modifyStoreListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Store>() {
            @Override
            public void changed(ObservableValue<? extends Store> observable, Store oldValue, Store newValue) {
                if (newValue == null) try {
                    changeListViewOrder();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (ContentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        SortListViewItemByName sortByName = new SortListViewItemByName(stores);
        stores = sortByName.sort();
        changeListViewOrder();
    }



    /**
     * Method that can change the order of the items in the list view
     */
    protected void changeListViewOrder() throws ParseException, ContentException, IOException {
        List<Store> newOrder = parseStores();
        ObservableList<Store> storesInListView = FXCollections.observableArrayList(newOrder);
        modifyStoreListview.setItems(storesInListView);

        Log.info(this.getClass(), "Shops order changed");
    }

    private List<Store> parseStores() throws IOException, ParseException, ContentException {
        ContentParser parser = new ContentParser();
        Firm firm = parser.getFirm();
        return firm.getStores();
    }





}
