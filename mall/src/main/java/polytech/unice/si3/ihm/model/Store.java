package polytech.unice.si3.ihm.model;

import javafx.scene.paint.Color;

public class Store {
    private String name;
    private Color color;
    private String webSitePath;
    private String imageName;
    private Coordinate coord;

    public Store(String name, Color color, String imageName, Coordinate coord){
        this.name = name;
        this.color = color;
        this.imageName = imageName;
        this.coord = coord;
    }

    public void displayStore() {

    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String getWebSitePath() {
        return webSitePath;
    }

    public String getImageName() {
        return imageName;
    }

    public Coordinate getCoord() {
        return coord;
    }

}
