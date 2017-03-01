package polytech.unice.si3.ihm.firm.exceptions;

import polytech.unice.si3.ihm.firm.model.commercial.Product;


public class ProductIsNotInTheInventoryException extends Exception {

    private Product product;

    public ProductIsNotInTheInventoryException(Product product) {
        super();
        this.product = product;
    }


    @Override
    public String toString() {
        return "ProductIsNotInTheInventoryException{} : "+product.getName()+" is not in the inventory" ;
    }
}
