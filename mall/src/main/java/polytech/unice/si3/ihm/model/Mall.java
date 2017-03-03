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
                level = new Level();
                data = (JSONArray) root.get(""+i);
                for (Object o : data) {
                    JSONObject info = (JSONObject) o;

                    Store store = new Store((String) info.get("name"), (String) info.get("web"), (String) info.get("category"));
                    store.setPicture((String) info.get("picture"), ((Long) info.get("picX")).intValue(), ((Long) info.get("picY")).intValue(),
                            ((Long) info.get("picWid")).intValue(), ((Long) info.get("picHei")).intValue());
                    store.setRectangle(Color.web((String) info.get("color")),((Long) info.get("recX")).intValue(), ((Long) info.get("recY")).intValue(),
                            ((Long) info.get("recWid")).intValue(), ((Long) info.get("recHei")).intValue());

                    level.addStore(store);
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
