package polytech.unice.si3.ihm.firm.model.sorting.shop;

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


}
