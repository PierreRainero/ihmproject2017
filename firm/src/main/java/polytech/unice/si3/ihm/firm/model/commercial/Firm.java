package polytech.unice.si3.ihm.firm.model.commercial;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent a firm
 *
 */
public class Firm {
    private String name;
    private String description;
    private String logo;
    private String linkForMoreInfo;
    private String banner;
    private List<Store> stores;
    private List<Product> products;
    private List<String> ads; 

    /**
     * Default constructor for a firm
     */
    public Firm() {
        this(null, null, null, null, null);
    }

    /**
     * Constructor for a firm
     * @param name the name of the firm
     * @param description the description of the firm
     */
    public Firm(String name, String description, String logo, String linkForMoreInfo, String banner) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.linkForMoreInfo = linkForMoreInfo;
        this.banner = banner;
        
        stores = new ArrayList<Store>();
        products = new ArrayList<Product>();
        ads = new ArrayList<String>();
    }

    /**
     * Allow to change the name of a firm
     * @param newName the new name
     */
    public void changeName(String newName){
        name = newName;
    }

    /**
     * Allow to change the description of a firm
     * @param newDescription the new description
     */
    public void changeDecription(String newDescription){
        description = newDescription;
    }


    /**
     * allow to know if the firm has at least one store
     * @return true if the firm has 0 stores
     */
    public boolean hasNoStores(){
        return stores.isEmpty();
    }

    /**
     * Allow to add a store to the firm
     * @param store the store to add
     */
    public void addStore(Store store){
        stores.add(store);
    }

    /**
     * Getter for the name of the firm
     * @return the name of the firm
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of the firm
     * @return the description of the firm
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the list of stores of the firm
     * @return the list of stores
     */
    public List<Store> getStores() {
        return stores;
    }
    
    public String getLogo(){
    	return logo;
    }
    
    public String getBanner(){
    	return banner;
    }
    
    public String getLinkForMoreInfo(){
    	return linkForMoreInfo;
    }
    
    public List<String> getAds(){
    	return ads;
    }
    
    public void addAdvertisement(String ad){
    	ads.add(ad);
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    public void addProduct(Product product){
    	products.add(product);
    }
    
}
