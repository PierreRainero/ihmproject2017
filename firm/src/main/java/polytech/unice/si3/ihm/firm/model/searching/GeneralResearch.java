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
        String comparisonValue = searchValue.toLowerCase();

        List<Store> result = new ArrayList<>();

        for (Store store : stores)
            if (store.getCity().toLowerCase().equals(comparisonValue) ||
                    store.getDepartment().toLowerCase().equals(comparisonValue) ||
                    store.getRegion().toLowerCase().equals(comparisonValue) ||
                    store.getName().toLowerCase().equals(comparisonValue) ||
                    store.getMallName().toLowerCase().equals(comparisonValue))
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
