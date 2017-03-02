package polytech.unice.si3.ihm.firm.model.commercial;

import org.junit.Before;
import org.junit.Test;
import polytech.unice.si3.ihm.firm.exceptions.ProductIsNotInTheInventoryException;
import polytech.unice.si3.ihm.firm.model.commercial.Inventory;
import polytech.unice.si3.ihm.firm.model.commercial.Product;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void init(){
        inventory = new Inventory();
    }

    @Test
    public void isEmpty(){
        assertTrue(inventory.isEmpty());
    }

    @Test
    public void addAProductToTheInventory(){
        Product product = new Product("Television 4K", "TV4K", "Television 150\" 4K", 799.99);
        inventory.addToInventory(product);
        assertTrue(inventory.isProductInInventory(product));
    }

    @Test
    public void removeProductFromTheInventory() throws ProductIsNotInTheInventoryException {
        Product product =  new Product("Television 4K", "TV4K", "Television 150\" 4K", 799.99);
        inventory.addToInventory(product);
        inventory.removeProduct(product);
        assertFalse(inventory.isProductInInventory(product));
    }

    @Test(expected = ProductIsNotInTheInventoryException.class)
    public void removeAProductWhichIsNotInTheInventory() throws ProductIsNotInTheInventoryException {
        Product product =  new Product("Television 4K", "TV4K", "Television 150\" 4K", 799.99);
        inventory.removeProduct(product);
    }

}
