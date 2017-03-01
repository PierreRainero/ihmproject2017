package polytech.unice.si3.ihm.view;

import polytech.unice.si3.ihm.MainApp;
import javafx.fxml.FXML;
/**
 * Created by Ken on 28/02/2017.
 */
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
}
