package polytech.unice.si3.ihm.view;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import polytech.unice.si3.ihm.model.Category;
import polytech.unice.si3.ihm.model.Mall;
import java.io.IOException;

public class MainViewController extends MenuController {
    private Mall mall;
    private int currentLevel;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainViewController() {
        mall = new Mall("mall");
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        buildGroundFloor(null);
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

    @FXML
    private Button culture;

    @FXML
    private Button decoration;

    @FXML
    private Button toys;

    @FXML
    private Button fashion;

    @FXML
    private Button services;

    @FXML
    private Button care;

    @FXML
    private Button sport;

    @FXML
    private Button technologies;

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
    private Label labelFloor;

    @FXML
    private Pane canvas;

    @FXML
    void buildBasement(MouseEvent event) {
        buildFloor("Sous-sol", -1);
    }

    @FXML
    void buildGroundFloor(Event event) {
        buildFloor("Rez-de-chaussée", 0);
    }

    @FXML
    void buildFirstFloor(MouseEvent event) {
        buildFloor("Premier étage", 1);
    }

    @FXML
    void buildSecondFloor(MouseEvent event) {
        buildFloor("Deuxième étage", 2);
    }

    @FXML
    void buildThirdFloor(MouseEvent event) {
        buildFloor("Troisième étage", 3);
    }

    private void buildFloor(String floor, int i) {
        canvas.getChildren().clear();
        labelFloor.setText(floor);
        currentLevel = i;

        Drawer drawer = new Drawer(canvas);
        drawer.displayLevel(mall.getLevel(i));
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
    void selectCare(MouseEvent event) {

    }

    @FXML
    void selectCulture(MouseEvent event) {

    }

    @FXML
    void selectDecoration(MouseEvent event) {

    }

    @FXML
    void selectFashion(MouseEvent event) {

    }

    @FXML
    void selectServices(MouseEvent event) {

    }

    @FXML
    void selectSport(MouseEvent event) {

    }

    @FXML
    void selectTechnologies(MouseEvent event) {
        canvas.getChildren().clear();
        Drawer drawer = new Drawer(canvas);
        drawer.displayCategory(mall.getLevel(currentLevel), Category.TECHNOLOGY);
    }

    @FXML
    void selectToys(MouseEvent event) {

    }
}
