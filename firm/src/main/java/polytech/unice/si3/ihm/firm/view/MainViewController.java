package polytech.unice.si3.ihm.firm.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.model.Firm;
import polytech.unice.si3.ihm.firm.model.Store;

public class MainViewController extends BasicController {

    @FXML
    private ImageView logo;

    @FXML
    private ImageView firmImageName;

    @FXML
    private Label description;

    @FXML
    private ComboBox<?> searchType;

    @FXML
    private TextField searchValue;

    @FXML
    private Button searchButton;

    @FXML
    private Label mallName;

    @FXML
    private Label shopAdress;

    @FXML
    private Hyperlink shopLink;

    @FXML
    private Button shopsButton;

    @FXML
    private ImageView carrouseImg1;

    @FXML
    private ImageView carrouseImg2;

    @FXML
    private ImageView carrouseImg3;

    @FXML
    private Label promo1;

    @FXML
    private Label promo2;

    @FXML
    private Label promo3;

    @FXML
    private Hyperlink firmLink;

    @FXML
    void choseCenterProduct(MouseEvent event) {

    }

    @FXML
    void choseLeftProduct(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
        String fxmlFile = "/fxml/one_product_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 410, 605);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Produit phare");
        stage.setScene(scene);
        
        
        OneProductController controller = loader.getController();
        controller.setCurrentStage(stage);
        
        stage.show();
    }

    @FXML
    void choseRightProduct(MouseEvent event) {

    }

    @FXML
    void openAllShops(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
        String fxmlFile = "/fxml/all_stores_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 810, 585);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Toutes les enseignes");
        stage.setScene(scene);

        Firm firm = new Firm("WEEWEE","Best Firm ever");
        Store store1 = new Store("BouBOU", "71th street, Bamako", "HAHA", "Simple store for cat goodies");
        Store store2 = new Store("JALLY", "96th street of Hillary.C, York New", "HEHE", "Simple store for dog goodies");
        firm.addStore(store1);
        firm.addStore(store2);

        AllStoreController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.setFirm(firm);
        controller.changeListView();
        stage.show();
    }

    @FXML
    void search(MouseEvent event) {

    }

}
