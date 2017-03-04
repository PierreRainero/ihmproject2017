package polytech.unice.si3.ihm.shop.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class Product {
    private String name;
    private String imageURL;
    private String description;
    private double price;
    private Optional<Promotion> promotion;
    private List<String> objectTypes;

    /**
     * Constructeur normal de produit
     * @param name nom du produit
     * @param imageURL url de l'image correspondant au produit
     * @param price prix du produit
     */
    public Product(String name, String imageURL, double price, String description){
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.promotion = Optional.empty();
        this.objectTypes = new ArrayList<String>();
        this.description = description;
    }

    /**
     * Retourne le prix de base de l'objet.
     * @return price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Retourne le prix avec promotion de l'objet
     * @return prix de l'objet avec promotion, -1 si il n'est pas en promotion
     */
    public double getPromotedPrice(){
        if(this.promotion.isPresent()){
            return this.price * promotion.get().getPercent();
        }else {
            return -1;
        }
    }

    public void setPromotion(Promotion promotion){
        this.promotion = Optional.of(promotion);
    }

    public String getName() {
        return this.name;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void addObjectType(String s){
        objectTypes.add(s);
    }

    public List<String> getObjectTypes(){
        return this.objectTypes;
    }

    public String getDescription(){
        return this.description;
    }
}
