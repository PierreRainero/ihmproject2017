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
    @FXML
    private ImageView carouselLeftImage;
    @FXML
    private Label carouselLeftName;
    @FXML
    private ImageView carouselCenterImage;
    @FXML
    private Label carouselCenterName;
    @FXML
    private ImageView carouselRightImage;
    @FXML
    private Label carouselRightName;

    public void initialiseView(Shop shop){
        this.logo.setImage(new Image(shop.getLogoMin()));
        this.nom.setImage(new Image(shop.getLogoText()));
    }

    public void initialiseCarousel(String leftImage, String leftName, String centerImage, String centerName, String rightImage, String rightName){
        this.carouselLeftImage.setImage(new Image(leftImage));
        this.carouselLeftName.setText(leftName);
        this.carouselCenterImage.setImage(new Image(centerImage));
        this.carouselCenterName.setText(centerName);
        this.carouselRightImage.setImage(new Image(rightImage));
        this.carouselRightName.setText(rightName);
    }
}
