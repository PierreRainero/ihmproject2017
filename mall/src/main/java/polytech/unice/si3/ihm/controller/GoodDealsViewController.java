package polytech.unice.si3.ihm.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import java.io.*;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.model.GoodDeal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import polytech.unice.si3.ihm.model.Info;
import javafx.scene.control.Alert;

/**
 * Created by Ken on 02/03/2017.
 */
public class GoodDealsViewController extends MenuController {
    private List<GoodDeal> deals;
    private List<ImageView> imageDeals;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public GoodDealsViewController() {
        deals = new ArrayList<GoodDeal>();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        imageDeals = new ArrayList<ImageView>();
        imageDeals.add(deal1);
        imageDeals.add(deal2);
        imageDeals.add(deal3);
        buildDeals();
        printDeals();
        initCursor();
    }

    @FXML
    private ImageView deal1;

    @FXML
    private ImageView deal2;

    @FXML
    private ImageView deal3;

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
        mainApp.getStage().show();

    }

    public void buildDeals(){
        JSONParser parser = new JSONParser();

        try {
            JSONObject js = (JSONObject)parser.parse(new FileReader(getClass().getResource("/json/deals.json").getFile()));
            JSONObject jsStore;
            GoodDeal gd;
            for(int i = 0 ; i < 3 ; i++){
                jsStore = (JSONObject)((JSONArray) js.get(String.valueOf(i))).get(0);
                gd = new GoodDeal("/images/" + jsStore.get("imageName"),"/json/store/" + jsStore.get("jsonName"));
                deals.add(gd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDeals(){
        for(int i = 0 ; i<deals.size();i++){
            imageDeals.get(i).setFitHeight(250);
            imageDeals.get(i).setFitWidth(1260);
            imageDeals.get(i).setImage(new Image(deals.get(i).getImagePath()));
        }
    }

    public void displayStore1(){
        Info i = deals.get(0).getInfoStore();
        displayInfo(i);
    }

    public void displayStore2(){
        displayAlert();
    }

    public void displayStore3(){
        Info i = deals.get(2).getInfoStore();
        displayInfo(i);
    }
    /*public void displayStore3(){
        try{
            Stage stage = new Stage();
            JsonParser js = new JsonParser("src/main/resources/datas/content.json",stage);
            js.parseJson();
            stage.show();
        }
        catch(Exception e){}
    }*/

    @FXML
    public void displayAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("La page du magasin que vous souhaitez consulter est en cours de construction.");
        alert.showAndWait();
    }

    @FXML
    public void displayInfo(Info info){
        FXMLLoader loader = new FXMLLoader();
        Parent node = null;
        try {
            node = loader.load(getClass().getResourceAsStream("/fxml/info.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(node, 1280, 720);
        mainApp.getStage().setScene(scene);

        InfoController controller = loader.getController();
        controller.setMainApp(mainApp);
        controller.initData(info);

        mainApp.getStage().show();
    }

    @FXML
    public void initCursor(){
        deal1.setCursor(Cursor.HAND);
        deal2.setCursor(Cursor.HAND);
        deal3.setCursor(Cursor.HAND);
    }

}
