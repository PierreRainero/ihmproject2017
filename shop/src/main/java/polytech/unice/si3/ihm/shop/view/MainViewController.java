package polytech.unice.si3.ihm.shop.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.shop.MainApp;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;


public class MainViewController extends BasicController {

    private Product carouselLeft;
    private Product carouselRight;
    private Product carouselCenter;

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

    public void initialiseCarousel(Product leftProduct, Product centerProduct, Product rightProduct){
        this.carouselLeft = leftProduct;
        this.carouselLeftImage.setImage(new Image(leftProduct.getImageURL()));
        this.carouselLeftName.setText(leftProduct.getName());

        this.carouselCenter = centerProduct;
        this.carouselCenterImage.setImage(new Image(centerProduct.getImageURL()));
        this.carouselCenterName.setText(centerProduct.getName());

        this.carouselRight = rightProduct;
        this.carouselRightImage.setImage(new Image(rightProduct.getImageURL()));
        this.carouselRightName.setText(rightProduct.getName());

        carouselCenterImage.setOnMouseClicked(event -> {
            try {
                MainApp.show(carouselCenter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        carouselCenterImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselCenterImage.setOnMouseExited(event -> {
            mouseExited(event);
        });

        carouselLeftImage.setOnMouseClicked(event -> {
            try {
                MainApp.show(carouselLeft);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        carouselLeftImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselLeftImage.setOnMouseExited(event -> {
            mouseExited(event);
        });

        carouselRightImage.setOnMouseClicked(event -> {
            try {
                MainApp.show(carouselRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        carouselRightImage.setOnMouseEntered(event -> {
            mouseEntered(event);
        });

        carouselRightImage.setOnMouseExited(event -> {
            mouseExited(event);
        });
    }

}
