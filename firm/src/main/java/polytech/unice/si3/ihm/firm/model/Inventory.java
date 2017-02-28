package polytech.unice.si3.ihm.firm.model;

import polytech.unice.si3.ihm.firm.exceptions.ProductIsNotInTheInventoryException;

import java.util.*;

/**
 * Class that represent the inventory of a store
 *
 * Created by SERRANO Simon on 17/02/2017.
 */
public class Inventory {

    Set<Product> products;

    /**
     * Constructor for an inventory
     */
    public Inventory() {
        products = new HashSet<Product>();
    }

    /**
     * Allow to add a product to the inventory
     * @param product the product to add
     */
    public void addToInventory(Product product){
        products.add(product);
    }

    /**
     * Allow to remove a product of the inventory
     * @param product the product to remove
     * @throws ProductIsNotInTheInventoryException if the product to remove is not in the inventory
     */
    public void removeProduct(Product product) throws ProductIsNotInTheInventoryException {
        if (products.contains(product))
            products.remove(product);
        else throw new ProductIsNotInTheInventoryException(product);
    }

    public boolean isProductInInventory(Product product){
        return products.contains(product);
    }

    /**
     * Allow to know if the inventory is empty
     * @return true if the inventory is empty
     */
    public boolean isEmpty(){
        return products.isEmpty();
    }


}
