package polytech.unice.si3.ihm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import polytech.unice.si3.ihm.model.Mall;
import java.io.IOException;

public class MainViewController extends MenuController {
    private Mall mall;
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
        reset();
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
    private GridPane grid;

    @FXML
    void buildBasement(MouseEvent event) {
        reset();
        labelFloor.setText("Sous-sol");
    }

    @FXML
    void buildFirstFloor(MouseEvent event) {
        reset();
        labelFloor.setText("Premier étage");

        Drawer drawer = new Drawer(grid);
        drawer.displayLevel(mall.getLevel(1));
    }

    @FXML
    void buildGroundFloor(MouseEvent event) {
        reset();
        labelFloor.setText("Rez-de-chaussée");
    }

    @FXML
    void buildSecondFloor(MouseEvent event) {
        reset();
        labelFloor.setText("Deuxième étage");

    }

    @FXML
    void buildThirdFloor(MouseEvent event) {
        reset();
        labelFloor.setText("Troisième étage");
    }

    @FXML
    void clickGrid(MouseEvent event) {

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

    @Override
    @FXML
    void reset(ActionEvent event) {
        reset();
    }

    private void reset() {
        grid.getChildren().clear();
    }
}
