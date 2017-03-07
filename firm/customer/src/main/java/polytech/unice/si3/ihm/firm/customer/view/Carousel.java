package polytech.unice.si3.ihm.firm.customer.view;

import java.util.concurrent.TimeUnit;

import polytech.unice.si3.ihm.firm.customer.controller.MainViewController;
import polytech.unice.si3.ihm.firm.customer.util.Log;

/**
 * 
 * Carousel Thread class
 *
 */
public class Carousel implements Runnable {
	private MainViewController carouselController;
	private Thread oldCarouselThread;
	
	/**
	 * Put it on "false" to make the carousel stop
	 */
	private static boolean carouselState;
	
	/**
	 * Normal constructor
	 * @param carouselController
	 */
	public Carousel(MainViewController carouselController, Thread oldCarouselThread){
		this.carouselController = carouselController;
		this.oldCarouselThread = oldCarouselThread;
	}
	
	@Override
    /**
     * {@inheritDoc}
     */
	public void run(){
		if(oldCarouselThread!=null)
			try {
				oldCarouselThread.join();
			} catch (Exception e1) {
				Log.error(this.getClass(), "Stop old thread failed", e1);
			}
		
		setCarouselState(true);
		int nbTick = 0;

		while(isCarouselState()){
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (Exception e) {
				Log.error(this.getClass(), "Wait until next animation carousel failed", e);
			}
			carouselController.tick(nbTick);
			nbTick = nbTick==2 ? 0 : nbTick+1;
		}
	}

	/**
	 * Getter of carousel state
	 * @return carousel actual state
	 */
	public static boolean isCarouselState() {
		return carouselState;
	}

	/**
	 * Setter of carousel state
	 * @param carouselState new carousel state
	 */
	public static void setCarouselState(boolean carouselState) {
		Carousel.carouselState = carouselState;
	}

}