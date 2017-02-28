package polytech.unice.si3.ihm.model;
import java.awt.Color;
/**
 * Created by Ken on 28/02/2017.
 */
public class Store {
    private String name;
    private Color color;
    private String webSitePath;
    private String imagePath;
    private Coordinate coord;

    public Store(String name, Color color, String imagePath, Coordinate coord){
        this.name = name;
        this.color = color;
        this.imagePath = imagePath;
        this.coord = coord;
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

    public String getImagePath() {
        return imagePath;
    }

    public Coordinate getCoord() {
        return coord;
    }

}
