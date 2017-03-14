package polytech.unice.si3.ihm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.*;
import polytech.unice.si3.ihm.model.Info;
import java.io.IOException;
import polytech.unice.si3.ihm.shop.JsonParser;


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
    private ImageView image;

    @FXML
    private Hyperlink site;

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
        mainApp.getStage().setResizable(false);
        mainApp.getStage().show();
    }

    @FXML
    public void displayMain(){
        FXMLLoader loader = new FXMLLoader();
        Parent node = null;
        try {
            node = loader.load(getClass().getResourceAsStream("/fxml/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(node, 1280, 720);
        mainApp.getStage().setScene(scene);

        MainViewController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.init();
        mainApp.getStage().show();

    }

    public void displayStore(){
        if(this.info.getJsp() != null){
            try{
                Stage stage = new Stage();
                JsonParser js = new JsonParser(info.getJsp(),stage);
                js.parseJson();
                stage.show();
            }
            catch(Exception e){}
        }
        else{
            displayAlert();
        }

    }

    @FXML
    public void initData(Info info) {
        this.info = info;
        name.setText(info.getName());
        description.setText(info.getDescription());
        type.setText(info.getType());
        site.setText(info.getSite());
        image.setImage(new Image(info.getImage()));
    }

    @FXML
    public void goToLink(){
        mainApp.getHostServices().showDocument(site.getText());
    }

    @FXML
    public void displayAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("La page du magasin que vous souhaitez consulter est en cours de construction.");
        alert.showAndWait();
    }
}
