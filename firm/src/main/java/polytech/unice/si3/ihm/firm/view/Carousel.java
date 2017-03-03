package polytech.unice.si3.ihm.firm.view;

import java.util.concurrent.TimeUnit;

import polytech.unice.si3.ihm.firm.controller.MainViewController;

public class Carousel implements Runnable {
	private MainViewController carouselController;
	
	public static boolean carouselState;
	
	public Carousel(MainViewController carouselController){
		this.carouselController = carouselController;
		carouselState = true;
	}
	
	@Override
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