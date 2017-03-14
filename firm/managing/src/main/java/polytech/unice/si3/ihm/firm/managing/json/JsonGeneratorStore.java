package polytech.unice.si3.ihm.firm.managing.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import polytech.unice.si3.ihm.firm.common.json.JsonReceiver;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;
import polytech.unice.si3.ihm.firm.common.util.Log;

public class JsonGeneratorStore {
	private static final String PATH = System.getProperties().get("user.dir")+"/../datas/content.json";
	
	private Store store;
	private Optional<File> fileSelected;
	
	public JsonGeneratorStore(){
		fileSelected = Optional.empty();
		store = new Store();
	}
	
	public JsonGeneratorStore(Store store){
		this.store = store;
		fileSelected =  Optional.of(new File(store.getImage()));
	}

	public void setShopName(String shopName) {
		store.changeStoreName(shopName);
	}
	
	public void setShopAdress(String newAddress) {
		store.changeStoreAddress(newAddress);
	}
	
	public void setShopCity(String newCity) {
		store.changeStoreCity(newCity);
	}
	
	public void setShopPostal(String postalCode) {
		store.changeCityNumber(postalCode);
	}
	
	public void setShopDepart(String depart) {
		store.changeDepartement(depart);
	}

	public void setShopMallName(String mallName) {
		store.changeStoreMallName(mallName);
	}
	
	public void setShopRegion(String regionName){
		store.changeRegionName(regionName);
	}
	
	public void setShopDescription(String description){
		store.changeStoreDescription(description);
	}
	
	public Optional<File> getFileSelected() {
		return fileSelected;
	}

	public void setFileSelected(Optional<File> fileSelected) {
		this.fileSelected = fileSelected;
	}
	
	@SuppressWarnings("unchecked")
	public void generate() throws FileNotFoundException, IOException, ParseException{
		JSONObject firmJson = JsonReceiver.getJsonFirm(PATH);
		JSONArray shops = (JSONArray)firmJson.get("shops");
		shops.add(getStoreJson());
	
		File file = new File(PATH);
		if (!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(firmJson.toString());
		writer.flush();
		writer.close();
		
		Log.info(this.getClass(), "JSON modifie");
	}
	
	private void fixImageUrl(){
		if(fileSelected.isPresent())
			store.changeStoreImage(fileSelected.get().getAbsolutePath());
		else if(store.getImage().isEmpty())
			store.changeStoreImage("");
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getStoreJson(){
		fixImageUrl();
		
		JSONObject returnValue = new JSONObject();
		
		returnValue.put("name", store.getName());
		returnValue.put("address", store.getAddress());
		returnValue.put("city", store.getCity());
		returnValue.put("city number", store.getCityNumber());
		returnValue.put("department", store.getDepartment());
		returnValue.put("region", store.getRegion());
		returnValue.put("mallname", store.getMallName());
		returnValue.put("image", store.getImage());
		returnValue.put("description", store.getDescription());
		
		return returnValue;
	}
}
