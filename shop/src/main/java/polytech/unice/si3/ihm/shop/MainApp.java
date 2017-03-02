package polytech.unice.si3.ihm.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.shop.model.Product;

import java.io.IOException;

public class MainApp extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        JsonParser jsonParser = new JsonParser("src/main/resources/datas/content.json", stage);

        jsonParser.parseJson();

        stage.show();
    }

    public static void show(Product product) throws Exception {
        Stage stage = new Stage();

        JsonParser jsonParser = new JsonParser("src/main/resources/datas/content.json", stage);

        jsonParser.productPopup(product);

        stage.show();

    }
}
