package polytech.unice.si3.ihm.firm.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import polytech.unice.si3.ihm.firm.model.Firm;
import polytech.unice.si3.ihm.firm.model.Store;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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


        AllStoreController controller = loader.getController();
        controller.setCurrentStage(stage);
        stage.show();
    }

    @FXML
    void search(MouseEvent event) {

    }
    
    @Override
    public void initContent(){

    }

}
