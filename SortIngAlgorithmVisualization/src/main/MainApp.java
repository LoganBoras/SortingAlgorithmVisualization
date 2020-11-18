package main;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import sorts.*;

public class MainApp {
	private int[] array;
	Random rd;
	Shuffle theShuffle;
	RunThrough theRunThrough;
	Green theGreen;
	
	private BubbleSort theBubble;
	private QuickSort theQuick;
	private CycleSort theCycle;
	private GnomeSort theGnome;
	private MergeSort theMerge;
	private PancakeSort thePancake;
	private RadixSort theRadix;
	private SelectionSort theSelection;
	private StoogeSort theStooge;
	private HeapSort theHeap;
	private InsertionSort theInsertion;
	private ShellSort theShell;
	private BogoSort theBogo;

	public MainApp() {
		
		JFrame appFrame = new JFrame();
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createArray(192*2);
		SortScreen sortScreen = new SortScreen(this, array);
		rd = new Random();
		
		appFrame.setLayout(new BorderLayout());
		appFrame.setSize(1920,1080);
		appFrame.add("Center", appFrame.getContentPane().add(sortScreen));
		appFrame.setVisible(true);
		
		sortScreen.repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		theRunThrough = new RunThrough(sortScreen, array);
		theShuffle = new Shuffle(sortScreen, array);
		theGreen = new Green(array, sortScreen);
		
		theBubble = new BubbleSort(sortScreen, array);
		theQuick = new QuickSort(sortScreen, array);
		theCycle = new CycleSort(sortScreen, array);
		theGnome = new GnomeSort(sortScreen, array);
		theMerge = new MergeSort(sortScreen, array);
		thePancake = new PancakeSort(sortScreen, array);
		theRadix  = new RadixSort(sortScreen, array);
		theSelection = new SelectionSort(sortScreen, array);
		theStooge = new StoogeSort(sortScreen, array);
		theInsertion = new InsertionSort(sortScreen, array);
		theHeap = new HeapSort(sortScreen, array);
		theShell = new ShellSort(sortScreen, array);
		theBogo = new BogoSort(sortScreen, array);
		
		theShell.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();

		theQuick.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theRadix.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();

		theHeap.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theSelection.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theBubble.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theMerge.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		thePancake.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theStooge.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theCycle.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theGnome.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();

		theInsertion.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		theBogo.begin();
		theRunThrough.runThrough();
		theShuffle.shuffle();
		
		
//		while(true) {
//			switch(rd.nextInt(8)) {
//			case 0:
//				theSelection.begin();
//				theShuffle.shuffle();
//			case 1:
//				theStooge.begin();
//				theShuffle.shuffle();
//			case 2:
//				thePancake.begin();
//				theShuffle.shuffle();
//			case 3:
//				theRadix.begin();
//				theShuffle.shuffle();
//			case 4:
//				theMerge.begin();
//				theShuffle.shuffle();
//			case 5:
//				theCycle.begin();
//				theShuffle.shuffle();
//			case 6:
//				theBubble.begin();
//				theShuffle.shuffle();
//			case 7:
//				theQuick.begin();
//				theShuffle.shuffle();
//			}
			
//		}
	}
	

	private void createArray(int i) {
		array = new int[i];
		for(int j=0;j<i;j++) {
			array[j] = (int) ((double)j/i*192)+1; 
		}
	}
	
	
	public static void main(String[] args) {
		new MainApp();
	}
}
