package polytech.unice.si3.ihm.firm.model.sorting.shop;

import org.junit.Test;

import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.common.model.sorting.shop.SortListViewItemByDepartment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortListViewItemByDepartmentTest {


    @Test
    public void sortTwoStoresAlreadySorted(){
        List<Store> stores = new ArrayList<Store>();
        stores.add(new Store("","","Nice","06000","Alpes-Maritimes","","","",""));
        stores.add(new Store("","","Toulon","83000","Var","","","",""));

        SortListViewItemByDepartment sort = new SortListViewItemByDepartment(stores);
        assertEquals(stores, sort.sort());
    }


    @Test
    public void sortTwoStoresNotAlreadySorted() {
        List<Store> stores = new ArrayList<Store>();
        Store store1 = new Store("", "", "Toulon", "83000","Var","", "", "", "");
        Store store2 = new Store("", "", "Nice", "06000","Alpes-Maritimes","", "", "", "");
        stores.add(store1);
        stores.add(store2);

        List<Store> expected = new ArrayList<Store>();
        expected.add(store2);
        expected.add(store1);

        SortListViewItemByDepartment sort = new SortListViewItemByDepartment(stores);
        assertEquals(expected, sort.sort());
    }

}
