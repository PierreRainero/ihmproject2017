package polytech.unice.si3.ihm.firm.view;

import java.util.concurrent.TimeUnit;

import polytech.unice.si3.ihm.firm.controller.MainViewController;

/**
 * 
 * Carousel Thread class
 *
 */
public class Carousel implements Runnable {
	private MainViewController carouselController;
	
	/**
	 * Put it on "false" to make the carousel stop
	 */
	public static boolean carouselState;
	
	/**
	 * Normal constructor
	 * @param carouselController
	 */
	public Carousel(MainViewController carouselController){
		this.carouselController = carouselController;
		carouselState = true;
	}
	
	@Override
    /**
     * {@inheritDoc}
     */
	public void run(){
		while(carouselState){
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			carouselController.tick();
		}
	}

}