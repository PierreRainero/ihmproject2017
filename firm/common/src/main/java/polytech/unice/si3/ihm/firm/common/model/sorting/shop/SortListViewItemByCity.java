package polytech.unice.si3.ihm.firm.common.model.sorting.shop;


import java.util.*;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

/**
 * 
 * Class to sort shops by city
 *
 */
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
    public List<Store> sort(){
        Collections.sort(stores, new Comparator<Store>() {
            @Override
            public int compare(Store o1, Store o2) {
                return o1.getCity().compareTo(o2.getCity());
            }
        });
        return stores;
    }


}
