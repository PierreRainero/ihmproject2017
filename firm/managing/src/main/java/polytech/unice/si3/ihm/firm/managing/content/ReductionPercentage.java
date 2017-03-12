package polytech.unice.si3.ihm.firm.managing.content;

import java.util.ArrayList;
import java.util.List;

public class ReductionPercentage {


    private int margin;
    private List<Integer> percentages;


    /**
     * default constructor for the margin set to 10
     */
    public ReductionPercentage() {

        margin = 10;
        percentages = calculatePercentages();
    }

    /**
     * Constructor for the reduction percentages available for the manager
     * @param margin custom margin
     */
    public ReductionPercentage(int margin) {
        this.margin = margin;
        percentages = calculatePercentages();
    }

    /**
     * Method that calculate the percentages depending on the margin
     * @return the list of percentages
     */
    public List<Integer> calculatePercentages(){
        List<Integer> percentages = new ArrayList<>();
        for (Integer i=0; i<=100; i+=margin){
            percentages.add(i);
        }
        return percentages;
    }

    /**
     * Getter for the list of percentages setting
     * @return the list of percentages
     */
    public List<Integer> getPercentages() {
        return percentages;
    }
}
