package polytech.unice.si3.ihm.firm.customer.model.searching;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.customer.model.commercial.Store;

public class RegionResearch extends GeneralResearch {
    /**
     * Constructor for the general research
     *
     * @param stores the list of stores
     */
    public RegionResearch(List<Store> stores) {
        super(stores);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public List<Store> search(String searchValue){
        List<Store> result = new ArrayList<>();

        for (Store store: getStores())
            if (store.getRegion().toLowerCase().equals(searchValue.toLowerCase()))
            	result.add(store);

        return result;
    }
}
