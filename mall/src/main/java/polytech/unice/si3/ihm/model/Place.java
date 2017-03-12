package polytech.unice.si3.ihm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents all the types of places (except stores) available in a store.
 *
 * @author Guillaume Casagrande
 */
public enum Place {
    WIFI, TOILET, ELEVATOR, STAIRS, WHEREAMI, GROUND, RELAXINGSPACE, RELAXINGSPACEFLOOR, ELEVATORLIGHT, STAIRSLIGHT, TOILETLIGHT;

    /**
     * Representation of the place.
     */
    private ImageView iv;

    /**
     * Constructs the javafx picture representing this place.
     *
     * @param imageName The name of the png file.
     * @param x The position on the X axis of the upper-left corner.
     * @param y The position on the Y axis of the upper-left corner.
     * @param width The width of the picture.
     * @param height The height of the picture.
     */
    public void createPlace(String imageName, int x, int y, int width, int height) {
        Image image = new Image("/images/"+imageName+".png");
        iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        iv.setX(x);
        iv.setY(y);
    }

    /**
     * Returns the picture to be printed in the canvas.
     */
    public ImageView getPicture() {
        return iv;
    }
}
