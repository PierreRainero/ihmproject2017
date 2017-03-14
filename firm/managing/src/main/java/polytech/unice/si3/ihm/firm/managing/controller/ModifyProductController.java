package polytech.unice.si3.ihm.firm.managing.controller;

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
import polytech.unice.si3.ihm.firm.common.model.commercial.Product;
import polytech.unice.si3.ihm.firm.common.util.ImageBuilder;
import polytech.unice.si3.ihm.firm.common.util.Log;

import java.io.IOException;
import java.util.List;

public class ModifyProductController {

    @FXML
    private ListView<Product> modifyProductListView;

    @FXML
    private Button openModifyProductView;

    @FXML
    private Button deleteProductButton;

    private List<Product> products;


    @FXML
    public void initialize() throws ParseException, ContentException, IOException {
        products = parseProducts();
        initializeListView();
    }

    @FXML
    void deleteProduct(MouseEvent event) {

    }

    @FXML
    void modifyProduct(MouseEvent event) throws IOException {
        if (!modifyProductListView.getSelectionModel().isEmpty()) {
            Stage stage = new Stage();
            String fxmlFile = "/fxml/modify_product_window.fxml";
            Log.debug(this.getClass(), "Loading FXML for oneProduct view from: {}", fxmlFile);
            FXMLLoader fxloader = new FXMLLoader();
            Parent rootNode = fxloader.load(getClass().getResourceAsStream(fxmlFile));
            stage.setMinWidth(600.0);
            stage.setMaxWidth(600.0);
            stage.setMinHeight(400.0);
            stage.setMaxHeight(400.0);

            Scene scene = new Scene(rootNode, 420, 450);
            scene.getStylesheets().add("/styles/main.css");
            stage.setTitle(modifyProductListView.getSelectionModel().getSelectedItem().getName());
            stage.setScene(scene);
            stage.show();

        }
    }

    private List<Product> parseProducts() throws IOException, ParseException, ContentException {
        ContentParser parser = new ContentParser();
        return parser.getFirm().getProducts();
    }

    private void initializeListView() throws ParseException, ContentException, IOException {
        changeListView();
        modifyProductListView.setPlaceholder(new Label("List is empty"));
        modifyProductListView.setOrientation(Orientation.VERTICAL);
        modifyProductListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<Product>(){
                    @Override
                    protected void updateItem(Product product, boolean empty){
                        super.updateItem(product,empty);
                        if (!empty){
                            VBox vbox = new VBox(new Label(product.getName()),
                                    new Label(product.getReference()),
                                    new Label(String.valueOf(product.getPrice())),
                                    new Label(product.getDescription()));

                            HBox hBox;
                            if (product.getImage()==null){
                                hBox = new HBox(new Label("No image"), vbox);
                            }
                            else{
                                ImageView storeImage = new ImageView();
                                storeImage.setImage(ImageBuilder.getImage(product.getImage()));
                                storeImage.setPreserveRatio(true);
                                storeImage.setFitHeight(180);
                                storeImage.setFitWidth(140);
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
    }

    private void changeListView() throws ParseException, ContentException, IOException {
        products = parseProducts();
        ObservableList<Product> productsObservable = FXCollections.observableArrayList(products);
        modifyProductListView.setItems(productsObservable);
    }

}
