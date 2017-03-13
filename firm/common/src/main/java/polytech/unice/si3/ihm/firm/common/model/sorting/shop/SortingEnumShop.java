package polytech.unice.si3.ihm.firm.common.model.sorting.shop;

/**
 * 
 * Enum class for shops sortings
 *
 */
public enum SortingEnumShop {
    CITY("Trier par villes"),
    REGION("Trier par régions"),
    DEPARTMENT("Trier par départements");

    private String sortingName;

    SortingEnumShop(String sortingName) {
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
    public static SortingEnumShop convertStringToSortingEnum(String selected){
        for (SortingEnumShop sortingEnum : SortingEnumShop.values()){
            if (selected.equals(sortingEnum.getSortingName())) 
            	return sortingEnum;
        }
        return SortingEnumShop.CITY;
    }

}
