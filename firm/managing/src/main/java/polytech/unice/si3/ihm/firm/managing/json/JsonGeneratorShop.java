package polytech.unice.si3.ihm.firm.managing.json;

import java.io.File;
import java.util.Optional;

public class JsonGeneratorShop {
	private Optional<File> fileSelected;
	private String shopName;

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Optional<File> getFileSelected() {
		return fileSelected;
	}

	public void setFileSelected(Optional<File> fileSelected) {
		this.fileSelected = fileSelected;
	}

	public JsonGeneratorShop(){
		fileSelected = Optional.empty();
	}
	
	public void generate(){
		
	}
}
