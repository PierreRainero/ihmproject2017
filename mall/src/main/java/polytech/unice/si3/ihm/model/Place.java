package polytech.unice.si3.ihm.model;

public class Place {
    private String name;
    private String imagePath;
    private int level;
    private Coordinate coord;

    public Place(String name,String imagePath,int level,Coordinate coord){
        this.name = name;
        this.imagePath = imagePath;
        this.level = level;
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getLevel() {
        return level;
    }

    public Coordinate getCoord() {
        return coord;
    }
}
