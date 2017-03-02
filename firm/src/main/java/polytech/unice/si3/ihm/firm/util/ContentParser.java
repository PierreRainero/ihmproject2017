package polytech.unice.si3.ihm.firm.util;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import polytech.unice.si3.ihm.firm.exceptions.ContentException;
import polytech.unice.si3.ihm.firm.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.model.commercial.Product;
import polytech.unice.si3.ihm.firm.model.commercial.Store;

public class ContentParser {
	
	private ContentParser(){
	}
	
	public static Firm getFirm() throws ContentException{
    	JSONParser parser = new JSONParser();
    	JSONObject firmJson = null;
    	try {
			Object obj = parser.parse(new FileReader(System.getProperties().get("user.dir")+"/src/main/resources/datas/content.json"));
	    	firmJson = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

    	String name = null;
    	String description = null;
    	String logo = null;
    	String link = null;
    	String banner = null;
    	JSONArray advertisements = null; 
    	JSONArray shops = null;
    	JSONArray products = null;
    	
    	try{
        	name = (String)firmJson.get("name");
        	description = (String)firmJson.get("description");
        	logo = (String)firmJson.get("logo");
        	link = (String)firmJson.get("link");
        	banner = (String)firmJson.get("banner");
        	advertisements = (JSONArray)firmJson.get("advertisements"); 
        	shops = (JSONArray)firmJson.get("shops");
        	products = (JSONArray)firmJson.get("products");
    	}catch(Exception e){
    		throw new ContentException("Incorrect JSONFile - Problem with firm's infos : "+System.getProperties().get("user.dir")+"/src/main/resources/datas/content.json");
    	}

    	Firm firm = new Firm(name, description, logo, link, banner);
    	
    	try{
	    	for(int i=0; i<shops.size(); i++){
	    		JSONObject tempoShop = (JSONObject) shops.get(i);
	    		firm.addStore(new Store((String) tempoShop.get("name"),
										(String) tempoShop.get("address"),
										(String) tempoShop.get("city"),
										(String) tempoShop.get("city number"),
										(String) tempoShop.get("mallname"),
										(String) tempoShop.get("description"),
										(String) tempoShop.get("image")));	
	    	}
	    }catch(Exception e){
	    	throw new ContentException("Incorrect JSONFile - Problem with shops infos : "+System.getProperties().get("user.dir")+"/src/main/resources/datas/content.json");
	    }
    	
    	try{
	    	for(int i=0; i<products.size(); i++){
	    		JSONObject tempoProduct = (JSONObject) products.get(i);
	    		firm.addProduct(new Product((String) tempoProduct.get("name"), 
	    									(String) tempoProduct.get("image"), 
	    									(String) tempoProduct.get("reference"), 
	    									(String) tempoProduct.get("description"), 
	    									(Double) tempoProduct.get("price")));	
	    	}
	    }catch(Exception e){
	    	throw new ContentException("Incorrect JSONFile - Problem with products infos : "+System.getProperties().get("user.dir")+"/src/main/resources/datas/content.json");
	    }
    	
    	
    	for(int i=0; i<advertisements.size(); i++){
    		firm.addAdvertisement((String)advertisements.get(i));
    	}
    	
    	return firm;
	}
}
