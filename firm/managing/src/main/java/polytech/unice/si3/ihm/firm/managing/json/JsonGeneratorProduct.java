package polytech.unice.si3.ihm.firm.managing.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import polytech.unice.si3.ihm.firm.common.json.JsonReceiver;
import polytech.unice.si3.ihm.firm.common.model.commercial.Product;
import polytech.unice.si3.ihm.firm.common.util.Log;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class JsonGeneratorProduct {
    private static final String PATH = System.getProperties().get("user.dir")+"/../datas/content.json";

    private Product product;
    public JsonGeneratorProduct(Product product){
        Optional.empty();
        this.product= product;
    }

	@SuppressWarnings("unchecked")
    public void generate() throws IOException, ParseException {
        JSONObject firmJson = JsonReceiver.getJsonFirm(PATH);
        JSONArray products = (JSONArray)firmJson.get("products");
        products.add(getProductJson());

        File file = new File(PATH);
        if (!file.exists())
            file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(firmJson.toString());
        writer.flush();
        writer.close();

        Log.info(this.getClass(), "JSON modifie");
    }

	@SuppressWarnings("unchecked")
    private JSONObject getProductJson(){
        JSONObject returnValue = new JSONObject();

        returnValue.put("name", product.getName());
        returnValue.put("image", product.getImage());
        returnValue.put("reference", product.getReference());
        returnValue.put("description", product.getDescription());
        returnValue.put("price", product.getPrice());
        returnValue.put("promoted", product.isPromoted());
        returnValue.put("flagship", product.isFlagship());

        return returnValue;
    }



}
