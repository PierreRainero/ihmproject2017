package polytech.unice.si3.ihm.firm.common.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import polytech.unice.si3.ihm.firm.common.exceptions.ContentException;
import polytech.unice.si3.ihm.firm.common.model.commercial.Firm;
import polytech.unice.si3.ihm.firm.common.model.commercial.Product;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.common.util.Log;

/**
 * 
 * Static class to parse a json and get the firm content
 *
 */
public class ContentParser {
	private static  String path = System.getProperties().get("user.dir")+"/../datas/content.json";
	private static final String INCORRECTJSON = "Incorrect JSONFile - Problem with firm's infos : "+ path;
	
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String IMAGE = "image";
	
	private JSONObject firmJson;
	
	
	/**
	 * Normal constructor
	 * @param datasPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public ContentParser(String datasPath) throws FileNotFoundException, IOException, ParseException{
		path = datasPath;
		firmJson = JsonReceiver.getJsonFirm(path);
	}
	
	/**
	 * Allows to get the firm datas
	 * @return firm to display
	 * @throws ContentException
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Firm getFirm() throws ContentException, IOException, ParseException{
    	Firm firm = getSkeletonFirm(firmJson);
    	fillShopsInfos(firm, firmJson);
    	fillProductsInfos(firm, firmJson);
    	fillAds(firm, firmJson);
    	
    	return firm;
	}
	
	/**
	 * Get the basic firm infos and create an firm object
	 * @param firmJson json containing all the datas required
	 * @return firm to use
	 * @throws ContentException
	 */
	private Firm getSkeletonFirm(JSONObject firmJson) throws ContentException{
    	String name = null;
    	String description = null;
    	String logo = null;
    	String link = null;
    	String banner = null;
    	
    	try{
        	name = (String)firmJson.get(NAME);
        	description = (String)firmJson.get(DESCRIPTION);
        	logo = (String)firmJson.get("logo");
        	link = (String)firmJson.get("link");
        	banner = (String)firmJson.get("banner");
    	}catch(Exception e){
    		Log.error(ContentParser.class, INCORRECTJSON, e);
    		throw new ContentException(INCORRECTJSON);
    	}

    	return new Firm(name, description, logo, link, banner);
	}
	
	/**
	 * Add all shops of the firm
	 * @param firm firm to update
	 * @param firmJson json containing all the datas required
	 * @throws ContentException
	 */
	private void fillShopsInfos(Firm firm, JSONObject firmJson) throws ContentException{
    	JSONArray shops = null;
    	
    	try{
        	shops = (JSONArray)firmJson.get("shops");
    	}catch(Exception e){
    		Log.error(ContentParser.class, INCORRECTJSON, e);
    		throw new ContentException(INCORRECTJSON);
    	}
    	
    	try{
	    	for(int i=0; i<shops.size(); i++){
	    		JSONObject tempoShop = (JSONObject) shops.get(i);
	    		firm.addStore(new Store((String) tempoShop.get(NAME),
										(String) tempoShop.get("address"),
										(String) tempoShop.get("city"),
										(String) tempoShop.get("city number"),
										(String) tempoShop.get("department"),
										(String) tempoShop.get("region"),
										(String) tempoShop.get("mallname"),
										(String) tempoShop.get(DESCRIPTION),
										(String) tempoShop.get(IMAGE)));
	    	}
	    }catch(Exception e){
	    	String errorMsg = "Incorrect JSONFile - Problem with shops infos : "+path;
	    	Log.error(ContentParser.class, errorMsg, e);
	    	throw new ContentException(errorMsg);
	    }
	}
	
	/**
	 * Add all product of the firm
	 * @param firm firm to update
	 * @param firmJson json containing all the datas required
	 * @throws ContentException
	 */
	private void fillProductsInfos(Firm firm, JSONObject firmJson) throws ContentException{
    	JSONArray products = null;
    	
    	try{
        	products = (JSONArray)firmJson.get("products");
    	}catch(Exception e){
    		Log.error(ContentParser.class, INCORRECTJSON, e);
    		throw new ContentException(INCORRECTJSON);
    	}
    	
    	try{
	    	for(int i=0; i<products.size(); i++){
	    		JSONObject tempoProduct = (JSONObject) products.get(i);
	    		
	    		Product productToAdd = new Product((String) tempoProduct.get(NAME), 
						(String) tempoProduct.get(IMAGE), 
						(String) tempoProduct.get("reference"), 
						(String) tempoProduct.get(DESCRIPTION), 
						(double) tempoProduct.get("price"));
	    		
	    		if((boolean)tempoProduct.get("promoted"))
	    			productToAdd.markProductAsPromoted();
	    		
	    		if((boolean)tempoProduct.get("flagship"))
	    			productToAdd.markProductAsFlagship();
	    			
	    		firm.addProduct(productToAdd);		
	    	}
	    }catch(Exception e){
	    	String errorMsg = "Incorrect JSONFile - Problem with products infos : "+path;
	    	Log.error(ContentParser.class, errorMsg, e);
	    	throw new ContentException(errorMsg);
	    }
	}
	
	/**
	 * Add all ads of the firm
	 * @param firm firm to update
	 * @param firmJson json containing all the datas required
	 * @throws ContentException
	 */
	private void fillAds(Firm firm, JSONObject firmJson) throws ContentException{
    	JSONArray advertisements = null; 
    	
    	try{
        	advertisements = (JSONArray)firmJson.get("advertisements"); 
    	}catch(Exception e){
    		Log.error(ContentParser.class, INCORRECTJSON, e);
    		throw new ContentException(INCORRECTJSON);
    	}
    	
    	for(int i=0; i<advertisements.size(); i++){
    		firm.addAdvertisement((String)advertisements.get(i));
    	}
	}
}
