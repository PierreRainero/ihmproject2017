package polytech.unice.si3.ihm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Store {
    private String name;
    private String webSite;
    private Rectangle rec;
    private ImageView iv;
    private Category category;
    private Color color;

    Store(String name, String webSite, String category) {
        this.name = name;
        this.webSite = webSite;
        this.category = Category.valueOf(category.toUpperCase());
    }

    void setPicture(String imageName, int x, int y, int width, int height) {
        Image image = new Image("/images/"+imageName+".png");
        iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        iv.setX(x);
        iv.setY(y);
    }

    void setRectangle(Color color, int x, int y, int width, int height) {
        rec = new Rectangle(x, y, width, height);
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getWebSite() {
        return webSite;
    }

    public Rectangle getRectangle() {
        rec.setFill(color);
        return rec;
    }

    public ImageView getPicture() {
        return iv;
    }

    public Category getCategory() {
        return category;
    }
}
