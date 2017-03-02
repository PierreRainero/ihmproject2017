package polytech.unice.si3.ihm.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private String name;
    private String logo;
    private String logoMin;
    private String logoText;
    private String about;
    private String legalNotice;
    private String adress;
    private String phone;
    private List<Product> products;

    /**
     * Constructeur d'un shop
     * @param name Nom du shop
     */
    public Shop(String name, String logo, String logoMin, String logoText) {
        this.name = name;
        this.logo = logo;
        this.logoMin = logoMin;
        this.logoText = logoText;
        this.products = new ArrayList<Product>();
    }

    /**
     * Ajoute un produit au shop
     * @param product Le produit à ajouter
     * @return True si le produit a été ajouté, false sinon
     */
    public boolean addProduct(Product product){
        if(!this.products.contains(product)){
            this.products.add(product);
            return true;
        }
        return false;
    }

    /**
     * Retire un produit du shop
     * @param product Le produit à retirer
     * @return True si le produit a été retiré, false sinon
     */
    public boolean removeProduct(Product product){
        if(this.products.contains(product)) {
            this.products.remove(product);
            return true;
        }
        return false;
    }

    /**
     * Change la description du shop
     * @param about la nouvelle description du shop
     */
    public void setAbout(String about){
        this.about = about;
    }

    /**
     * Change les mentions légales du shop
     * @param legalNotice les nouvelles mentions légales du shop
     */
    public void setLegalNotice(String legalNotice){
        this.legalNotice = legalNotice;
    }

    /**
     * Change l'adresse du shop
     * @param adress la nouvelle adresse du shop
     */
    public void setAdress(String adress){
        this.adress = adress;
    }

    /**
     * Change le numéro de téléphone du shop
     * @param phone le nouveau numéro de téléphone du shop
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * Renvoie le nom du shop
     * @return le nom du shop
     */
    public String getName(){
        return this.name;
    }

    /**
     * Renvoie le logo du shop
     * @return le logo du shop
     */
    public String getLogo(){
        return this.logo;
    }

    /**
     * Renvoie le logo minimal du shop
     * @return le logo minimal du shop
     */
    public String getLogoMin(){
        return this.logoMin;
    }

    /**
     * Renvoie le texte du logo du shop
     * @return le texte du logo du shop
     */
    public String getLogoText(){
        return this.logoText;
    }

    /**
     * Renvoie la description du shop
     * @return la description du shop
     */
    public String getAbout(){
        return this.about;
    }

    /**
     * Renvoie les mentions légales du shop
     * @return les mentions légales du shop
     */
    public String getLegalNotice(){
        return this.legalNotice;
    }

    /**
     * Renvoie l'adresse du shop
     * @return l'adresse du shop
     */
    public String getAdress(){
        return this.adress;
    }

    /**
     * Renvoie le numéro de téléphone du shop
     * @return le numéro de téléphone du shop
     */
    public String getPhone(){
        return this.phone;
    }
}
