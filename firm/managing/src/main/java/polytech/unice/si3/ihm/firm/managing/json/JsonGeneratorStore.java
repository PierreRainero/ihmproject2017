package polytech.unice.si3.ihm.firm.managing.json;

import java.io.File;
import java.util.Optional;
import polytech.unice.si3.ihm.firm.common.model.commercial.Store;

public class JsonGeneratorStore {
	private Store store;
	private Optional<File> fileSelected;
	
	public JsonGeneratorStore(){
		fileSelected = Optional.empty();
		store = new Store();
	}

	public void setShopName(String shopName) {
		store.changeStoreName(shopName);
	}

	public Optional<File> getFileSelected() {
		return fileSelected;
	}

	public void setFileSelected(Optional<File> fileSelected) {
		this.fileSelected = fileSelected;
	}
	
	public void generate(){
		fixImageUrl();
	}
	
	private void fixImageUrl(){
		if(fileSelected.isPresent())
			store.changeStoreImage(fileSelected.get().getAbsolutePath());
		else
			store.changeStoreImage("");
	}
}
