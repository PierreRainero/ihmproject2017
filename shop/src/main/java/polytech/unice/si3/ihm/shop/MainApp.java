package polytech.unice.si3.ihm.shop;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        JsonParser jsonParser = new JsonParser("src/main/resources/datas/content.json", stage);

        jsonParser.parseJson();

        stage.show();
    }
}
