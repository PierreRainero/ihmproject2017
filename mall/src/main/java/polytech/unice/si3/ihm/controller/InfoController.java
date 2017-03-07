package polytech.unice.si3.ihm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import polytech.unice.si3.ihm.MainApp;
import polytech.unice.si3.ihm.model.Info;

import java.io.IOException;

/**
 * Created by Ken on 07/03/2017.
 */
public class InfoController extends MenuController {
    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label type;

    @FXML
    private Label site;

    private Info info;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public InfoController() {
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void displayDeals(){
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

    public void setInfo(Info info){
        this.info = info;
    }
}
