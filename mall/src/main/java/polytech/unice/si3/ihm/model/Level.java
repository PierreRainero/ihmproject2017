package polytech.unice.si3.ihm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a floor of a mall. Lists the stores and the places available in this floor.
 *
 * @author Guillaume Casagrande
 */
public class Level {
    /**
     * All the points of interest available at this floor.
     */
    private List<Store> stores;
    private List<Place> places;

    /**
     * Constructs a Level object by initializing the lists.
     */
    Level() {
        this.stores = new ArrayList<Store>();
        this.places = new ArrayList<Place>();
    }

    /**
     * Finds the store corresponding to an user search.
     *
     * @param nameStore The name given by the user.
     * @return The correct name of the store, or "not found" if the store doesn't exist.
     */
    public String lookForStore(String nameStore) {
        for (Store store : stores) {
            if (store.getName().toLowerCase().equals(nameStore)) {
                return store.getName();
            }
        }
        return "not found";
    }

    /**
     * Adds a store or a place to this level.
     */
    void addStore(Store store) {
        stores.add(store);
    }

    void addPlace(Place place){
        places.add(place);
    }

    /**
     * Returns the lists of the points of interest of this level.
     */
    public List<Store> getStores(){
        return stores;
    }

    public List<Place> getPlaces(){
        return places;
    }
}
