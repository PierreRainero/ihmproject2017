package polytech.unice.si3.ihm.firm.common.model.sorting.shop;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

/**
 * Class to sort the stores by region
 */
public class SortListViewItemByRegion {
    private List<Store> stores;

    /**
     * Constructor for the sorting by region method
     * @param stores the list of stores
     */
    public SortListViewItemByRegion(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Method that sort the stores by their region name
     * @return the sorted list of stores
     */
    public List<Store> sort(){
        Collections.sort(stores, new Comparator<Store>() {
            @Override
            public int compare(Store o1, Store o2) {
                return o1.getRegion().compareTo(o2.getRegion());
            }
        });
        return stores;
    }


}
