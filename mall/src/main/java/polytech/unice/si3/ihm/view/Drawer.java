package polytech.unice.si3.ihm.view;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import polytech.unice.si3.ihm.MainApp;
import polytech.unice.si3.ihm.controller.InfoController;
import polytech.unice.si3.ihm.model.Category;
import polytech.unice.si3.ihm.model.Level;
import polytech.unice.si3.ihm.model.Place;
import polytech.unice.si3.ihm.model.Store;

import java.io.IOException;
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
    public MainApp mainApp;

    /**
     * Builds a Drawer class thanks to the pane of the scene.
     */
    public Drawer(Pane pane,MainApp mainApp) {
        this.canvas = pane;
        this.mainApp = mainApp;
    }


    /**
     * Displays a level with the parameters by default.
     *
     * @param level The level to represent.
     */
    public void displayLevel(Level level) {
        List<Place> places = level.getPlaces();
        for (Place p : places) {
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE) || p.equals(Place.TERRACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR) || p.equals(Place.GROUNDFLOOR)
                    || p.equals(Place.ENTRANCENORTH) || p.equals(Place.ENTRANCESOUTH) || p.equals(Place.ENTRANCEWEST)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
        List<Store> stores = level.getStores();
        for (final Store store : stores) {
            store.getPicture().setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent t) {
                    FXMLLoader loader = new FXMLLoader();
                    Parent node = null;
                    try {
                        node = loader.load(getClass().getResourceAsStream("/fxml/info.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(node, 1280, 720);
                    mainApp.getStage().setScene(scene);

                    InfoController controller = loader.getController();
                    controller.setMainApp(mainApp);
                    controller.initData(store.getInfo());
                    mainApp.getStage().show();
                }
            });
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
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE) || p.equals(Place.TERRACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR) || p.equals(Place.GROUNDFLOOR)
                    || p.equals(Place.ENTRANCENORTH) || p.equals(Place.ENTRANCESOUTH) || p.equals(Place.ENTRANCEWEST)) {
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
            if (p.equals(Place.GROUND) || p.equals(Place.ELEVATOR) || p.equals(Place.RELAXINGSPACE) || p.equals(Place.TERRACE)
                    || p.equals(Place.STAIRS) || p.equals(Place.TOILET) || p.equals(Place.RELAXINGSPACEFLOOR) || p.equals(Place.GROUNDFLOOR)
                    || p.equals(Place.ENTRANCENORTH) || p.equals(Place.ENTRANCESOUTH) || p.equals(Place.ENTRANCEWEST)) {
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
        if (place.equals(Place.WIFI)) {
            for (Place p : places) {
                if (p.equals(Place.WIFI0) || p.equals(Place.WIFI1) || p.equals(Place.WIFI2) || p.equals(Place.WIFI3)) {
                    canvas.getChildren().add(p.getPicture());
                }
            }
            return;
        }
        for (Place p : places) {
            if (p.equals(place)) {
                canvas.getChildren().add(p.getPicture());
            }
        }
    }
}
