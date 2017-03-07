package polytech.unice.si3.ihm.firm.customer.model.searching;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.customer.model.commercial.Store;

public class MallNameResearch extends GeneralResearch {
    /**
     * Constructor for the general research
     *
     * @param stores the list of stores
     */
    public MallNameResearch(List<Store> stores) {
        super(stores);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public List<Store> search(String searchValue){
        List<Store> result = new ArrayList<>();

        for (Store store: getStores())
            if (store.getMallName().toLowerCase().equals(searchValue.toLowerCase()))
            	result.add(store);

        return result;
    }
}
