package polytech.unice.si3.ihm.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import polytech.unice.si3.ihm.model.Category;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Place;
import polytech.unice.si3.ihm.model.Store;

import java.util.List;

/**
 * Represents a floor by drawing the figures and the pictures.
 *
 * @author Guillaume Casagrande
 */
public class Drawer {
    /**
     * Canvas containing the drawings.
     */
    private Pane canvas;

    /**
     * Builds a Drawer class thanks to the pane of the scene.
     */
    public Drawer(Pane pane) {
        this.canvas = pane;
    }

    /**
     * Displays a level with the parameters by default.
     *
     * @param level The level to represent.
     */
    public void displayLevel(Level level) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
        List<Store> stores = level.getStores();
        for (Store store : stores) {
            canvas.getChildren().add(store.getRectangle());
            canvas.getChildren().add(store.getPicture());
        }
    }

    /**
     * Displays a level putting forward the stores of a category.
     * The stores of the category become yellow, and the others, grey.
     *
     * @param level The level to represent.
     * @param category The category chosen by the user.
     */
    public void displayCategory(Level level, Category category) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
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

    /**
     * Displays a level putting forward one store.
     * The concerned store become yellow, and the others, grey.
     *
     * @param level The level to represent.
     * @param storeName The store chosen by the user.
     */
    public void highlightStore(Level level, String storeName) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
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

    /**
     * Displays a place of a level.
     *
     * @param level The current level.
     * @param place The place to represent (usually a "highlight" place).
     */
    public void displayPlace(Level level, Place place) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(place)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
    }
}
