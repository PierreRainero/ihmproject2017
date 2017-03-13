package polytech.unice.si3.ihm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Stocks the identity and the representation of a shop.
 *
 * @author Guillaume Casagrande
 */
public class Store {
    private String name;
    private String webSite;
    private Rectangle rec;
    private ImageView iv;
    private Category category;
    private Color color;

    /**
     * Constructs a Store object.
     *
     * @param name The name of the shop.
     * @param webSite The address of the shop on the internet.
     * @param category The product type sold by the shop.
     */
    Store(String name, String webSite, String category) {
        this.name = name;
        this.webSite = webSite;
        this.category = Category.valueOf(category.toUpperCase());
    }

    /**
     * Constructs the javafx picture representing this shop.
     *
     * @param imageName The name of the png file.
     * @param x The position on the X axis of the upper-left corner.
     * @param y The position on the Y axis of the upper-left corner.
     * @param width The width of the picture.
     * @param height The height of the picture.
     */
    void setPicture(String imageName, int x, int y, int width, int height) {
        Image image = new Image("/images/"+imageName+".png");
        iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        iv.setX(x);
        iv.setY(y);
    }

    /**
     * Constructs the javafx rectangle representing this shop.
     *
     * @param color The color of the rectangle
     * @param x The position on the X axis of the upper-left corner.
     * @param y The position on the Y axis of the upper-left corner.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    void setRectangle(Color color, int x, int y, int width, int height) {
        rec = new Rectangle(x, y, width, height);
        rec.setStroke(Color.BLACK);
        this.color = color;
    }

    /**
     * Returns some data of this shop.
     */
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
