package polytech.unice.si3.ihm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import polytech.unice.si3.ihm.model.Coordinate;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Store;

import java.io.IOException;

public class MainViewController {

    private MainApp mainApp;
    private Stage stage;

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
        FXMLLoader loader = new FXMLLoader();
        Parent node = null;
        try {
            node = loader.load(getClass().getResourceAsStream("/fxml/good_deals.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(node, 1280, 720);
        mainApp.getStage().setScene(scene);

        GoodDealsViewController controller = loader.getController();
        controller.setMainApp(mainApp);

        mainApp.getStage().show();
    }

    public void loadStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button offers;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem reset;

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
    private GridPane grid;

    @FXML
    void buildBasement(MouseEvent event) {

    }

    @FXML
    void buildFirstFloor(MouseEvent event) {
        Level fFloor = new Level(1);
        Store appleStore = new Store("Apple",  Color.web("0x323232"), "apple", new Coordinate(11, 4));
        appleStore.setSize(203, 136);
        fFloor.addStore(appleStore);
        Drawer drawer = new Drawer(grid);
        drawer.displayLevel(fFloor);
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
    void clickGrid(MouseEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
        stage.close();
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

    @FXML
    void reset(ActionEvent event) {
        grid.getChildren().clear();
    }
}
