package main;
import java.util.Random;

public class RunThrough {
	private int[] array;
	private SortScreen theScreen;
	private Random rd;
	
	public RunThrough(SortScreen theScreen, int[] array) {
		this.theScreen = theScreen;
		this.array = array;
		rd = new Random();
		theScreen.setDecide(rd.nextInt(12)+1);
		theScreen.setChoice(rd.nextInt(26));
		runThrough();
		System.out.println(theScreen.getDecide());
	}
	
	public void runThrough() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    theScreen.delay = 10;
	    theScreen.accesses = 0;
	    theScreen.swaps = 0;

		theScreen.setTheName("Check");

		
		for(int i=0;i<array.length;i++) {
            theScreen.indexArray[i]=240;
            theScreen.accesses++;
			theScreen.repaint();
			if(i%theScreen.mod==0) {
 				 try {
 					Thread.sleep(theScreen.delay);
 				 } catch (InterruptedException e) {
 					e.printStackTrace();
 				 } 
          	} 
		}
		for(int i=0;i<array.length;i++) {
			theScreen.indexArray[i] = 0;
		}
        theScreen.repaint();
	}
}
