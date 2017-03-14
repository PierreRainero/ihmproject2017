package polytech.unice.si3.ihm.shop.model;

import java.util.*;

public class Shop {

    private String name;
    private String logo;
    private String logoMin;
    private String logoText;
    private String about;
    private String legalNotice;
    private String adress;
    private String phone;
    private List<SuperType> types;
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
        this.products = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    /**
     * Ajoute un produit au shop
     * @param product Le produit à ajouter
     * @return True si le produit a été ajouté, false sinon
     */
    public boolean addProduct(Product product){
        if(!this.products.contains(product)){
            this.products.add(product);
            checkIfProductTypeKnown(product.getProductType());
            return true;
        }
        return false;
    }

    /**
     * Ajoute les types et SuperType qui n'ont pas encore été ajoutés.
     * @param productType List de types présents dans le produit
     */
    public void checkIfProductTypeKnown(List<SuperType> productType){
        boolean alreadyInList;
        for(SuperType type : productType){
            int size = types.size();
            alreadyInList = false;

            for(int j=0;j<size;j++){
                if(type.getName().equals(types.get(j).getName())){
                    alreadyInList = true;
                }
            }
            if(!alreadyInList){
                SuperType st = new SuperType(type.name);
                for(String str : type.getTypes()){
                    st.addType(new String(str));
                }
                this.types.add(st);
            }else{
                int index = getStringIndex(type.getName());
                for(String subTypes : type.getTypes()){
                    if(!types.get(index).getTypes().contains(subTypes)){
                        types.get(index).addType(subTypes);
                    }
                }
            }
        }
    }

    /**
     * Retourne la position d'un type, en fonction d'une string.
     * @param superTypeName nom du type recherché
     * @return
     */
    private int getStringIndex(String superTypeName){
        int i=0;
        for(SuperType superType : types){
            if(superType.getName().equals(superTypeName))
                return i;
            i++;
        }
        return -1;
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
     * Retourne la liste contenant les produits
     * @return List contenant les différents produits contenus dans le magasin
     */
    public List<Product> getProducts(){
        return this.products;
    }

    /**
     * Retourne la liste contenant les produits, triée par ordre croissant
     * @return List contenant les différents produits contenus dans le magasin triés par ordre croissant
     */
    public void sortByCroissantPrice(){
        this.products.sort((p1, p2) -> {
            if(p1.getPrice()>p2.getPrice())
                return 1;
            else if(p1.getPrice()<p2.getPrice())
                return -1;
            else
                return 0;
        });
    }

    /**
     * Retourne la liste contenant les produits, triée par ordre décroissant
     * @return List contenant les différents produits contenus dans le magasin trié par ordre croissant
     */
    public void sortByDeCroissantPrice(){
        this.products.sort((p1, p2) -> {
            if(p1.getPrice()<p2.getPrice())
                return 1;
            else if(p1.getPrice()>p2.getPrice())
                return -1;
            else
                return 0;
        });
    }

    /**
     * Retourne la liste contenant les produits, triée par nombre de ventes
     * @return List contenant les différents produits contenus dans le magasin trié par nombre de ventes
     */
    public void sortByPopularity(){
        this.products.sort((p1, p2) -> {
            if(p1.getSales()<p2.getSales())
                return 1;
            else if(p1.getSales()>p2.getSales())
                return -1;
            else
                return 0;
        });
    }

    /**
     * Retourne le produit ayant pour nom la string en argument
     * @return product correspondant, null si il n'y en a aps
     */
    public Product getProduct(String productName){
        for(Product product : products)
            if(product.getName().equals(productName))
                return product;
        return null;
    }

    /**
     * Permet de récupérer les trois produits les plus vendus dans le magasin.
     * @return List contenant les trois produits ayant été le plus vendu, trié par nombre de ventes
     */
    public List<Product> getTopSales(){
        List<Product> returned = new ArrayList<>();
        sortByPopularity();
        for(int i=0;i<3;i++)
            returned.add(products.get(i));

        return returned;
    }


    /**
     * Retourne la liste contenant les produits, triée par nombre de ventes
     * @return List contenant les différents produits contenus dans le magasin trié par nombre de ventes
     */
    public List<Product> sortByPromotion(){
        List<Product> returned = new ArrayList<>();
        returned.addAll(this.products);
        returned.sort((p1, p2) -> {
            if(p1.getPromotion()<p2.getPromotion())
                return 1;
            else if(p1.getPromotion()>p2.getPromotion())
                return -1;
            else
                return 0;
        });
        return returned;
    }

    /**
     * Permet de récupérer les trois produits les plus vendus dans le magasin.
     * @return List contenant les trois produits ayant été le plus vendu, trié par nombre de ventes
     */
    public List<Product> getTopPromotion(){
        List<Product> returned = sortByPromotion();
        List<Product> toReturn = new ArrayList<>();

        for(int i=0;i<returned.size();i++){
            if(returned.get(i).getPromotion()!=0)
                toReturn.add(returned.get(i));
        }

        return toReturn;
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

    public List<SuperType> getTypes(){
        return this.types;
    }
}
