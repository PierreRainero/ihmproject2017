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

import java.io.*;

public class JsonParser {
    private JSONObject input;
    private Stage stage;

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

    public void parseJson() throws Exception {

        Shop shop = new Shop(input.get("name").toString(), input.get("logo").toString());

        String fxmlFile = "/fxml/main_view.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setMinHeight(700.0);
        stage.setMinWidth(1140.0);

        Scene scene = new Scene(rootNode, 1280, 720);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle(shop.getName());
        stage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setCurrentStage(stage);
        controller.initialiseView(shop);

        createObjects(shop);
    }

    private void createObjects(Shop shop) {
        JSONArray jsonArray = input.getJSONArray("itemsList");
        JSONObject jsonObject;
        for(int i=0;i<jsonArray.length();i++){
            jsonObject = jsonArray.getJSONObject(i);
            shop.addProduct(new Product(jsonObject.getString("name"), jsonObject.getString("imageURL"), jsonObject.getDouble("price")));
        }
    }
}
