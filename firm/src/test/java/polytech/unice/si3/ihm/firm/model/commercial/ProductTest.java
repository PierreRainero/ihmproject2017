package polytech.unice.si3.ihm.firm.model.commercial;

import org.junit.Before;
import org.junit.Test;
import polytech.unice.si3.ihm.firm.model.commercial.Product;

import static org.junit.Assert.*;

public class ProductTest {
    private Product productTest;

    @Before
    public void defineContext(){
    	productTest =  new Product("Product Test", "src/main/resources/images/games/mosserEffect.png", "T4sT", "descri", 2.);
    }

    @Test
    public void productIsPromoted(){
    	assertFalse(productTest.isPromoted());
    	
        productTest.markProductAsPromoted();
        assertTrue(productTest.isPromoted());
        
        productTest.unmarkProductAsPromoted();
        assertFalse(productTest.isPromoted());
    }

    @Test
    public void changeProductName(){
        String expected = "expected";
        productTest.changeProductName(expected);
        assertEquals(expected, productTest.getName());
    }

    @Test
    public void changeProductDescription(){
        String expected = "expected";
        productTest.changeProductDescription(expected);
        assertEquals(expected, productTest.getDescription());
    }

    @Test
    public void changeProductPrice(){
        double expected = 18.50;
        productTest.changeProductPrice(expected);
        assertEquals(expected, productTest.getPrice(), 0.50);
    }

    @Test
    public void changeProductImage(){
        assertEquals("src/main/resources/images/games/mosserEffect.png", productTest.getImage());
        productTest.changeProductImage("src/main/resources/images/games/residentEvil.png");
        assertEquals("src/main/resources/images/games/residentEvil.png", productTest.getImage());
    }
}
