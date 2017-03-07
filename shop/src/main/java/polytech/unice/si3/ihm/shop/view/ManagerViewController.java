package polytech.unice.si3.ihm.shop.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import polytech.unice.si3.ihm.shop.JsonParser;
import polytech.unice.si3.ihm.shop.model.Shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerViewController extends BasicController {

    private Shop shop;
    private File jsonFile;
    private JSONArray itemsList;

    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem newMenuItem;
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
    @FXML
    private ScrollPane productsScrollPane;

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
        saveAsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    saveAsFile(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shopName.clear();
                shopLogo.clear();
                shopLogoMin.clear();
                shopLogoText.clear();
                shopDescription.clear();
                shopMentions.clear();
                shopAdresse.clear();
                shopTelephone.clear();
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
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            for(int i = 0; i < shop.getProduct().size(); i++){
                Button button = new Button(shop.getProduct().get(i).getName());
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //TODO : Ouvrir la page de modif de produit ici
                    }
                });
                vBox.getChildren().add(button);
            }
            productsScrollPane.setContent(vBox);
        }
    }

    private void saveAsFile(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open json file");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        jsonFile = fileChooser.showSaveDialog(stage);
        if(jsonFile != null){
            saveFile();
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
