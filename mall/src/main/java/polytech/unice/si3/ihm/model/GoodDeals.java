package polytech.unice.si3.ihm.model;

import org.json.simple.parser.JSONParser;
//import polytech.unice.si3.ihm.shop.*;
/**
 * Created by Ken on 04/03/2017.
 */
public class GoodDeals {
    private String imagePath;
    private JSONParser json;

    public GoodDeals(String imagePath){
        this.imagePath = imagePath;
    }
}
