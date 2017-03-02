package polytech.unice.si3.ihm.firm.model.sorting;


import polytech.unice.si3.ihm.firm.model.commercial.Store;

import java.util.*;

public class SortListViewItemByCity {

    private List<Store> stores;

    /**
     * Constructor for the sorting by cities method
     * @param stores the list of stores
     */
    public SortListViewItemByCity(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Method that sort the stores by their city name
     * @return the list sorted
     */
    public List<Store> sortByCity(){
        Collections.sort(stores, new Comparator<Store>() {
            public int compare(Store o1, Store o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        return stores;


    }


}
