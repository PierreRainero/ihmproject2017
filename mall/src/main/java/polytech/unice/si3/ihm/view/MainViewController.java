package polytech.unice.si3.ihm.view;



/**
 * Created by Ken on 28/02/2017.
 */
import polytech.unice.si3.ihm.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import polytech.unice.si3.ihm.model.Coordinate;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Store;

public class MainViewController {

    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainViewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void displayGoodDeals(){
        mainApp.changeScene("/fxml/good_deals.fxml");
    }

    @FXML
    private Button offers;

    @FXML
    private Button wifi;

    @FXML
    private Button toilet;

    @FXML
    private Button elevators;

    @FXML
    private Button stairs;

    @FXML
    private Button restaurants;

    @FXML
    private Button whereAmI;

    @FXML
    private Button basement;

    @FXML
    private Button groundFloor;

    @FXML
    private Button firstFloor;

    @FXML
    private Button secondFloor;

    @FXML
    private Button thirdFloor;

    @FXML
    void buildBasement(MouseEvent event) {

    }

    @FXML
    void buildFirstFloor(MouseEvent event) {
        Level fFloor = new Level(1);
        Store appleStore = new Store("Apple",  Color.web("0x323232"), "apple", new Coordinate(11, 4));
        fFloor.addStore(appleStore);
        fFloor.displayStores();
    }

    @FXML
    void buildGroundFloor(MouseEvent event) {

    }

    @FXML
    void buildSecondFloor(MouseEvent event) {

    }

    @FXML
    void buildThirdFloor(MouseEvent event) {

    }

    @FXML
    void displayElevators(MouseEvent event) {

    }

    @FXML
    void displayPositionUser(MouseEvent event) {

    }

    @FXML
    void displayRestaurants(MouseEvent event) {

    }

    @FXML
    void displayStairs(MouseEvent event) {

    }

    @FXML
    void displayToilet(MouseEvent event) {

    }

    @FXML
    void displayWifi(MouseEvent event) {

    }
}
