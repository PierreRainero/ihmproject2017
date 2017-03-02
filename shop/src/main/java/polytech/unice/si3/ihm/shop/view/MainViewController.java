package polytech.unice.si3.ihm.shop.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.shop.model.Shop;

public class MainViewController extends BasicController {

    @FXML
    private ImageView logo;
    @FXML
    private ImageView nom;

    public void initialiseView(Shop shop){
        this.logo.setImage(new Image(shop.getLogoMin()));
        this.nom.setImage(new Image(shop.getLogoText()));
    }
}
