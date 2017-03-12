package polytech.unice.si3.ihm.firm.customer.model.searching;

public enum ResearchTypes {

    CITY("Rechercher par ville"),
    REGION("Rechercher par région"),
    DEPARTMENT("Rechercher par département"),
    STORENAME("Recherche par nom de magasin"),
    MALLNAME("Rechercher par centre commercial"),
    DEFAULT("Choisir un type de recherche");

    private String value;


    /**
     * Constructor for a research type
     * @param value the value written in the combobox
     */
    ResearchTypes(String value) {
        this.value = value;
    }

    /**
     * Getter for the value of the research type
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
