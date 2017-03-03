package polytech.unice.si3.ihm.firm.model.sorting;

/**
 * 
 * Enum class for sorting
 *
 */
public enum SortingEnum {
    CITY("Trier par villes"),
    REGION("Trier par régions"),
    DEPARTMENT("Trier par départements");

    private String sortingName;

    SortingEnum(String sortingName) {
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
