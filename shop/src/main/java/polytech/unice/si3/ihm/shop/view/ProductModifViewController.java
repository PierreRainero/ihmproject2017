package polytech.unice.si3.ihm.shop.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polytech.unice.si3.ihm.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductModifViewController extends BasicController{

    private Product product;

    @FXML
    private TextField nom;
    @FXML
    private TextField image;
    @FXML
    private TextField prix;
    @FXML
    private TextArea description;
    @FXML
    private TextArea types;
    @FXML
    private TextField nbVentes;
    @FXML
    private Button returnButton;

    public void initialiseView(Product product){
        this.product = product;
        nom.setText(product.getName());
        image.setText(product.getImageURL());
        prix.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());
        String typesString = "";
        for(int i = 0; i < product.getProductType().size(); i++){
            typesString += product.getProductType().get(i) + ",";
        }
        types.setText(typesString);
        nbVentes.setText(String.valueOf(product.getSales()));
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save();
                currentStage.close();
            }
        });
    }

    private void save(){
        product.setName(nom.getText());
        product.setImageURL(image.getText());
        product.setPrice(Double.valueOf(prix.getText()));
        product.setDescription(description.getText());
        List<String> typesList = new ArrayList<>();
        String[] typesArray = types.getText().split(",");
        for(int i = 0; i < typesArray.length; i++){
            typesList.add(typesArray[i]);
        }
        product.setProductTypes(typesList);
        product.setSales(Integer.valueOf(nbVentes.getText()));
    }

}
