package polytech.unice.si3.ihm.model;

import polytech.unice.si3.ihm.shop.JsonParser;

/**
 * Created by Ken on 07/03/2017.
 */
public class Info {
    private String jsPath;
    private JsonParser jsp;

    public Info(String jsPath){
        this.jsPath = jsPath;
    }

    public void setJsp(JsonParser jsp){
        this.jsp = jsp;
    }
}
