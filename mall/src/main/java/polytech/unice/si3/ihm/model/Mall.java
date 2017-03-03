package polytech.unice.si3.ihm.model;

import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

public class Mall {
    private Map<Integer,Level> levels;

    public Mall(String name) {
        levels = new HashMap<Integer, Level>();
        buildMallFromJson(name);
    }

    public void addLevel(int number, Level level) {
        levels.put(number,level);
    }

    public Level getLevel(int levelNumber){
        return levels.get(levelNumber);
    }

    public void buildMallFromJson(String name) {
        try {
            JSONObject obj = new JSONObject("/json/"+name+".json");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
