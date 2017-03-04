package polytech.unice.si3.ihm.firm.model.sorting.product;


/**
 * 
 * Enum class for products sortings
 *
 */
public enum SortingEnumProduct {
    ALL("Tous les produits"),
    FLAGSHIP("Produits phares"),
    PROMOTED("Promotions");
	
    private String sortingName;

    SortingEnumProduct(String sortingName) {
        this.sortingName = sortingName;
    }

    /**
     * Complete sorting name for the view
     * @return string for the view
     */
    public String getSortingName() {
        return sortingName;
    }
}
