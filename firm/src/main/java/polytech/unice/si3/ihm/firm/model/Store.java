package polytech.unice.si3.ihm.firm.model;



/**
 * Class that represent a store
 *
 */
public class Store {
    private String name;
    private String address;
    private String city;
    private String cityNumber;
    private String mallName;
    private String description;
    private String image;
    private Inventory inventory;

    /**
     * Default constructor for a store
     */
    public Store() {
        this(null, null, null, null, null, null);
    }

    /**
     * Constructor for a store
     * @param name the name of the store
     * @param address the address of the store
     * @param mallName the name of the mall where the store is
     * @param description the description of the store
     */
    public Store(String name, String address, String city, String cityNumber, String mallName, String description) {
        this.name = name;
        this.address = address;
        this.city=city;
        this.cityNumber = cityNumber;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the link of the store's image
     * @return the link
     */
    public String getImage() {
        return image;
    }

    /**
     * Getter for the city where the store is located
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for the number of the department where the store is located
     * @return the cityNumber
     */
    public String getCityNumber() {
        return cityNumber;
    }
}

