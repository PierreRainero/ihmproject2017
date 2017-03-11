package polytech.unice.si3.ihm.firm.common.model.commercial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import polytech.unice.si3.ihm.firm.common.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.common.model.commercial.Product;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

public class FirmTest {
    private Firm firm;

    @Before
    public void defineContext(){
        firm = new Firm("Steam", "Steam c'est très bien !", "src/main/resources/images/logoMin.png", "http://store.steampowered.com/?l=french", "src/main/resources/images/logo.png");
    }
    
    @Test
    public void accessorTest(){
    	assertEquals("src/main/resources/images/logoMin.png", firm.getLogo());
    	assertEquals("src/main/resources/images/logo.png", firm.getBanner());
    	assertEquals("http://store.steampowered.com/?l=french", firm.getLinkForMoreInfo());
    }

    @Test
    public void changeNameTest(){
    	assertEquals("Steam", firm.getName());
    	firm.changeName("Origins");
    	assertEquals("Origins", firm.getName());
    }

    @Test
    public void changeDescription(){
    	assertEquals("Steam c'est très bien !", firm.getDescription());
    	firm.changeDecription("Ou pas");
    	assertEquals("Ou pas", firm.getDescription());
    }

    @Test
    public void addOneStore(){
    	assertTrue(firm.hasNoStores());
        Store store = new Store();
        firm.addStore(store);
        assertFalse(firm.hasNoStores());
        assertEquals(1, firm.getStores().size());
    }
    
    @Test
    public void addOneProduct(){
    	assertEquals(0, firm.getProducts().size());
    	Product product = new Product("Product Test", "", "T4sT", "descri", 2.);
        firm.addProduct(product);
        assertEquals(1, firm.getProducts().size());
    }
    
    @Test
    public void addOneAd(){
    	assertEquals(0, firm.getAds().size());
    	String ad = new String("Awesome AD !!");
        firm.addAdvertisement(ad);
        assertEquals(1, firm.getAds().size());
    }

}
