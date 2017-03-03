package polytech.unice.si3.ihm.firm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.controller.MainViewController;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.util.ContentParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Main class - Allows to start the application
 *
 */
public class MainApp extends Application {
	/**
	 * Log file of the application
	 */
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    /**
     * Main
     * @param args line arguments at launch
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Start the view
     */
    public void start(Stage stage) throws Exception {

        log.info("Starting firm view");
        Firm firm = ContentParser.getFirm();

        String fxmlFile = "/fxml/main_view.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(700.0);
        stage.setMinWidth(1140.0);

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 1280, 720);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Enseigne du centre commercial");
        stage.setScene(scene);
        
        MainViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initContent(firm);
        log.info("Content charged");
        
        stage.show();
    }
}
