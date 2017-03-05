package polytech.unice.si3.ihm.model;

import org.json.simple.parser.JSONParser;
//import polytech.unice.si3.ihm.shop.*;
/**
 * Created by Ken on 04/03/2017.
 */
public class GoodDeal {
    private String imagePath;
    private JSONParser json;

    public GoodDeal(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return this.imagePath;
    }
}
