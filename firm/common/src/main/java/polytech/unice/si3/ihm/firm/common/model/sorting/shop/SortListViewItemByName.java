package polytech.unice.si3.ihm.firm.common.model.sorting.shop;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListViewItemByName {

    List<Store> stores;

    public SortListViewItemByName(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> sort(){
        Collections.sort(stores, new Comparator<Store>() {
            @Override
            public int compare(Store o1, Store o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return stores;
    }
}
