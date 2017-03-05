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
            for (int i = 0; i <= 3; i++) {
                level = new Level();
                data = (JSONArray) root.get(""+i);
                for (Object o : data) {
                    JSONObject info = (JSONObject) o;
                    if ("store".equals(info.get("type"))) {
                        level.addStore(createStore(info));
                    }
                    else if ("place".equals(info.get("type"))) {
                        level.addPlace(createPlace(info));
                    }
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

    private Store createStore(JSONObject info) {
        Store store = new Store((String) info.get("name"), (String) info.get("web"), (String) info.get("category"));
        store.setPicture((String) info.get("picture"), ((Long) info.get("picX")).intValue(), ((Long) info.get("picY")).intValue(),
                ((Long) info.get("picWid")).intValue(), ((Long) info.get("picHei")).intValue());
        store.setRectangle(Color.web((String) info.get("color")),((Long) info.get("recX")).intValue(), ((Long) info.get("recY")).intValue(),
                ((Long) info.get("recWid")).intValue(), ((Long) info.get("recHei")).intValue());
        return store;
    }

    private Place createPlace(JSONObject info) {
        Place p = Place.valueOf(((String) info.get("name")).toUpperCase());
        p.createPlace((String) info.get("picture"), ((Long) info.get("posX")).intValue(), ((Long) info.get("posY")).intValue(),
                 ((Long) info.get("width")).intValue(), ((Long) info.get("height")).intValue());
        return p;
    }
}
