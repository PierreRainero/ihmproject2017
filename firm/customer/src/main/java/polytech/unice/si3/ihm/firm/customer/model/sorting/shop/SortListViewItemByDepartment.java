package polytech.unice.si3.ihm.firm.customer.model.sorting.shop;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;


/**
 * Class to sort stores by department
 */
public class SortListViewItemByDepartment {
    List<Store> stores;


    /**
     * Constructor for the sorting by department method
     * @param stores the list of stores
     */
    public SortListViewItemByDepartment(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Method that sort the stores by their first two digits of the city number
     * @return the list of the sorted stores
     */
    public List<Store> sort(){
        Collections.sort(stores, new Comparator<Store>() {
            @Override
            public int compare(Store o1, Store o2) {
                return o1.getDepartment().compareTo(o2.getDepartment());
            }
        });
        return stores;
    }





}
