/**
 * This class sorts arrayLists that are permuted or sorted in descending or ascending order and compares mergesort
 * and quicksort against one another to find which is faster. No built-in sorting methods can be used unless otherwise
 * specified.
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:12:23
 * CS 2420-001_SMR-2023
 */

package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListSorter {
	
	/**
	 * Takes a generic class object and performs a mergesort on the arrayList of the generic objects.
	 * 
	 * @param arr: ArrayList of generic items to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		
		
	}
	
	
	
	/**
	 * Takes an arrayList of generic class objects and sorts it through the quicksort algorithm, that splits a
	 * larger array into sub arrays and sorts those.
	 * 
	 * @param arr: ArrayList of generic objects to be sorted.
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		
		// Catch case for small array.
		//if(arr.size() < 2) {
			//return; // If there is only one element, it is already sorted.
		//}
		
		// Print out the original array.
		System.out.print(arr);
		System.out.println(" unsorted");
		
		// Set up relevant fields.
		int leftBound = 0;
		int rightBound = arr.size()-1;
		int low = leftBound;
		
		partition(leftBound, rightBound, arr);
	}
	
	
	
	/**
	 * Helper method that actually sorts the partitions for quicksort
	 * 
	 * @param leftBound: index of the array that bounds the partition's lowest index
	 * @param rightBound: index of the array that bounds the partition's highest index
	 * @param arr: Array the partition is sorting.
	 */
	public static <T extends Comparable<? super T>> void partition(int leftBound, int rightBound, ArrayList<T> arr) {
		
		// Base case, tracks when the partitions are tracking sub arrays that don't exist
		if (leftBound < 0) {
			return;
		} else if (rightBound >= arr.size()) {
			return;
		}
		
		// Set up relevant fields.
		int low = leftBound;
		int high = rightBound - 1; // This leaves room to swap the pivot to the end
		int mid = pivotAtMid(arr, rightBound, leftBound);
		T pivot = arr.get(mid);
		
		// Group of test statements.
		System.out.println("Partition: " + arr);
		
		System.out.println("leftBound: " + arr.get(leftBound));
		System.out.println("rightBound: "+ rightBound);
		System.out.println("rightBound: " + arr.get(rightBound));
		System.out.println("low: " + arr.get(low));
		System.out.println("high: " + arr.get(high));
		System.out.println("pivot: " + pivot);
		System.out.println("mid index: " + mid);
		
		// Comparator object to use
		Comparator<T> cmp = new Comparator<T>() { // Makes the comparator to make comparisons with.
			public int compare(T e1, T e2) { return e1.compareTo(e2); } };
		
		// Get rid of the pivot we're working with.
		Collections.swap(arr, rightBound, mid);
		
		
		// While the algorithm hasn't sorted through the two ends of the partition:
		while (low < high) {
			
			// If the low is lower than pivot, increment the position
			if (cmp.compare(arr.get(low), pivot) < 0) {
				
				System.out.println("low not found " + arr.get(low) + " against " + pivot); // Test statement
				low++;
			}
			
			// If the low is higher than the pivot, print as such
			if (cmp.compare(arr.get(low), pivot) > 0) {
				System.out.println("low found " + arr.get(low) + " against " + pivot); // Test statement
			}
			
			
			// If the high is still higher than the pivot, decrement the position
			if (cmp.compare(arr.get(high), pivot) > 0 && low < high) {
				
				System.out.println("high not found " + arr.get(high) + " against " + pivot);
				high--;
			}
			
			// If the high is lower than the pivot, print as such
			if (cmp.compare(arr.get(high), pivot) < 0 && low < high) {
				
				System.out.println("high found " + arr.get(high) + " against " + pivot); // Test statement
				
				// If the low has also been found, swap the two and then move on
				if (cmp.compare(arr.get(low), pivot) > 0) {
					
					System.out.println("SWAPPING " + arr.get(low) + " LOW WITH " + arr.get(high) + " HIGH"); // Test statement
					Collections.swap(arr, high, low);
				}
			}
		}
		
		// Now put the pivot back into its place IF IT BELONGS THERE
		int pivotIndex = low;
		
		// Test statements
		System.out.println("current pivotIndex " + pivotIndex);
		System.out.println("current rightBound " + rightBound);
		
		if ( pivotIndex != rightBound-1) {
			
			System.out.println(rightBound);
			System.out.println("SWAPPING " + arr.get(pivotIndex) + " WITH " + pivot); // Test statement
			Collections.swap(arr, pivotIndex, rightBound); // Swap the two values.
		
		} else if (pivotIndex != rightBound-1) { 
			
			
		} else {
			
			return;
		}
		
		System.out.println(rightBound);
		System.out.println("NEXT PARTITION");
		partition(pivotIndex, rightBound, arr);
		partition(leftBound, pivotIndex-1, arr);
	}
	
	
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the center of the array.
	 * 
	 * @param arr: List that the method will compute the pivot point for.
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtMid(ArrayList<T> arr, int high, int low) {
		return (high + low) / 2;
	}
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the low of the array, or 0.
	 *
	 * @param high: Highest index the method will be sorting for
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtLow(ArrayList<T> arr) {
		return 0;
	}
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the center of the array.
	 * 
	 * @param arr: List that the method will compute the pivot point for.
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtHigh(ArrayList<T> arr) {
		return arr.size() - 1;
	}
	
	
	
	/**
	 * Generates an arrayList of 1 to size in ascending order.
	 * 
	 * @param size: Size of the returned array.
	 * @return ArrayList<Integer>: The sorted arrayList in ascending order from 1 to size
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, 1 to size to the returned arrayList.
		for (int i = 1; i <= size; i++) {
			returnArr.add(i);
		}
		
		return returnArr;
	}
	
	
	
	/**
	 * Generates a permuted(random) series of numbers from 1 to size and returns an arrayList of them
	 * 
	 * @param size: Greatest value of the values and the size of the returned ArrayList
	 * @return ArrayList<Integer>: Permuted arrayList of 1 to size
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, 1 to size to the returned arrayList.
		for (int i = 1; i <= size; i++) {
			returnArr.add(i);
		}
		
		// Now, the sorted list needs to be shuffled:
		Collections.shuffle(returnArr);
		
		return returnArr;
	}
	
	
	
	/**
	 * Generates an arrayList of 1 to size in descending order.
	 * 
	 * @param size: Size of the returned array.
	 * @return ArrayList<Integer>: The sorted arrayList in descending order from 1 to size
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, size to 1 to the returned arrayList.
		for (int i = size; 0 < i; i--) {
			returnArr.add(i);
		}
		
		return returnArr;
	}
}
