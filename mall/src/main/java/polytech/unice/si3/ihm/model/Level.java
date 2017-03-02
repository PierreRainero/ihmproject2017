package polytech.unice.si3.ihm.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int number;
    private List<Store> stores;
    private List<Place> places;

    public Level(int number){
        this.number = number;
        this.stores = new ArrayList<Store>();
        this.places = new ArrayList<Place>();
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

    public int getNumber() {
        return number;
    }
}
