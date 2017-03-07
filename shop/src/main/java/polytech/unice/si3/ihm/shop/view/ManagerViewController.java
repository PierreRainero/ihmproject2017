package polytech.unice.si3.ihm.shop.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import polytech.unice.si3.ihm.shop.JsonParser;
import polytech.unice.si3.ihm.shop.MainApp;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ManagerViewController extends BasicController {

    private Shop shop;
    private File jsonFile;
    private JSONArray itemsList;

    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private TextField shopName;
    @FXML
    private TextField shopLogo;
    @FXML
    private TextField shopLogoMin;
    @FXML
    private TextField shopLogoText;
    @FXML
    private TextArea shopDescription;
    @FXML
    private TextArea shopMentions;
    @FXML
    private TextField shopAdresse;
    @FXML
    private TextField shopTelephone;

    /**
     * Initialise la vue de base du ManagerViewController
     */
    public void initialiseView(Stage stage){
        openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loadFile(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    saveFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadFile(Stage stage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open json file");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        jsonFile = fileChooser.showOpenDialog(stage);
        if(jsonFile != null){
            JsonParser jsonParser = new JsonParser(jsonFile);
            shop = jsonParser.parseJson();
            jsonParser.createObjects(shop);
            itemsList = jsonParser.getItemsList();
        }
        if(shop != null){
            shopName.setText(shop.getName());
            shopLogo.setText(shop.getLogo());
            shopLogoMin.setText(shop.getLogoMin());
            shopLogoText.setText(shop.getLogoText());
            shopDescription.setText(shop.getAbout());
            shopMentions.setText(shop.getLegalNotice());
            shopAdresse.setText(shop.getAdress());
            shopTelephone.setText(shop.getPhone());
        }
    }


    private void saveFile() throws IOException {
        FileWriter fileWriter = new FileWriter(jsonFile, false);
        JSONObject content = new JSONObject();
        content.put("name",shopName.getText());
        content.put("logo",shopLogo.getText());
        content.put("logoMin",shopLogoMin.getText());
        content.put("logoText",shopLogoText.getText());
        content.put("about",shopDescription.getText());
        content.put("legalNotice",shopMentions.getText());
        content.put("adress",shopAdresse.getText());
        content.put("phone",shopTelephone.getText());
        content.put("itemsList", itemsList);
        fileWriter.write(content.toString());
        fileWriter.close();
    }
}
