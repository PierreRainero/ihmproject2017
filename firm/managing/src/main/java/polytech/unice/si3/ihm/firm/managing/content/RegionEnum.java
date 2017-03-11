package polytech.unice.si3.ihm.firm.managing.content;

/**
 * 
 * Enum class for regions
 *
 */
public enum RegionEnum {
    AUVERGNE_RHONE_ALPES("Auvergne-Rhône-Alpes"),
    BOURGOGNE_FRANCHE_COMTE("Bourgogne-Franche-Comté"),
    BRETAGNE("Bretagne"),
    CENTRE_VAL_DE_LOIRE("Centre-Val de Loire"),
    CORSE("Corse"),
    GRAND_EST("Grand Est"),
    HAUT_DE_FRANCE("Hauts-de-France"),
    ILE_DE_FRANCE("Île-de-France"),
    NORMANDIE("Normandie"),
    NOUVELLE_AQUITAINE("Nouvelle-Aquitaine"),
    OCCITANIE("Occitanie"),
    PAYS_DE_LOIRE("Pays de la Loire"),
    PROVENCE_ALPES_COTE_AZUR("Provence-Alpes-Côte d'Azur");

    private String regionName;

    RegionEnum(String regionName) {
        this.regionName = regionName;
    }

    /**
     * Complete region name for the view
     * @return string for the view
     */
    public String getRegionName() {
        return regionName;
    }

    
    /**
     * Method that converts a string in a RegionEnum object if the string is corresponding to one of the objects
     * @param selected the string to convert
     * @return the RegionEnum object corresponding to the string
     */
    public static RegionEnum convertStringToSortingEnum(String selected){
        for (RegionEnum name : RegionEnum.values()){
            if (selected.equals(name.getRegionName())) 
            	return name;
        }
        return RegionEnum.AUVERGNE_RHONE_ALPES;
    }

}
