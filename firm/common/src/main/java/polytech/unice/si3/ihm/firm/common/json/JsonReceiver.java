package polytech.unice.si3.ihm.firm.common.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReceiver {
	
	/**
	 * Private constructor to hide the public one
	 */
	private JsonReceiver(){
		
	}
	
	/**
	 * Allow to generate a JSONObject with all datas needed
	 * @return json with all datas
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONObject getJsonFirm(String path) throws FileNotFoundException, IOException, ParseException{
    	JSONParser parser = new JSONParser();
    	JSONObject firmJson;

		Object obj = parser.parse(new FileReader(path));
	    firmJson = (JSONObject) obj;

    	return firmJson;
	}
}
