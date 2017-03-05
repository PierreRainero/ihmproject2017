package polytech.unice.si3.ihm.shop.view;

import javafx.fxml.FXML;
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
        this.productPrice.setText(Double.toString(product.getPrice()) + " €");
        this.productName.setText(product.getName().toString());
    }

}
