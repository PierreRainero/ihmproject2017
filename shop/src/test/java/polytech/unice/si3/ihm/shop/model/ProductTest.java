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
    String type;

    @Before
    public void init(){
        livre = "livre";
        image = "@/images/totoimg.png";
        description = "Coucou je suis le livre.";
        type = "jeu vidéo";

        List<String> types = new ArrayList<>();
        types.add(type);

        p = new Product(livre, image, 15, description, types);
    }

    @Test
    public void productTest(){
        Promotion promo = new Promotion(50);
        assertEquals(livre, p.getName());
        assertEquals(image, p.getImageURL());
        assertEquals(15.0, p.getPrice(), 0.01);
        p.setPromotion(promo);
        assertEquals(7.5, p.getPromotedPrice(), 0.01);
        String disquedur = "disque dur";
        /*List<String> list = new ArrayList<String>();
        list.add(disquedur);
        p.addObjectType(disquedur);
        assertEquals(list, p.getObjectTypes());*/
    }


}

