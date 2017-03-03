package polytech.unice.si3.ihm.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import polytech.unice.si3.ihm.model.Category;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Place;
import polytech.unice.si3.ihm.model.Store;

import java.util.List;

class Drawer {
    private Pane canvas;

    Drawer(Pane pane) {
        this.canvas = pane;
    }

    void displayLevel(Level level) {
        List<Store> stores = level.getStores();
        for (Store store : stores) {
            canvas.getChildren().add(store.getRectangle());
            canvas.getChildren().add(store.getPicture());
        }
    }

    void displayCategory(Level level, Category category) {
        List<Store> stores = level.getStores();
        for (Store store : stores) {
            Rectangle r = store.getRectangle();
            if (store.getCategory().equals(category)) {
                r.setFill(Color.web("0xffff00"));
            }
            else {
                r.setFill(Color.web("0x9f9bb1"));
            }
            canvas.getChildren().add(r);
            canvas.getChildren().add(store.getPicture());
        }
    }

    void highlightStore(Level level, String storeName) {
        List<Store> stores = level.getStores();
        for (Store store : stores) {
            Rectangle r = store.getRectangle();
            if (store.getName().equals(storeName)) {
                r.setFill(Color.web("0xffff00"));
            }
            else {
                r.setFill(Color.web("0x9f9bb1"));
            }
            canvas.getChildren().add(r);
            canvas.getChildren().add(store.getPicture());
        }
    }

    void displayPlace(Level level, Place place) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(place)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
    }
}
