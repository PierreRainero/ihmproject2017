package polytech.unice.si3.ihm.shop.model;

public class Promotion {
    private double percent;

    public Promotion(double value){
        this.percent=value/100;
    }

    public double getPercent(){
        return this.percent;
    }

}
