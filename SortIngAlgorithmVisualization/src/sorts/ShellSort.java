package sorts;

import main.SortScreen;

//Java implementation of ShellSort 
public class ShellSort 
{ 
	private SortScreen theScreen;
	private int[] arr;
	
	public ShellSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Shellsort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 5;
	    theScreen.accesses = 0;
	    theScreen.swaps = 0;
	    
	     sort(arr); 
		for(int i=0;i<arr.length;i++)
			theScreen.indexArray[i] = 0;
        theScreen.repaint();
	}
	
	 /* An utility function to print array of size n*/
	 static void printArray(int arr[]) 
	 { 
	     int n = arr.length; 
	     for (int i=0; i<n; ++i) 
	         System.out.print(arr[i] + " "); 
	     System.out.println(); 
	 } 
	
	 /* function to sort arr using shellSort */
	 int sort(int arr[]) 
	 { 
	     int n = arr.length; 
	
	     // Start with a big gap, then reduce the gap 
	     for (int gap = n/2; gap > 0; gap /= 2) 
	     { 
	         // Do a gapped insertion sort for this gap size. 
	         // The first gap elements a[0..gap-1] are already 
	         // in gapped order keep adding one more element 
	         // until the entire array is gap sorted 
	         for (int i = gap; i < n; i += 1) 
	         { 
	             // add a[i] to the elements that have been gap 
	             // sorted save a[i] in temp and make a hole at 
	             // position i 
	             int temp = arr[i]; 
	             theScreen.accesses++;
	
	             // shift earlier gap-sorted elements up until 
	             // the correct location for a[i] is found 
	             int j; 
	             for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) { 
	                 arr[j] = arr[j - gap];
	                 theScreen.swaps++;
	                 theScreen.accesses+=2;
		             theScreen.indexArray[j]=240;
		             theScreen.indexArray[j - gap]=240;
		                theScreen.repaint();
		                if(j%(theScreen.mod)==0) {
		     				 try {
		     					Thread.sleep(theScreen.delay);
		     				 } catch (InterruptedException e) {
		     					e.printStackTrace();
		     				 } 
		              	} 
	             }
	
	             // put temp (the original a[i]) in its correct 
	             // location 
	             arr[j] = temp; 
	             theScreen.accesses++;
	         } 
	     } 
	     return 0; 
	 } 
	
	 // Driver method 
//	 public static void main(String args[]) 
//	 { 
//	     int arr[] = {12, 34, 54, 2, 3}; 
//	     System.out.println("Array before sorting"); 
//	     printArray(arr); 
//	
//	     ShellSort ob = new ShellSort(); 
//	     ob.sort(arr); 
//	
//	     System.out.println("Array after sorting"); 
//	     printArray(arr); 
//	 } 
}  