package polytech.unice.si3.ihm.firm.model.sorting;

public enum SortingEnum {




    CITY("Trier par villes"),
    REGION("Trier par régions"),
    DEPARTMENT("Trier par départements");


    private String sortingName;

    SortingEnum(String sortingName) {
        this.sortingName = sortingName;
    }

    public String getSortingName() {
        return sortingName;
    }
}
