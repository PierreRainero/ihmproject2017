package polytech.unice.si3.ihm.view;

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
        buildGroundFloor();
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
    private TextField searchBar;

    @FXML
    private Label labelFloor;

    @FXML
    private Pane canvas;

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

    private void buildFloor(String floor, int i) {
        reset();
        labelFloor.setText(floor);
        currentLevel = i;

        Drawer drawer = new Drawer(canvas);
        drawer.displayLevel(mall.getLevel(i));
    }

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
        display(Place.TOILET);
    }

    @FXML
    void displayWifi() {
        display(Place.WIFI);
    }

    private void display(Place place) {
        reset();
        Drawer drawer = new Drawer(canvas);

        Level level = mall.getLevel(currentLevel);
        drawer.displayLevel(level);
        drawer.displayPlace(level, place);
    }

    @FXML
    void search() {
        reset();
        Drawer drawer = new Drawer(canvas);

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

    @FXML
    void searchEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search();
        }
    }

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

    private void selection(Category category) {
        reset();
        Drawer drawer = new Drawer(canvas);
        drawer.displayCategory(mall.getLevel(currentLevel), category);
    }

    private void reset() {
        canvas.getChildren().clear();
    }
}
