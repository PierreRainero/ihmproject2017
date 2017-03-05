package polytech.unice.si3.ihm.firm.model.commercial;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StoreTest {
	private Store store;
	
    @Before
    public void defineContext(){
    	store = new Store("Kebabier du coin", "Dans le coin justement", "Sophia", "00000","Alpes-Maritimes","Provence-Alpes Cote d'Azur", "PolyCenter", "Du chat de qualitay", "src/main/resources/images/nice_etoile.png");
    }
    
    @Test
    public void accessorTest(){
    	assertEquals("src/main/resources/images/nice_etoile.png", store.getImage());
    	assertEquals("00000", store.getCityNumber());
    	assertEquals("Sophia", store.getCity());
    }
    
    @Test
    public void changeStoreName(){
    	assertEquals("Kebabier du coin", store.getName());
    	store.changeStoreName("Le bucheron du coin");
    	assertEquals("Le bucheron du coin", store.getName());
    }
    
    @Test
    public void changeStoreDescription(){
    	assertEquals("Du chat de qualitay", store.getDescription());
    	store.changeStoreDescription("maybe du chien");
    	assertEquals("maybe du chien", store.getDescription());
    }
    
    @Test
    public void changeMallName(){
    	assertEquals("PolyCenter", store.getMallName());
    	store.changeStoreMallName("SophiaCity");
    	assertEquals("SophiaCity", store.getMallName());
    }
    
    @Test
    public void changeStoreAddress(){
    	assertEquals("Dans le coin justement", store.getAddress());
    	store.changeStoreAddress("Ici");
    	assertEquals("Ici", store.getAddress());
    }
}
