package polytech.unice.si3.ihm.shop.model;

import java.util.ArrayList;
import java.util.List;

public class SuperType {
    String name;
    List<String> types;

    public SuperType (String name){
        this.name = name;
        this.types = new ArrayList<>();
    }

    public void addType(String type){
        this.types.add(type);
    }

    public List<String> getTypes(){
        return types;
    }

    public String getName(){
        return this.name;
    }
}
