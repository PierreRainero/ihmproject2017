package polytech.unice.si3.ihm.firm.model;

import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by SERRANO Simon on 18/02/2017.
 */
public class ProductTest {

    private Product productTest;
    private Product productTest1;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void init(){
        productTest = new Product("disque vinyl", "DV1", "Disque vinyl de qualité", 17.99);
        productTest1 = new Product("pot de peinture rouge", "PP1", "Pot de peinture pour bois", 4.99);
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
