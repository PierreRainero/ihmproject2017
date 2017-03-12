package polytech.unice.si3.ihm.firm.model.sorting.shop;

import org.junit.Test;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.customer.model.sorting.shop.SortListViewItemByRegion;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortListViewItemByRegionTest {

    @Test
    public void sortTwoStoresAlreadySorted() {
        List<Store> stores = new ArrayList<Store>();
        stores.add(new Store("", "", "Paris", "75004", "", "Ile de France", "", "", ""));
        stores.add(new Store("", "", "Nice", "06000", "", "Provence-Alpes Cote d'Azur", "", "", ""));

        SortListViewItemByRegion sort = new SortListViewItemByRegion(stores);
        assertEquals(stores, sort.sort());
    }


    @Test
    public void sortTwoStoresNotAlreadySorted() {
        List<Store> stores = new ArrayList<Store>();
        Store store1 = new Store("", "", "Nice", "06000", "", "Provence-Alpes Cote d'Azur", "", "", "");
        Store store2 = new Store("", "", "Paris", "75004", "", "Ile de France", "", "", "");
        stores.add(store1);
        stores.add(store2);

        List<Store> expected = new ArrayList<Store>();
        expected.add(store2);
        expected.add(store1);

        SortListViewItemByRegion sort = new SortListViewItemByRegion(stores);
        assertEquals(expected, sort.sort());
    }

}
