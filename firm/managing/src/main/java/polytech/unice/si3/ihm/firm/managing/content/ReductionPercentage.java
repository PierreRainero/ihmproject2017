package polytech.unice.si3.ihm.firm.managing.content;



public class ReductionPercentage {





    /**
     * Constructor for the reduction percentages available for the manager
     */
    public ReductionPercentage() {

    }

    /**
     * Method that calculate the price depending on the percentage of the reduction
     * @return the new price
     */
    public int calculatePrice(int price, int percentage){
        return price - (price*percentage)/100;
    }


}
