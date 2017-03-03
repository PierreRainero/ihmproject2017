package polytech.unice.si3.ihm.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Store> stores;
    private List<Place> places;

    Level(){
        this.stores = new ArrayList<Store>();
        this.places = new ArrayList<Place>();
    }

    public String lookForStore(String nameStore) {
        for (Store store : stores) {
            if (store.getName().toLowerCase().equals(nameStore)) {
                return store.getName();
            }
        }
        return "not found";
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public void addPlace(Place place){
        places.add(place);
    }

    public List<Store> getStores(){
        return stores;
    }

    public List<Place> getPlaces(){
        return places;
    }
}
