package polytech.unice.si3.ihm.shop.model;

import org.junit.Before;
import org.junit.Test;

import javax.sql.rowset.spi.SyncResolver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductTest {
    Product p;
    String livre;
    String image;
    String description;
    String type0;
    String type1;

    @Before
    public void init(){
        livre = "livre";
        image = "@/images/totoimg.png";
        description = "Coucou je suis le livre.";
        type0 = "jeu vidéo";
        type1 = "FPS";

        List<String> types = new ArrayList<>();
        types.add(type0);
        types.add(type1);

        SuperType st = new SuperType("Console");
        st.addType("something");

        List<SuperType> spl = new ArrayList<>();

        spl.add(st);
        p = new Product(livre, image, 15, description, spl, 1 ,new Promotion(0));
    }

    @Test
    public void productTest(){
        Promotion promo = new Promotion(50);
        assertEquals(livre, p.getName());
        assertEquals(image, p.getImageURL());
        assertEquals(15.0, p.getPrice(), 0.01);
        assertEquals(15.0, p.getPromotedPrice(), 0.01);
        assertEquals(0, p.getPromotion(), 0.01);
        p.setPromotion(promo);
        assertEquals(50.0, p.getPromotion(), 0.01);
        assertEquals(7.5, p.getPromotedPrice(), 0.01);
        assertEquals(description, p.getDescription());
        assertEquals(1, p.getSales());
    }

    @Test
    public void productTypesTest() {
        List<SuperType> mySuperType = new ArrayList<>();
        SuperType myType = new SuperType("My Type Name");
        mySuperType.add(myType);
        myType.addType("Poisson");
        myType.addType("Légumes");
        myType.addType("Fruits");
        p.setProductTypes(mySuperType);
        assertEquals(p.getProductType(), mySuperType);
        assertEquals(p.getProductType().get(0).getName(), "My Type Name");
    }
}

