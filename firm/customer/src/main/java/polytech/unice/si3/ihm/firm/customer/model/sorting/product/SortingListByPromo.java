package polytech.unice.si3.ihm.firm.customer.model.sorting.product;

import java.util.ArrayList;
import java.util.List;

import polytech.unice.si3.ihm.firm.customer.model.commercial.Product;

/**
 * 
 * Class to sort shops by flagship state
 *
 */
public class SortingListByPromo {
    private List<Product> products;
    
    /**
     * Constructor for the sorting by promo
     * @param products the list of products
     */
    public SortingListByPromo(List<Product> products) {
        this.products = products;
    }
    
    /**
     * Method that sort the products and keep only promoted products
     * @return the sorted list
     */
    public List<Product> sort(){
    	List<Product> sortList = new ArrayList<>();
    	
    	for(Product product : products)
    		if(product.isPromoted())
    			sortList.add(product);
    	
        return sortList;
    }
    
}
