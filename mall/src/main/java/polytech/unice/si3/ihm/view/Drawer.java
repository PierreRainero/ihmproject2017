package polytech.unice.si3.ihm.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Store;

import java.util.List;

public class Drawer {
    private GridPane grid;

    public Drawer(GridPane gp) {
        this.grid = gp;
    }

    public void displayLevel(Level level) {
        List<Store> stores = level.getStores();
        for (Store store : stores) {
            Rectangle r = new Rectangle(store.getWidth(), store.getHeight());
            grid.add(r, store.getPosition().getX(), store.getPosition().getY());
            Image image = new Image("/images/"+store.getImageName()+".png");
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            iv1.setFitWidth(74);
            iv1.setFitHeight(72);
            grid.add(iv1, 12, 4);
        }
    }
}
