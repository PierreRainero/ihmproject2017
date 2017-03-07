package polytech.unice.si3.ihm.firm.model.searching;

import polytech.unice.si3.ihm.firm.model.commercial.Store;

import java.util.ArrayList;
import java.util.List;

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
