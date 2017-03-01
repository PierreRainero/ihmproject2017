package polytech.unice.si3.ihm.model;

import java.util.List;

public class Level {
    private int number;
    private List<Store> stores;
    private List<Place> places;

    public Level(int number){
        this.number = number;
    }

    public Level(int number,List<Store> stores, List<Place> places){
        this.number = number;
        this.stores = stores;
        this.places = places;
    }

    public void addStore(Store store){
        stores.add(store);
    }

    public void addPlace(Place place){
        places.add(place);
    }

    public void displayStores() {
        for (Store store : stores) {
            store.displayStore();
        }
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
