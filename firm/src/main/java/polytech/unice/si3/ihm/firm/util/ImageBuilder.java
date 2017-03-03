package polytech.unice.si3.ihm.firm.util;

import java.io.File;

import javafx.scene.image.Image;

/**
 * 
 * Static class to generate an Image with an url
 *
 */
public class ImageBuilder {
	
	/**
	 * Private constructor to hide the public one
	 */
	private ImageBuilder(){
	}
	
	/**
	 * Create an image using an url
	 * @param url path to the image
	 * @return image object of the url
	 */
	public static Image getImage(String url) {
    	File file = new File(url);
        return new Image(file.toURI().toString());
	}
	
	/**
	 * Create an image using an url
	 * @param url path to the image
	 * @param width width of the image
	 * @param height height of the image
	 * @return image object of the url
	 */
	public static Image getImage(String url, int width, int height) {
    	File file = new File(url);
        return new Image(file.toURI().toString(), width, height, false, false);
	}
}
