package polytech.unice.si3.ihm.model;

import javafx.scene.paint.Color;

public class Store {
    private String name;
    private Color color;
    private String webSitePath;
    private String imageName;
    private Coordinate position;
    private int width;
    private int height;

    public Store(String name, Color color, String imageName, Coordinate coor, int width, int height){
        this.name = name;
        this.color = color;
        this.imageName = imageName;
        this.position = coor;
        this.width = width;
        this.height = height;
    }

    public void setPicture() {

    }

    public void setRectangle() {

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

    public Coordinate getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
