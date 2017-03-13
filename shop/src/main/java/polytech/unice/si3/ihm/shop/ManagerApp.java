package polytech.unice.si3.ihm.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.shop.view.ManagerViewController;

public class ManagerApp extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/manager_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setResizable(false);


        Scene scene = new Scene(rootNode, 1280, 720);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Manager panel");
        stage.setScene(scene);

        ManagerViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(stage);

        stage.show();
    }
}
