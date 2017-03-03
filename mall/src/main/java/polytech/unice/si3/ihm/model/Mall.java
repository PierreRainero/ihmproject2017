package polytech.unice.si3.ihm.model;

import javafx.scene.paint.Color;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Mall {
    private Map<Integer, Level> levels;

    public Mall(String name) {
        levels = new HashMap<Integer, Level>();
        buildMallFromJson(name);
    }

    private void buildMallFromJson(String name) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject root = (JSONObject)parser.parse(new FileReader(getClass().getResource("/json/"+name+".json").getFile()));
            JSONArray data;
            Level level;
            for (int i = -1; i <= 3; i++) {
                level = new Level(i);
                data = (JSONArray) root.get(""+i);
                for (Object o : data) {
                    JSONObject info = (JSONObject) o;
                    level.addStore(new Store((String) info.get("name"), Color.web((String) info.get("color")), (String) info.get("picture"),
                            new Coordinate((((Long) info.get("posX")).intValue()), ((Long) info.get("posY")).intValue()),
                            ((Long) info.get("width")).intValue(), ((Long) info.get("height")).intValue()));
                }
                levels.put(i, level);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Level getLevel(int floor) {
        return levels.get(floor);
    }
}
