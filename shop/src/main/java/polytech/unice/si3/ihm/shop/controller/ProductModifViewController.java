package polytech.unice.si3.ihm.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.model.SuperType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @FXML
    private Button deleteButton;

    public void initialiseView(Shop shop, Product product){
        this.product = product;
        nom.setText(product.getName());
        image.setText(product.getImageURL());
        prix.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());
        StringBuilder typesString = new StringBuilder();

        for(SuperType superType : product.getProductType()){
            typesString.append(superType.getName().toString() + ":");
            for(String string : superType.getTypes()){
                typesString.append(string + ",");
            }
            typesString.delete(typesString.length()-1, typesString.length());
            typesString.append(".");
        }

        types.setText(typesString.toString());
        nbVentes.setText(String.valueOf(product.getSales()));
        returnButton.setOnAction(event -> {
                save();
                currentStage.close();
        });
        deleteButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression du produit");
            alert.setHeaderText("Vous êtes sur le point de supprimer ce produit");
            alert.setContentText("Etes vous sur de vouloir faire ça ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                shop.removeProduct(product);
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
        List<SuperType> typesList = new ArrayList<>();

        String[] typesArray = types.getText().split("\\.");
        for(int i = 0; i < typesArray.length ; i++) {
            String[] typesSplitArray = typesArray[i].split(":");
            SuperType st = new SuperType(typesSplitArray[0]);
            for (int j = 1; j < typesSplitArray.length; j++) {
                st.addType(typesSplitArray[j]);
            }
            typesList.add(st);
        }
        product.setProductTypes(typesList);
        product.setSales(Integer.valueOf(nbVentes.getText()));
    }
}
