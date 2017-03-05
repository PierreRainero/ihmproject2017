package polytech.unice.si3.ihm.firm.model.searching;

import polytech.unice.si3.ihm.firm.model.commercial.Store;

import java.util.ArrayList;
import java.util.List;

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

        for (Store store : stores){

            if (store.getCity().equals(searchValue)) result.add(store);
            else if (store.getDepartment().equals(searchValue)) result.add(store);
            else if (store.getRegion().equals(searchValue)) result.add(store);
            else if(store.getName().equals(searchValue)) result.add(store);
            else if (store.getMallName().equals(searchValue)) result.add(store);

        }
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
