package polytech.unice.si3.ihm.firm.common.model.sorting.product;

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
    
    /**
     * Method that converts a string in a SortingEnum object if the string is corresponding to one of the objects
     * @param selected the string to convert
     * @return the SortingEnum object corresponding to the string
     */
    public static SortingEnumProduct convertStringToSortingEnum(String selected){
        for (SortingEnumProduct sortingEnum : SortingEnumProduct.values()){
            if (selected.equals(sortingEnum.getSortingName())) 
            	return sortingEnum;
        }
        return SortingEnumProduct.ALL;
    }
}
