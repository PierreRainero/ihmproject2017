package polytech.unice.si3.ihm.firm.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import polytech.unice.si3.ihm.firm.model.Firm;
import polytech.unice.si3.ihm.firm.model.Store;

public class ContentParser {
	
	private ContentParser(){
		
	}
	
	public static Firm getFirm(){
    	JSONParser parser = new JSONParser();
    	JSONObject firmJson = null;
    	try {
			Object obj = parser.parse(new FileReader(System.getProperties().get("user.dir")+"/src/main/resources/datas/content.json"));
	    	firmJson = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	Firm firm = new Firm((String)firmJson.get("name"), (String)firmJson.get("description"), (String)firmJson.get("logo"), (String)firmJson.get("link"));
    	JSONArray shops = (JSONArray)firmJson.get("shops");
    	for(int i=0; i<shops.size(); i++){
    		JSONObject tempoShop = (JSONObject) shops.get(i);
    		firm.addStore(new Store((String)tempoShop.get("name"), (String)tempoShop.get("address"), (String)tempoShop.get("mallname"), (String)tempoShop.get("description")));
    	}
    	
    	return firm;
	}
}
