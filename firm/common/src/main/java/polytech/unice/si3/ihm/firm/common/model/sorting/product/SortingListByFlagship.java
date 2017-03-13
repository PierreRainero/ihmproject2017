package polytech.unice.si3.ihm.firm.common.model.sorting.product;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.common.model.commercial.Product;

/**
 * 
 * Class to sort shops by flagship state
 *
 */
public class SortingListByFlagship {
    private List<Product> products;
    
    /**
     * Constructor for the sorting by flagship
     */
    public SortingListByFlagship(List<Product> products) {
        this.products = products;
    }
    
    /**
     * Method that sort the products and keep only flagship products
     * @return the sorted list
     */
    public List<Product> sort(){
    	List<Product> sortList = new ArrayList<>();
    	
    	for(Product product : products)
    		if(product.isFlagship())
    			sortList.add(product);
    	
        return sortList;
    }
    
}
