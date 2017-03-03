package polytech.unice.si3.ihm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Place {
    WIFI, TOILET, ELEVATOR, STAIRS, WHEREAMI;

    private ImageView iv;

    public void createPlace(String imageName, int x, int y, int width, int height) {
        Image image = new Image("/images/"+imageName+".png");
        iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(width);
        iv.setFitHeight(height);
        iv.setX(x);
        iv.setY(y);
    }

    public ImageView getPicture() {
        return iv;
    }
}
