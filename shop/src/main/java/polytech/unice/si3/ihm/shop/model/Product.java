package polytech.unice.si3.ihm.shop.model;

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
    private List<String> productTypes;
    private int sales;

    /**
     * Constructeur normal de produit
     * @param name nom du produit
     * @param imageURL url de l'image correspondant au produit
     * @param price prix du produit
     */
    public Product(String name, String imageURL, double price, String description, List<String> productType, int sales){
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.promotion = Optional.empty();
        this.productTypes = productType;
        this.description = description;
        this.sales = sales;
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

    /**
     * Permet de mettre un produit en promotion
     * @param promotion taux de promotion en %
     */
    public void setPromotion(Promotion promotion){
        this.promotion = Optional.of(promotion);
    }

    /**
     * Retourne le nom du produit
     * @return nom du produit
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retourne l'url de l'image du produit
     * @return url de l'image du produit
     */
    public String getImageURL() {
        return this.imageURL;
    }

    /**
     * Retourne une List contenant le(s) type(s) du produit
     * @return type(s) du produit
     */
    public List<String> getProductType(){
        return this.productTypes;
    }

    /**
     * Retourne la description du produit
     * @return description du produit
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Retourne le noombre de ventes du produit
     * @return nombre de ventes du produit
     */
    public int getSales(){
        return this.sales;
    }
}
