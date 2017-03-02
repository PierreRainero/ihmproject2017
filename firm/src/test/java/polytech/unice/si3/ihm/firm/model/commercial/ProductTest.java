package polytech.unice.si3.ihm.firm.model.commercial;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import polytech.unice.si3.ihm.firm.model.commercial.Product;

import static org.junit.Assert.*;

public class ProductTest {

    private Product productTest;
    private Product productTest1;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void init(){
    }

    @Test
    public void productIsPromoted(){
        productTest.markProductAsPromoted();
        assertTrue(productTest.isPromoted());
        assertFalse(productTest1.isPromoted());
    }

    @Test
    public void productIsNoMorePromoted(){
        productTest.markProductAsPromoted();
        productTest.unmarkProductAsPromoted();
        productTest1.markProductAsPromoted();
        assertFalse(productTest.isPromoted());
        assertTrue(productTest1.isPromoted());
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




}
