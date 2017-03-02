package polytech.unice.si3.ihm.firm.util;

import java.io.File;

import javafx.scene.image.Image;

public class ImageBuilder {
	
	private ImageBuilder(){
	}
	
	public static Image getImage(String url) {
    	File file = new File(url);
        return new Image(file.toURI().toString());
	}
	
	public static Image getImage(String url, int width, int height) {
    	File file = new File(url);
        return new Image(file.toURI().toString(), width, height, false, false);
	}
}
