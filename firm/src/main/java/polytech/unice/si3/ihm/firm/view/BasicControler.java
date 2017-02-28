package polytech.unice.si3.ihm.firm.view;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BasicControler {
	protected Stage currentStage;
	
    public void setCurrentStage(Stage stage){
    	currentStage = stage;
    }
    
    @FXML
    void exit(MouseEvent event) {;
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
    
    public void initContent(){
    	throw new UnsupportedOperationException();
    }
}
