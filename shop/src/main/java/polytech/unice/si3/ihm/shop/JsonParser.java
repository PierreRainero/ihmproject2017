package polytech.unice.si3.ihm.shop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import polytech.unice.si3.ihm.shop.model.Product;
import polytech.unice.si3.ihm.shop.model.Shop;
import polytech.unice.si3.ihm.shop.view.MainViewController;
import polytech.unice.si3.ihm.shop.view.ProductViewController;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private JSONObject input;
    private Stage stage;

    /**
     * Constructeur de JsonParser
     * @param filePath url vers le fichier json utilisé pour créer le magasin
     * @param stage stage général
     * @throws Exception
     */
    public JsonParser(String filePath, Stage stage) throws Exception {
        File file = new File(filePath);
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
        this.stage = stage;
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
        this.stage = stage;
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
     * Permet d'afficher la popup correspondant à un produit
     * @param product produit dont on veut afficher la popup
     * @throws IOException
     */
    public void productPopup(Product product) throws IOException {
        String fxmlFile = "/fxml/product_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(400);
        stage.setMinWidth(500);

        Scene scene = new Scene(rootNode, 500,600);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle(product.getName());
        stage.setScene(scene);

        ProductViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(product);

    }

    /**
     * Créé les objets, et les ajoute dans le magasin (en fonction du fichier json en entrée)
     * @param shop magasin que l'on gère
     */
    public void createObjects(Shop shop) {
        JSONArray jsonArray = input.getJSONArray("itemsList");
        JSONObject jsonObject;
        for(int i=0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            JSONArray productTypes = jsonObject.getJSONArray("types");
            List<String> types = new ArrayList<>();

            for(int j=0;j<productTypes.length();j++){
                types.add(productTypes.get(j).toString());
            }
            shop.addProduct(new Product(jsonObject.getString("name"), jsonObject.getString("imageURL"), jsonObject.getDouble("price"), jsonObject.getString("description"), types, jsonObject.getInt("sales")));
        }
    }

    public JSONArray getItemsList(){
        return input.getJSONArray("itemsList");
    }
}
