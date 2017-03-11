package polytech.unice.si3.ihm.firm.customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.customer.controller.MainViewController;
import polytech.unice.si3.ihm.firm.customer.json.ContentParser;
import polytech.unice.si3.ihm.firm.common.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.common.util.Log;

/**
 * 
 * Main class - Allows to start the application
 *
 */
public class MainApp extends Application {
	
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
        Log.info(this.getClass(), "Starting firm customer view");
        Firm firm = (new ContentParser()).getFirm();

        String fxmlFile = "/fxml/main_view.fxml";
        Log.debug(this.getClass(), "Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(700.0);
        stage.setMinWidth(1140.0);

        Log.debug(this.getClass(), "Showing JFX scene");
        Scene scene = new Scene(rootNode, 1280, 720);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("Enseigne du centre commercial");
        stage.setScene(scene);
        
        MainViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initContent(firm);
        
        stage.show();
    }
}
