package polytech.unice.si3.ihm.firm.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.util.ContentParser;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;

/**
 * 
 * Controler class for the home view
 *
 */
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
    private ListView<String> ads;

    @FXML
    private Hyperlink firmLink;
    
    @FXML
    private HBox hboxSearch;

    @FXML
    /**
     * Actions when the center product is clicked
     * @param event event to catch
     */
    void choseCenterProduct(MouseEvent event) {

    }

    @FXML
    /**
     * Actions when the left product is clicked
     * @param event event to catch
     */
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
    /**
     * Actions when the right product is clicked
     * @param event event to catch
     */
    void choseRightProduct(MouseEvent event) {

    }
    
    @FXML
    /**
     * Open the default browser with the firm url
     * @param event event to catch
     */
    void linkPressed(MouseEvent event) throws IOException {
    	URI uri = URI.create(linkToVisit);
    	Desktop.getDesktop().browse(uri);
    }

    @FXML
    /**
     * Open a new window with all shops of the firm
     * @param event event to catch
     * @throws IOException
     * @throws ContentException
     */
    void openAllShops(MouseEvent event) throws Exception {
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
        controller.setScene(scene);
        controller.setCurrentStage(stage);
        controller.initContent(firm);
        stage.show();
    }

    @FXML
    /**
     * Make a search
     * @param event event to catch
     */
    void search(MouseEvent event) {

    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    public void initContent(Object obj){
    	super.initContent(obj);
    	
    	Firm firm;
    	if(obj instanceof Firm)
    		firm = (Firm) obj;
    	else
    		return;
    	
    	searchButton.setGraphic(new ImageView(ImageBuilder.getImage("src/main/resources/images/ic_search_black_24dp_2x.png", 25, 25)));
    	addResizeListener();
    	
    	fillAds(firm);
    	startCarousel(firm);
    	
    	updateLogo(firm.getLogo());
    	updateFirmImageName(firm.getBanner());
    	updateDescription(firm.getDescription());
    	linkToVisit = firm.getLinkForMoreInfo();
    }
    
    /**
     * Update the firm logo
     * @param url url of the image
     */
    private void updateLogo(String url){
    	logo.setImage(ImageBuilder.getImage(url));
    }
    
    /**
     * Update the firm banner
     * @param url url of the image
     */
    private void updateFirmImageName(String url){
        firmImageName.setImage(ImageBuilder.getImage(url));
    }
    
    /**
     * Update the firm description
     * @param description description of the firm
     */
    private void updateDescription(String description){
    	this.description.setText(description);
    }
    
    /**
     * Fill the ads listView with the model
     * @param firm firm containing all ads
     */
    private void fillAds(Firm firm){
    	ListProperty<String> listProperty = new SimpleListProperty<>();
    	ads.itemsProperty().bind(listProperty);
    	listProperty.set(FXCollections.observableArrayList(firm.getAds()));
    }
    
    /**
     * Initialize the carousel in the center
     * @param firm firm containing all products
     */
    private void startCarousel(Firm firm){
    	if(!firm.getProducts().isEmpty())
    		carrouseImg1.setImage(ImageBuilder.getImage(firm.getProducts().get(0).getImage()));
    	if(firm.getProducts().size()>1)
    		carrouseImg2.setImage(ImageBuilder.getImage(firm.getProducts().get(1).getImage()));
    	if(firm.getProducts().size()>2)
    		carrouseImg3.setImage(ImageBuilder.getImage(firm.getProducts().get(2).getImage()));
    	
    	carrouseImg2.setFitHeight(430.);
    	carrouseImg2.setFitWidth(310.);
    	
    	carrouseImg1.setFitHeight(350.);
    	carrouseImg1.setFitWidth(252.);
    	carrouseImg3.setFitHeight(350.);
    	carrouseImg3.setFitWidth(252.);
    }
    
    
    @Override
    /**
     * {@inheritDoc}
     */
    protected void addResizeListener(){
    	scene.widthProperty().addListener(new ChangeListener<Number>() {
    	    public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
    	    	searchType.setPrefWidth(hboxSearch.getWidth());
    	    }
    	});
    }
    
}
