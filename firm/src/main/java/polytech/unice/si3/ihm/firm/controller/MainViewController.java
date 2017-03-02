package polytech.unice.si3.ihm.firm.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.util.ContentParser;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;

import polytech.unice.si3.ihm.firm.exceptions.ContentException;

public class MainViewController extends BasicController {
	private String linkToVisit;

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
    void linkPressed(MouseEvent event) throws IOException {
    	URI uri = URI.create(linkToVisit);
    	Desktop.getDesktop().browse(uri);
    }

    @FXML
    void openAllShops(MouseEvent event) throws IOException, ContentException {
    	Stage stage = new Stage();
        String fxmlFile = "/fxml/all_stores_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 810, 585);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Toutes les enseignes");
        stage.setScene(scene);

        Firm firm = ContentParser.getFirm();

        AllStoreController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initContent(firm);
        stage.show();
    }

    @FXML
    void search(MouseEvent event) {

    }
    
    @Override
    public void initContent(Object obj){
    	Firm firm = null;
    	if(obj instanceof Firm)
    		firm = (Firm) obj;
    	else
    		return;
    	
    	updateLogo(firm.getLogo());
    	updateFirmImageName(firm.getBanner());
    	updateDescription(firm.getDescription());
    	linkToVisit = firm.getLinkForMoreInfo();
    }
    
    private void updateLogo(String url){
    	logo.setImage(ImageBuilder.getImage(url));
    }
    
    private void updateFirmImageName(String url){
        firmImageName.setImage(ImageBuilder.getImage(url));
    }
    
    private void updateDescription(String description){
    	this.description.setText(description);
    }
    
}
