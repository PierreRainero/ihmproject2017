package polytech.unice.si3.ihm.firm.common.model.searching;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

public class CityResearch extends GeneralResearch {



    /**
     * Constructor for the general research
     *
     * @param stores the list of stores
     */
    public CityResearch(List<Store> stores) {
        super(stores);
    }



    @Override
    /**
     * {@inheritDoc}
     */
    public List<Store> search(String searchValue){
        List<Store> result = new ArrayList<>();

        for (Store store : getStores())
            if (store.getCity().equalsIgnoreCase(searchValue))
            	result.add(store);

        return result;
    }


}
