package polytech.unice.si3.ihm.firm.model;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Class that represents a commercialized product
 *
 */
public class Product {
    private String name;
    private String reference;
    private String description;
    private double price;
    private boolean promoted = false;
    private String image;

    /**
     * Constructor for a product
     * @param name the name of the product
     * @param reference the reference of the product
     * @param description the description of the product
     * @param price the price of the product
     */
    public Product(String name, String reference, String description, double price) {
        this.name = name;
        this.reference = reference;
        this.description = description;
        this.price = price;
    }


    /**
     * Allow to mark a product as promoted to have it highlighted on store's page
     */
    public void markProductAsPromoted(){
        promoted = true;
    }

    /**
     * Allow to unmark a product as promoted to remove it from the highlighted products
     */
    public void unmarkProductAsPromoted(){
        promoted = false;
    }

    /**
     * Allow to change the price of a product
     * @param newPrice the new price
     */
    public void changeProductPrice(double newPrice){
        price = newPrice;
    }

    /**
     * Allow to change the name of a product
     * @param newName the new name
     */
    public void changeProductName(String newName){
        name = newName;
    }

    /**
     * Allow to change the description of a product (not modify it yet)
     * @param newDescription the new description
     */
    public void changeProductDescription(String newDescription){
        description = newDescription;
    }

    /**
     * Allow to change the image of a product
     * @param newImage the new image
     */
    public void changeProductImage(String newImage){
        image = newImage;
    }

    /**
     * Allow to know if the product is promoted
     * @return true if the product is promoted
     */
    public boolean isPromoted(){return promoted;}

    /**
     * Getter for the name of the product
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the reference of the product
     * @return the reference of the product
     */
    public String getReference() {
        return reference;
    }

    /**
     * Getter for the description of the product
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the price of the product
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for the image of the product
     * @return the image of the product
     */
    public String getImage() {
        return image;
    }
}
