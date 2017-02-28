package polytech.unice.si3.ihm.firm.model;

import javafx.scene.image.Image;

/**
 * Class that represent a store
 *
 */
public class Store {
    private String name;
    private String address;
    private String mallName;
    private String description;
    private String image;
    private Inventory inventory;

    /**
     * Default constructor for a store
     */
    public Store() {
        this(null, null, null, null);
    }

    /**
     * Constructor for a store
     * @param name the name of the store
     * @param address the address of the store
     * @param mallName the name of the mall where the store is
     * @param description the description of the store
     */
    public Store(String name, String address, String mallName, String description) {
        this.name = name;
        this.address = address;
        this.mallName = mallName;
        this.description = description;
        inventory = new Inventory();
    }

    /**
     * Allow to change the name of the store
     * @param newName the new name
     */
    public void changeStoreName(String newName){
        name=newName;
    }

    /**
     * Allow to change the description of the store
     * @param newDescription the new description
     */
    public void changeStoreDescription(String newDescription){
        description = newDescription;
    }

    /**
     * Allow to change the name of the mall where the store is
     * @param newMallName the new name
     */
    public void changeStoreMallName(String newMallName){
        mallName=newMallName;
    }

    /**
     * Allow the change the address of the store
     * @param newAddress the new address
     */
    public void changeStoreAddress(String newAddress){
        address=newAddress;
    }

    /**
     * Getter for the name of the store
     * @return the name of the store
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the address of the store
     * @return the address of the store
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for the name of the mall where the store is
     * @return the name of the mall
     */
    public String getMallName() {
        return mallName;
    }

    /**
     * Getter for the description of the store
     * @return
     */
    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
