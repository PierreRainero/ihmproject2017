package polytech.unice.si3.ihm.firm.managing.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.firm.common.util.ImageBuilder;
import polytech.unice.si3.ihm.firm.common.util.Log;

public class MainViewController {
    @FXML
    private ImageView banner;

    @FXML
    /**
     * {@inheritDoc}
     */
    public void initialize() {
    	banner.setImage(ImageBuilder.getImage("images/logo.png"));
    	Log.info(this.getClass(), "Content charged");
    }  
}


