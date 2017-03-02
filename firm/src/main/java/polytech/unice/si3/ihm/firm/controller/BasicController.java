package polytech.unice.si3.ihm.firm.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BasicController {
	protected Stage currentStage;
    protected Scene scene;
    protected int initialeWidth;
    protected int initialeHeight;
	
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
    	throw new UnsupportedOperationException();
    }
    
    public void setScene(Scene scene){
    	this.scene = scene;
    }
    
    protected void addResizeListener(){
    	throw new UnsupportedOperationException();
    }
}
