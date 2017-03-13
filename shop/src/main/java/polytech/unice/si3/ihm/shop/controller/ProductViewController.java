package polytech.unice.si3.ihm.shop.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.shop.model.Product;

public class ProductViewController extends BasicController{

    @FXML
    private Label productDescription;

    @FXML
    private ImageView productPicture;

    @FXML
    private Label productPrice;

    @FXML
    private Label productName;

    public void initialiseView(Product product){
        this.productDescription.setText(product.getDescription().toString());
        productDescription.setWrapText(true);
        this.productPicture.setImage(new Image(product.getImageURL()));
        String price = "Prix : " + Double.toString(product.getPrice()) + " €";
        if(product.getPromotion()!=0){
            price = "Prix : " + product.getPromotedPrice() + " €" + "\n(-" + product.getPromotion() + "%)";
        }
        this.productPrice.setText(price);
        this.productPrice.setAlignment(Pos.CENTER);
        this.productName.setText(product.getName().toString());
    }

}
