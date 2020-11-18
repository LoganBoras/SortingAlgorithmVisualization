package main;
import java.util.Random;

public class Shuffle {
	private int[] array;
	private SortScreen theScreen;
	private Random rd;
	
	public Shuffle(SortScreen theScreen, int[] array) {
		this.theScreen = theScreen;
		this.array = array;
		rd = new Random();
		shuffle();
		System.out.println(theScreen.getDecide());
	}
	
	public void shuffle() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    theScreen.delay = 10;
	    theScreen.accesses = 0;
        theScreen.swaps = 0;
		
		int temp;
		int rand;
		theScreen.setTheName("Shuffle");
		
		theScreen.setDecide(rd.nextInt(12)+1);
		theScreen.setChoice(rd.nextInt(26));
		
		for(int i=0;i<array.length;i++) {
			rand = rd.nextInt(array.length);
			if(i%20==0) {
				theScreen.setDecide(rd.nextInt(12)+1);
				theScreen.setChoice(rd.nextInt(16)+5);
			}
			temp =array[i];
			array[i] = array[rand];
			array[rand] = temp;
            theScreen.swaps++;
			 theScreen.accesses+=2;
            theScreen.indexArray[i]=240;
			theScreen.repaint();
			if(i%theScreen.mod==0) {
 				 try {
 					Thread.sleep(theScreen.delay);
 				 } catch (InterruptedException e) {
 					e.printStackTrace();
 				 } 
          	} 
		}
		for(int i=0;i<array.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
		System.out.println(theScreen.getDecide());
	}
}
