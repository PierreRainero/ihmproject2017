package polytech.unice.si3.ihm.shop.model;

import java.util.List;

/**
 *
 */
public class Product {
    private String name;
    private String imageURL;
    private String description;
    private double price;
    private Promotion promotion;
    private List<SuperType> productTypes;
    private int sales;

    /**
     * Constructeur normal de produit
     * @param name nom du produit
     * @param imageURL url de l'image correspondant au produit
     * @param price prix du produit
     */
    public Product(String name, String imageURL, double price, String description, List<SuperType> productType, int sales, Promotion promotion){
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.productTypes = productType;
        this.description = description;
        this.sales = sales;
        this.promotion = promotion;
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
    public double getPromotion(){
        return promotion.getPercent();
    }

    public double getPromotedPrice(){
        double result = this.price - this.price * this.promotion.getValue();
        result=(double)((int)(result*100))/100;
        return result;
    }

    /**
     * Permet de mettre un produit en promotion
     * @param promotion taux de promotion en %
     */
    public void setPromotion(Promotion promotion){
        this.promotion = promotion;
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
    public List<SuperType> getProductType(){
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

    public void setName(String name){
        this.name = name;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setProductTypes(List<SuperType> productTypes){
        this.productTypes.clear();
        for(SuperType entry : productTypes){
            SuperType superType = new SuperType(entry.name);
            for(String string : entry.getTypes()){
                superType.addType(string);
            }
            this.productTypes.add(entry);
        }
    }

    public void setSales(int sales){
        this.sales = sales;
    }
}
