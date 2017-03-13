package polytech.unice.si3.ihm.firm.managing.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import polytech.unice.si3.ihm.firm.common.json.JsonReceiver;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

import java.io.IOException;

public class JsonStoreEditor {
    private static final String PATH = System.getProperties().get("user.dir")+"/../customer/src/main/resources/datas/content.json";

    Store store;

    public JsonStoreEditor(Store store) throws IOException, ParseException {
        this.store = store;
    }

    public void remove() throws IOException, ParseException {
        JSONObject firmJson = JsonReceiver.getJsonFirm(PATH);
        JSONArray shops = (JSONArray)firmJson.get("shops");
        for (int i=0; i<shops.size(); i++){
            JSONObject shop = (JSONObject) shops.get(i);
            shop.remove(store.getName());
            shop.remove(store.getAddress());
            shop.remove(store.getCity());
            shop.remove(store.getCityNumber());
            shop.remove(store.getMallName());
            shop.remove(store.getDescription());
            shop.remove(store.getImage());
            shop.remove(store.getRegion());
            shop.remove(store.getDepartment());
        }

    }
}
