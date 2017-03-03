package polytech.unice.si3.ihm.model;

public class Place {
    private String name;
    private String imagePath;
    private int level;

    public Place(String name,String imagePath,int level){
        this.name = name;
        this.imagePath = imagePath;
        this.level = level;
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
}
