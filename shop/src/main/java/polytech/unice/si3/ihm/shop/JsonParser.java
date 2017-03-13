package polytech.unice.si3.ihm.shop;

import org.json.JSONArray;
import org.json.JSONObject;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.model.SuperType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private JSONObject input;

    /**
     * Constructeur de JsonParser
     * @param filePath url vers le fichier json utilisé pour créer le magasin
     * @throws Exception
     */
    public JsonParser(String filePath) throws Exception {
        this(new File(filePath));
    }

    /**
     * Constructeur de JsonParser
     * @param file fichier json utilisé pour créer le magasin
     * @throws Exception
     */
    public JsonParser(File file) throws Exception {
        String jsonString = "";
        try{
            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            while ((line=br.readLine())!=null){
                jsonString+=line+"\n";
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        input = new JSONObject(jsonString);
    }

    /**
     * Lance le "parsage" : création en fonction du fichier json en paramètre de la fênetre, du magasin et des
     * différents produits
     * @throws Exception
     */
    public Shop parseJson() throws Exception {
        Shop shop = new Shop(input.get("name").toString(), input.get("logo").toString(), input.get("logoMin").toString(), input.get("logoText").toString());
        shop.setAbout(input.get("about").toString());
        shop.setLegalNotice(input.get("legalNotice").toString());
        shop.setAdress(input.get("adress").toString());
        shop.setPhone(input.get("phone").toString());
        return shop;
    }

    /**
     * Créé les objets, et les ajoute dans le magasin (en fonction du fichier json en entrée)
     * @param shop magasin que l'on gère
     */
    public void createObjects(Shop shop) {
        JSONArray jsonArray = input.getJSONArray("itemsList");
        JSONObject jsonObject;

        SuperType superType;

        List<SuperType> itemTypeList;

        for(int i=0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            JSONObject productTypes = jsonObject.getJSONObject("types");
            itemTypeList = new ArrayList<>();

            for(int j=0;j<productTypes.names().length();j++){
                String currentProductType = productTypes.names().get(j).toString();
                superType = new SuperType(currentProductType);
                for(int k=0;k<productTypes.getJSONArray(superType.getName()).length();k++){
                    if(!superType.getTypes().contains(productTypes.getJSONArray(superType.getName()).get(k).toString()))
                        superType.addType(productTypes.getJSONArray(superType.getName()).get(k).toString());
                }
                itemTypeList.add(superType);
            }
            shop.addProduct(new Product(jsonObject.getString("name"), jsonObject.getString("imageURL"), jsonObject.getDouble("price"), jsonObject.getString("description"), itemTypeList, jsonObject.getInt("sales")));
        }
    }
}
