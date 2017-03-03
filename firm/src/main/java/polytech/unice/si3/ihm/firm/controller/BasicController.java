package polytech.unice.si3.ihm.firm.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;
/**
 * 
 * Basic controller class - Skeleton for controlers
 *
 */
public class BasicController {
	protected Stage currentStage;
    protected Scene scene;
    protected int initialeWidth;
    protected int initialeHeight;
    
    @FXML
    private AnchorPane mainContainer;
	
    /**
     * Modification accessor for the stage of the current view
     * @param stage new stage to add
     */
    public void setCurrentStage(Stage stage){
    	currentStage = stage;
    	scene = stage.getScene();
    }
    
    @FXML
    /**
     * Close the current stage (view)
     * @param event event to catch
     */
    void exit(MouseEvent event) {
        currentStage.close();
    }

    @FXML
    /**
     * Actions when the mouse is in a specific zone (by default change cursor to a hand)
     * @param event event to catch
     */
    void mouseEntered(MouseEvent event) {
    	currentStage.getScene().getRoot().setCursor(Cursor.HAND);
    }

    @FXML
    /**
     * Actions when the mouse is in a specific zone (by default change cursor to the default cursor)
     * @param event event to catch
     */
    void mouseExited(MouseEvent event) {
    	currentStage.getScene().getRoot().setCursor(Cursor.DEFAULT);
    }
    
    /**
     * Initialize all controls
     * @param object object containing all datas to initialize the controls
     */
    public void initContent(Object object){
    	mainContainer.setBackground(new Background(new BackgroundImage( ImageBuilder.getImage("src/main/resources/images/bg.png"), 
    																	BackgroundRepeat.REPEAT, 
    																	BackgroundRepeat.REPEAT, 
    																	BackgroundPosition.DEFAULT,
    																	BackgroundSize.DEFAULT)));
    }
    
    /**
     * Modification accessor for the scene of the current view
     * @param scene new scene to add
     */
    public void setScene(Scene scene){
    	this.scene = scene;
    }
    
    /**
     * Allows to add listeners to catch when the window is resized
     */
    protected void addResizeListener(){
    	return;
    }
}
