package polytech.unice.si3.ihm.model;

/**
 * Created by Ken on 04/03/2017.
 */
public class GoodDeal {
    private String imagePath;
    private Info infoStore;

    public GoodDeal(String imagePath,String info) {
        this.imagePath = imagePath;
        this.infoStore = new Info(info);
    }

    public String getImagePath(){
        return this.imagePath;
    }

    public Info getInfoStore(){
        return this.infoStore;
    }
}
