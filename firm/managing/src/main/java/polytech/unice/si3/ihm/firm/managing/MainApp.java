package polytech.unice.si3.ihm.firm.managing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.common.util.Log;
import java.net.URL;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
    	Log.info(this.getClass(), "Starting firm managing view");

        String fxmlFile = "/fxml/main_managing_view.fxml";
        Log.debug(this.getClass(), "Loading FXML for main view from: {}", fxmlFile);
        URL location = getClass().getResource( fxmlFile );
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( location );
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Log.debug(this.getClass(), "Showing JFX scene");
        Scene scene = new Scene(rootNode, 500, 720);
        scene.getStylesheets().add("/styles/main.css");

        stage.setTitle("Enseigne : Gestionnaire");
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.show();
    }
}
