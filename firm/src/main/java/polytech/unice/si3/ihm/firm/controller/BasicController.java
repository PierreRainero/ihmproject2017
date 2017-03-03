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

public class BasicController {
	protected Stage currentStage;
    protected Scene scene;
    protected int initialeWidth;
    protected int initialeHeight;
    
    @FXML
    private AnchorPane mainContainer;
	
    public void setCurrentStage(Stage stage){
    	currentStage = stage;
    	scene = stage.getScene();
    }
    
    @FXML
    void exit(MouseEvent event) {
        currentStage.close();
    }

    @FXML
    void mouseEntered(MouseEvent event) {
    	currentStage.getScene().getRoot().setCursor(Cursor.HAND);
    }

    @FXML
    void mouseExited(MouseEvent event) {
    	currentStage.getScene().getRoot().setCursor(Cursor.DEFAULT);
    }
    
    public void initContent(Object object){
    	mainContainer.setBackground(new Background(new BackgroundImage( ImageBuilder.getImage("src/main/resources/images/bg.png"), 
    																	BackgroundRepeat.REPEAT, 
    																	BackgroundRepeat.REPEAT, 
    																	BackgroundPosition.DEFAULT,
    																	BackgroundSize.DEFAULT)));
    }
    
    public void setScene(Scene scene){
    	this.scene = scene;
    }
    
    protected void addResizeListener(){
    	return;
    }
}
