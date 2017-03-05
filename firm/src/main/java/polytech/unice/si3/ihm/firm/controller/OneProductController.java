package polytech.unice.si3.ihm.firm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.firm.model.commercial.Product;
import polytech.unice.si3.ihm.firm.util.ImageBuilder;
import polytech.unice.si3.ihm.firm.util.Log;

/**
 * 
 * Controler class for the view of one product selected
 *
 */
public class OneProductController extends BasicController {
    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label reference;

    @FXML
    private Label description;
    
    @FXML
    private Label price;
    
    @FXML
    private Button exit;
    
    @FXML
    private Label libName;

    @FXML
    private Label libPrice;

    @FXML
    private Label libRef;
    
    @FXML
    private ImageView promoted;

    @Override
    /**
     * {@inheritDoc}
     */
    public void initContent(Object obj){
    	super.initContent(obj);
    	
    	Product product;
    	if(obj instanceof Product)
    		product = (Product) obj;
    	else
    		return;
    	
    	image.setImage(ImageBuilder.getImage(product.getImage()));
    	updatePromoted(product.isPromoted());
    	
    	price.setText(String.valueOf(product.getPrice())+" €");
    	
    	updateName(product.getName());
    	updateRefence(product.getReference());
    	updateDescription(product.getDescription());
    	
        Log.info(this.getClass(), "Content charged");
    }
    
    /**
     * Add the promo image if it's a promoted product
     * @param promoted promoted state
     */
    private void updatePromoted(boolean promoted){
    	if(!promoted)
    		return;
    	
    	this.promoted.setImage(ImageBuilder.getImage("src/main/resources/images/promo.png", 50, 24));
    }
    
    /**
     * Update the product name
     * @param name name to update
     */
    private void updateName(String name){
    	this.name.setText(name);
    	this.name.setWrapText(true);
    }
    
    /**
     * Update the product name
     * @param name name to update
     */
    private void updateRefence(String reference){
    	this.reference.setText(reference);
    	this.reference.setWrapText(true);
    }
    
    /**
     * Update the product name
     * @param name name to update
     */
    private void updateDescription(String description){
    	this.description.setText(description);
    	this.description.setWrapText(true);
    }

}