package polytech.unice.si3.ihm.shop.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import polytech.unice.si3.ihm.shop.JsonParser;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.model.SuperType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerViewController extends BasicController {

    private Shop shop;
    private File jsonFile;

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
            for(int i = 0; i < shop.getProducts().size(); i++){
                final Product product = shop.getProducts().get(i);
                Button button = new Button(product.getName());
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            openModifProduct(product);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                vBox.getChildren().add(button);
            }
            productsScrollPane.setContent(vBox);
        }
    }

    private void openModifProduct(Product product) throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/product_modif_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(400);
        stage.setMinWidth(500);

        Scene scene = new Scene(rootNode, 500,600);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Modification de " + product.getName());
        stage.setScene(scene);

        ProductModifViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(product);
        stage.show();
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
        content.put("itemsList", getItemList());

        fileWriter.write(content.toString());
        fileWriter.close();
    }

    private JSONArray getItemList(){
        JSONArray array = new JSONArray();
        for(int i = 0; i < shop.getProducts().size(); i++){
            JSONObject object = new JSONObject();
            object.put("name", shop.getProducts().get(i).getName());
            object.put("imageURL", shop.getProducts().get(i).getImageURL());
            object.put("price", shop.getProducts().get(i).getPrice());
            object.put("description", shop.getProducts().get(i).getDescription());
            JSONObject types = new JSONObject();
            for(SuperType superType : shop.getProducts().get(i).getProductType())
                types.put(superType.getName(), superType.getTypes());
            object.put("types", types);
            object.put("sales", shop.getProducts().get(i).getSales());
            array.put(object);
        }
        return array;
    }
}
