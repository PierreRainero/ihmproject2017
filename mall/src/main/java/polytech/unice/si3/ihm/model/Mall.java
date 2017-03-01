package polytech.unice.si3.ihm.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Mall {
    private String name;
    private Map<Integer,Level> levels;
    private int numberOfLevel;

    public Mall(String name,int nol){
        this.name=name;
        this.numberOfLevel = nol;
        this.levels = new HashMap<Integer, Level>();
    }

    public Mall(String name,int nol,List<Level> levels){
        this.name=name;
        this.numberOfLevel = nol;
        this.levels = new HashMap<Integer,Level>();
        for(int i = 0; i<levels.size();i++){
            addLevel(i,levels.get(i));
        }
    }

    public void addLevel(int number,Level level){
        numberOfLevel++;
        levels.put(number,level);
    }

    public Level getLevel(int levelNumber){
        return levels.get(levelNumber);
    }

    public int getNumberOfLevel(){
        return this.getNumberOfLevel();
    }

}
