package polytech.unice.si3.ihm.firm.common.model.searching;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

public class GeneralResearch {

    private List<Store> stores = new ArrayList<>();

    /**
     * Constructor for the general research
     * @param stores the list of stores
     */
    public GeneralResearch(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Method that is used to search a specific store
     * @param searchValue the search value
     * @return the list of store corresponding to the research
     */
    public List<Store> search(String searchValue){
        List<Store> result = new ArrayList<>();

        for (Store store : stores)
            if (store.getCity().equalsIgnoreCase(searchValue) ||
                    store.getDepartment().equalsIgnoreCase(searchValue) ||
                    store.getRegion().equalsIgnoreCase(searchValue) ||
                    store.getName().equalsIgnoreCase(searchValue) ||
                    store.getMallName().equalsIgnoreCase(searchValue))
            	result.add(store);

        return result;

    }

    /**
     * Getter for the list of stores
     * @return the list of stores
     */
    public List<Store> getStores() {
        return stores;
    }
}
