package polytech.unice.si3.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polytech.unice.si3.ihm.view.MainViewController;

import java.io.IOException;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        log.info("Starting main");
        String fxmlFile = "/fxml/main.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 1280, 720);
        //scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Centre Commercial");
        stage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setMainApp(this);

        stage.show();

    }

    public void changeScene(String fxml){
        String fxmlFile = fxml;
        FXMLLoader loader = new FXMLLoader();
        Parent node = null;
        try {
            node = (Parent)loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(node, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }
}
