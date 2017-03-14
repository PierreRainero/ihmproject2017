package polytech.unice.si3.ihm.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import polytech.unice.si3.ihm.model.Category;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Mall;
import polytech.unice.si3.ihm.model.Place;
import polytech.unice.si3.ihm.view.Drawer;

import java.io.IOException;

/**
 * Interprets the user's interactions and modifies the scene.
 *
 * @author Guillaume Casagrande
 */
public class MainViewController extends MenuController {
    /**
     * Stores all the levels.
     */
    private Mall mall;

    /**
     * Keeps the number of the level opened.
     */
    private int currentLevel;

    @FXML
    private TextField searchBar;

    @FXML
    private Label labelFloor;

    @FXML
    private Pane canvas;

    /**
     * Constructs the MainViewController object.
     */
    public MainViewController() {
        mall = new Mall("mall");
    }

    /**
     * Initializes the map by drawing the ground floor.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Opens the scene corresponding to good deals.
     */
    public void displayGoodDeals(){
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent node = loader.load(getClass().getResourceAsStream("/fxml/good_deals.fxml"));
            Scene scene = new Scene(node, 1280, 720);
            mainApp.getStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GoodDealsViewController controller = loader.getController();
        controller.setMainApp(mainApp);
        mainApp.getStage().show();
    }

    /**
     * Constructs a specific floor when the user presses the button corresponding to the levels..
     */
    @FXML
    void buildGroundFloor() {
        buildFloor("Rez-de-chaussée", 0);
    }

    @FXML
    void buildFirstFloor() {
        buildFloor("Premier étage", 1);
    }

    @FXML
    void buildSecondFloor() {
        buildFloor("Deuxième étage", 2);
    }

    @FXML
    void buildThirdFloor() {
        buildFloor("Troisième étage", 3);
    }

    /**
     * Constructs a floor.
     *
     * @param floor The name of the floor to display.
     * @param i The number of the level.
     */
    private void buildFloor(String floor, int i) {
        reset();
        labelFloor.setText(floor);
        currentLevel = i;

        Drawer drawer = new Drawer(canvas,mainApp);
        drawer.displayLevel(mall.getLevel(i));
    }

    /**
     * Displays a specific point of interest when the user clicks on the corresponding button.
     */
    @FXML
    void displayElevators() {
        display(Place.ELEVATORLIGHT);
    }

    @FXML
    void displayPositionUser() {
        display(Place.WHEREAMI);
    }

    @FXML
    void displayStairs() {
        display(Place.STAIRSLIGHT);
    }

    @FXML
    void displayToilet() {
        display(Place.TOILETLIGHT);
    }

    @FXML
    void displayWifi() {
        display(Place.WIFI);
    }

    /**
     * Display a point of interest.
     *
     * @param place The type of point of interest to highlight.
     */
    private void display(Place place) {
        reset();
        Drawer drawer = new Drawer(canvas,mainApp);

        Level level = mall.getLevel(currentLevel);
        drawer.displayLevel(level);
        drawer.displayPlace(level, place);
    }

    /**
     * Manages the search of a store in the level when the user clicks on the "Go!" button.
     * Verifies if the name given in the text box can link with a store of the current level.
     */
    @FXML
    void search() {
        reset();
        Drawer drawer = new Drawer(canvas,mainApp);

        Level level = mall.getLevel(currentLevel);
        String search = searchBar.getText();
        String store = level.lookForStore(search.toLowerCase().trim());
        searchBar.clear();
        if (!"not found".equals(store)) {
            drawer.highlightStore(level, store);
        }
        else {
            drawer.displayLevel(level);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Résultat de la recherche");
            alert.setHeaderText(null);
            alert.setContentText("Le magasin recherché \""+search+"\" n'a pas été trouvé. Essayez de nouveau en vérifiant son orthographe.");
            alert.showAndWait();
        }
    }

    /**
     * Launches the search when the user presses the key "Enter".
     *
     * @param event The key pressed y the user.
     */
    @FXML
    void searchEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search();
        }
    }

    /**
     * Highlights the store corresponding to a specific category.
     */
    @FXML
    void selectCare() {
        selection(Category.CARE);
    }

    @FXML
    void selectCulture() {
        selection(Category.CULTURE);
    }

    @FXML
    void selectDecoration() {
        selection(Category.DECORATION);
    }

    @FXML
    void selectFashion() {
        selection(Category.FASHION);
    }

    @FXML
    void selectServices() {
        selection(Category.SERVICES);
    }

    @FXML
    void selectRestoration() {
        selection(Category.RESTORATION);
    }

    @FXML
    void selectTechnologies() {
        selection(Category.TECHNOLOGY);
    }

    @FXML
    void selectToys() {
        selection(Category.TOYS);
    }

    /**
     * Highlights the store corresponding to a category when the user presses a "Category" button.
     *
     * @param category The name of the category of store to highlight.
     */
    private void selection(Category category) {
        reset();
        Drawer drawer = new Drawer(canvas,mainApp);
        drawer.displayCategory(mall.getLevel(currentLevel), category);
    }

    /**
     * Empties the canvas: removes all the pictures
     */
    @FXML
    void reset() {
        canvas.getChildren().clear();
    }

    public void init(){
        buildGroundFloor();
    }
}
