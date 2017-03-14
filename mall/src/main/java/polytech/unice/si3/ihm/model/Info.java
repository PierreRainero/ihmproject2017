package polytech.unice.si3.ihm.model;

import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import polytech.unice.si3.ihm.shop.JsonParser;

import java.io.FileReader;

/**
 * Created by Ken on 07/03/2017.
 */
public class Info {
    private String image;
    private String name;
    private String description;
    private String type;
    private String site;
    private String jsp;

    public Info(String jsPath){
        JSONParser parser = new JSONParser();
        try {
            JSONObject js = (JSONObject)parser.parse(new FileReader(getClass().getResource(jsPath).getFile()));
            this.image = "/images/" + js.get("image");
            this.name = (String)js.get("name");
            this.description = (String)js.get("description");
            this.type = (String)js.get("type");
            this.site = (String)js.get("site");
            if(js.get("storePath")!=null){
                this.jsp = (String)js.get("storePath");
            }
            else jsp = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsp(){
        return jsp;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSite() {
        return site;
    }
}
