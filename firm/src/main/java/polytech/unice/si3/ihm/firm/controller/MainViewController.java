package polytech.unice.si3.ihm.firm.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import polytech.unice.si3.ihm.firm.exceptions.ContentException;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.model.commercial.Product;
import polytech.unice.si3.ihm.firm.model.sorting.product.SortingEnumProduct;
import polytech.unice.si3.ihm.firm.model.sorting.product.SortingListByFlagship;
import polytech.unice.si3.ihm.firm.model.sorting.product.SortingListByPromo;
import polytech.unice.si3.ihm.firm.util.ContentParser;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;
import polytech.unice.si3.ihm.firm.util.Log;
import polytech.unice.si3.ihm.firm.view.Carousel;

/**
 * 
 * Controler class for the home view
 *
 */
public class MainViewController extends BasicController {
	private String linkToVisit;
	
	private List<Product> allProducts;
	private List<Product> currentProducts;
	
	private ImageView[] carouselImages;
	private int[] index;
	private int[] shiftIndex;
	private int nbTick;
	
	private Thread carouselThread;
	private boolean resetCarousel;

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
    private ComboBox<String> carouselType;
    
    @FXML
    private ImageView loader;

    @FXML
    /**
     * Actions when the center product is clicked
     * @param event event to catch
     */
    private void choseCenterProduct(MouseEvent event) throws IOException {
    	switch(nbTick){
    		case -1: case 2:
    			choseProduct(index[1]);
    			break;
    		case 0:
    			choseProduct(index[0]);
    			break;
    		case 1:
    			choseProduct(index[2]);
    			break;
    		default:
    			return;
    	}
    }

    @FXML
    /**
     * Actions when the left product is clicked
     * @param event event to catch
     */
    private void choseLeftProduct(MouseEvent event) throws IOException {
    	switch(nbTick){
	    	case -1: case 2:
				choseProduct(index[0]);
				break;
			case 0:
				choseProduct(index[2]);
				break;
			case 1:
				choseProduct(index[1]);
				break;
			default:
				return;
    	}
    }

    @FXML
    /**
     * Actions when the right product is clicked
     * @param event event to catch
     */
    private void choseRightProduct(MouseEvent event) throws IOException {
    	switch(nbTick){
	    	case -1: case 2:
				choseProduct(index[2]);
				break;
			case 0:
				choseProduct(index[1]);
				break;
			case 1:
				choseProduct(index[0]);
				break;
			default:
				return;
		}
    }
    
    private void choseProduct(int index) throws IOException{
    	Stage stage = new Stage();
        String fxmlFile = "/fxml/one_product_view.fxml";
        Log.debug(this.getClass(), "Loading FXML for oneProduct view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(420.0);
        stage.setMinWidth(420.0);

        Scene scene = new Scene(rootNode, 420, 450);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle(currentProducts.get(index).getName());
        stage.setScene(scene);
        
        OneProductController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initContent(currentProducts.get(index));
        stage.show();
    }
    
    @FXML
    /**
     * Open the default browser with the firm url
     * @param event event to catch
     */
    private void linkPressed(MouseEvent event) throws IOException {
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
    private void openAllShops(MouseEvent event) throws IOException, ContentException, ParseException {
    	Stage stage = new Stage();
        String fxmlFile = "/fxml/all_stores_view.fxml";
        Log.debug(this.getClass(), "Loading FXML for allShops view from: {}", fxmlFile);
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
    private void search(MouseEvent event) {

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
    	
    	index = new int[3];
    	
    	currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            	Carousel.setCarouselState(false);
            	Log.info(this.getClass(), "Window closed");
            }
        });
    	
    	searchButton.setGraphic(new ImageView(ImageBuilder.getImage("src/main/resources/images/ic_search_black_24dp_2x.png", 25, 25)));
    	addResizeListener();
    	
    	loader.setImage(ImageBuilder.getImage("src/main/resources/images/loader.gif", 25, 25));
    	loader.setVisible(false);
    	
    	fillAds(firm);
    	
    	allProducts = firm.getProducts();
    	initializeCombobox();
    	
    	carouselImages = new ImageView[3];
    	shiftIndex = new int[3];
    	resetCarousel = false;
    	startCarousel();
    	
    	updateLogo(firm.getLogo());
    	updateFirmImageName(firm.getBanner());
    	updateDescription(firm.getDescription());
    	linkToVisit = firm.getLinkForMoreInfo();
    	
        Log.info(this.getClass(), "Content charged");
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
     * Allow to populate the combobox of sorting methods
     */
    private void initializeCombobox(){
        ObservableList<String> sortingMethodsList = FXCollections.observableArrayList();
        for (SortingEnumProduct sorting : SortingEnumProduct.values()){
            sortingMethodsList.add(sorting.getSortingName());
        }
        carouselType.setItems(sortingMethodsList);
        carouselType.setPromptText(SortingEnumProduct.ALL.getSortingName());
        currentProducts = allProducts;
    }
    
    @FXML
    private void sortWithTheSelectedSortingMethod(ActionEvent event) {
    	String value = carouselType.getValue();
    	
        if (SortingEnumProduct.convertStringToSortingEnum(value).equals(SortingEnumProduct.ALL))
        	currentProducts = allProducts;
        else if (SortingEnumProduct.convertStringToSortingEnum(value).equals(SortingEnumProduct.FLAGSHIP))
        	currentProducts = (new SortingListByFlagship(allProducts)).sort();
        else if (SortingEnumProduct.convertStringToSortingEnum(value).equals(SortingEnumProduct.PROMOTED))
        	currentProducts = (new SortingListByPromo(allProducts)).sort();
        else
        	return;
        
        loader.setVisible(true);
        resetCarousel = true;
    }
    
    /**
     * Initialize the carousel in the center
     * @param firm firm containing all products
     */
	private void startCarousel(){
    	if(resetCarousel){
    		Carousel.setCarouselState(false);
    		resetPosition();
	    	resetSizes();
	        Log.info(this.getClass(), "Carousel datas changed");
    	}
    	
    	if(currentProducts.isEmpty())
    		return;

    	index[0] = 0;
		index[1] = 0;
		index[2] = 0;
    	
    	if(currentProducts.size()>1)
    		index[1] = 1;
    	
    	if(currentProducts.size()>2)
    		index[2] = 2;

		carrouseImg1.setImage(ImageBuilder.getImage(currentProducts.get(index[0]).getImage()));
    	carrouseImg2.setImage(ImageBuilder.getImage(currentProducts.get(index[1]).getImage()));
		carrouseImg3.setImage(ImageBuilder.getImage(currentProducts.get(index[2]).getImage()));
		
    	carrouseImg2.setFitHeight(430.);
    	carrouseImg2.setFitWidth(310.);
    	
    	carrouseImg1.setFitHeight(350.);
    	carrouseImg1.setFitWidth(252.);
    	carrouseImg3.setFitHeight(350.);
    	carrouseImg3.setFitWidth(252.);
		
    	carouselImages[0] = carrouseImg1;
    	carouselImages[1] = carrouseImg2;
    	carouselImages[2] = carrouseImg3;
    	
    	shiftIndex[0] = 0;
    	shiftIndex[1] = 0;
    	shiftIndex[2] = 0;
    	
    	nbTick = -1;
    	
    	resetCarousel = false;
    	carouselThread = new Thread(new Carousel(this, carouselThread));
    	carouselThread.start();
    	Log.info(this.getClass(), "Carousel started");
    }
    
    /**
     * Corresponds to a clock tick. Allows to make carousel move.
     */
    public void tick(int nbTick){
    	this.nbTick = nbTick;
    	incrementProducts();
    	updateShifts();
    	move();
    }
    
    /**
     * Switch carousel images positions
     * @param nbTick state (0,1,2), 0 = start position
     */
    private void move(){
    	int gap  = 27;
    	
    	Bounds boundsInScreenImg1 = carouselImages[0+shiftIndex[0]].localToScreen(carouselImages[0+shiftIndex[0]].getBoundsInLocal());
    	double boundsInScreenImg1X = boundsInScreenImg1.getMinX();
    	
    	Bounds boundsInScreenImg2 = carouselImages[1+shiftIndex[1]].localToScreen(carouselImages[1+shiftIndex[1]].getBoundsInLocal());
    	double boundsInScreenImg2X = boundsInScreenImg2.getMinX();
    	
    	double xDistanceBetween1And2 = boundsInScreenImg2X-boundsInScreenImg1X;
    	
    	TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(1750), carouselImages[1+shiftIndex[1]]);
    	translateTransition1.setByX(-xDistanceBetween1And2-gap);
    	ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(1750), carouselImages[1+shiftIndex[1]]);
    	scaleTransition1.setByX(-0.2);
    	scaleTransition1.setByY(-0.2);
    	
    	
    	Bounds boundsInScreenImg3 = carouselImages[2+shiftIndex[2]].localToScreen(carouselImages[2+shiftIndex[2]].getBoundsInLocal());
    	double boundsInScreenImg3X = boundsInScreenImg3.getMinX();
    	
    	double xDistanceBetween2And3 = boundsInScreenImg3X-boundsInScreenImg2X;
    	
    	TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1750), carouselImages[2+shiftIndex[2]]);
    	translateTransition2.setByX(-xDistanceBetween2And3+gap);
    	ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(1750), carouselImages[2+shiftIndex[2]]);
    	scaleTransition2.setByX(0.2);
    	scaleTransition2.setByY(0.2);
    	
    	double xDistanceBetween1And3 = boundsInScreenImg3X-boundsInScreenImg1X;
    	
    	TranslateTransition translateTransition3 = new TranslateTransition(Duration.millis(0.1), carouselImages[0+shiftIndex[0]]);
    	carouselImages[0+shiftIndex[0]].setImage(ImageBuilder.getImage(currentProducts.get(index[2]).getImage()));
    	translateTransition3.setByX(xDistanceBetween1And3);
    	
    	translateTransition1.play();
    	scaleTransition1.play();
    	translateTransition2.play();
    	scaleTransition2.play();
    	translateTransition3.play();
    	
		try {
			TimeUnit.MILLISECONDS.sleep(1760);
		} catch (Exception e) {
			Log.error(this.getClass(), "Animation carousel wait failed", e);
		}
		
		
		if(nbTick==2){
	    	resetPosition();
	    	resetSizes();
		}
		
		loader.setVisible(false);
		
    	if(resetCarousel)
    		startCarousel();
    }
    
    /**
     * Increment display products
     */
    private void incrementProducts(){
    	index[0] = index[1];
    	index[1] = index[2];
    	index[2] = index[2]+1>=currentProducts.size() ? 0 : index[2]+1;
    }
    
    /**
     * Update shift to switch images
     * @param nbTick
     */
    private void updateShifts(){
    	if(nbTick==0){
        	shiftIndex[0] = 0;
        	shiftIndex[1] = 0;
        	shiftIndex[2] = 0;
        	return;
    	}
    	
    	if(nbTick==1){
    		shiftIndex[0] = 1;
    		shiftIndex[1] = 1;
    		shiftIndex[2] = -2;
    		return;
    	}
    	
    	if(nbTick==2){
    		shiftIndex[0] = 2;
    		shiftIndex[1] = -1;
    		shiftIndex[2] = -1;
    		return;
    	}
    }
    
    /**
     * Reset initial sizes at end of state 2 (note necessary if scales are perfects)
     */
    private void resetSizes(){
    	carrouseImg1.setScaleX(1.);
    	carrouseImg1.setScaleY(1.);
    	carrouseImg2.setScaleX(1.);
    	carrouseImg2.setScaleY(1.);
    	carrouseImg3.setScaleX(1.);
    	carrouseImg3.setScaleY(1.);
    }
    
    /**
     * Reset initial positions at end of state 2 (note necessary if translates are perfects)
     */
    private void resetPosition(){
    	carrouseImg1.setTranslateX(0.);
    	carrouseImg2.setTranslateX(0.);
    	carrouseImg3.setTranslateX(0.);
    }
    
}
