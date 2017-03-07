package polytech.unice.si3.ihm.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.view.MainViewController;

import java.io.IOException;

public class MainApp extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        JsonParser jsonParser = new JsonParser("src/main/resources/datas/content.json", stage);

        Shop shop = jsonParser.parseJson();

        String fxmlFile = "/fxml/main_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(700.0);
        stage.setMinWidth(1140.0);

        Scene scene = new Scene(rootNode, 1280, 720);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle(shop.getName());
        stage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(shop);

        jsonParser.createObjects(shop);

        controller.initialiseCarousel(shop);

        stage.show();
    }

    public static void show(Product product) throws Exception {
        Stage stage = new Stage();

        JsonParser jsonParser = new JsonParser("src/main/resources/datas/content.json", stage);

        jsonParser.productPopup(product);

        stage.show();
    }
}
