package polytech.unice.si3.ihm.firm.model.commercial;

import org.junit.Before;
import org.junit.Test;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.model.commercial.Store;

import static org.junit.Assert.assertEquals;

public class FirmTest {

    private Firm firm;

    @Before
    public void init(){
        firm = new Firm();
    }

    @Test
    public void changeNameTest(){

    }

    @Test
    public void changeDescription(){

    }

    @Test
    public void addOneStore(){
        Store store = new Store();
        firm .addStore(store);
    }

}