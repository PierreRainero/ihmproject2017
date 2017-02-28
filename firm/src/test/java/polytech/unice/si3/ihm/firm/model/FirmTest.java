package polytech.unice.si3.ihm.firm.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by SERRANO Simon on 26/02/2017.
 */
public class FirmTest {

    private Firm firm;

    @Before
    public void init(){
        firm = new Firm();
    }

    @Test
    public void changeNameTest(){
        firm.changeName("New Name");
        String expected  = "New Name";
        assertEquals(expected, firm.getName());

        firm = new Firm("Name", "desc");
        firm.changeName("New Name");
        assertEquals(expected, firm.getName());
    }

    @Test
    public void changeDescription(){
        firm.changeDecription("New desc");
        String expected = "New desc";
        assertEquals(expected, firm.getDescription());

        firm = new Firm("Name", "desc");
        firm.changeDecription("New desc");
        assertEquals(expected, firm.getDescription());
    }

    @Test
    public void addOneStore(){
        Store store = new Store();
        firm .addStore(store);
    }

}
