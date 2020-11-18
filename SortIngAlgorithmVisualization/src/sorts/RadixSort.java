package sorts;

//Radix sort Java implementation 
import java.util.*;

import main.SortScreen; 

public class RadixSort implements Algorithm{ 
	private SortScreen theScreen;
	private int[] arr;
	
	public RadixSort(SortScreen screen, int[] arr) {
		this.theScreen = screen;
		this.arr = arr;
	}
	
	public void begin() {
	    theScreen.setTheName("Radixsort");
        theScreen.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	    theScreen.delay = 30;
	    theScreen.accesses = 0;
	    theScreen.swaps = 0;
	    
	     int n = arr.length; 
	     radixsort(arr, n); 
	     
			for(int i=0;i<arr.length;i++)
				theScreen.indexArray[i] = 0;
	        theScreen.repaint();
	}
	
	 // A utility function to get maximum value in arr[] 
	 public int getMax(int arr[], int n) 
	 { 
	     int mx = arr[0]; 
	     for (int i = 1; i < n; i++) { 
	         if (arr[i] > mx) 
	             mx = arr[i]; 
	         theScreen.accesses++;
	     }
	     return mx; 
	 } 
	
	 // A function to do counting sort of arr[] according to 
	 // the digit represented by exp. 
	 private void countSort(int arr[], int n, int exp) 
	 { 
	     int output[] = new int[n]; // output array 
	     int i; 
	     int count[] = new int[10]; 
	     Arrays.fill(count,0); 
	
	     // Store count of occurrences in count[] 
	     for (i = 0; i < n; i++) { 
	         count[ (arr[i]/exp)%10 ]++; 
	         theScreen.accesses++;
	     }
	
	     // Change count[i] so that count[i] now contains 
	     // actual position of this digit in output[] 
	     for (i = 1; i < 10; i++) { 
	         count[i] += count[i - 1];
	         theScreen.accesses++;
	     }
	
	     // Build the output array 
	     for (i = n - 1; i >= 0; i--) 
	     { 
	         output[count[ (arr[i]/exp)%10 ] - 1] = arr[i]; 
	         count[ (arr[i]/exp)%10 ]--; 
	         theScreen.accesses+=2;
	     } 
	
	     // Copy the output array to arr[], so that arr[] now 
	     // contains sorted numbers according to current digit 
	     for (i = 0; i < n; i++){ 
	         arr[i] = output[i]; 
             theScreen.swaps++;
	         theScreen.accesses++;
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
	 } 
	
	 // The main function to that sorts arr[] of size n using 
	 // Radix Sort 
	 private void radixsort(int arr[], int n) 
	 { 
	     // Find the maximum number to know number of digits 
	     int m = getMax(arr, n); 
	
	     // Do counting sort for every digit. Note that instead 
	     // of passing digit number, exp is passed. exp is 10^i 
	     // where i is current digit number 
	     for (int exp = 1; m/exp > 0; exp *= 10) 
	         countSort(arr, n, exp); 
	 } 
	 
	 public void setTheArr(int[] array) {
			this.arr = array;
		}  
}