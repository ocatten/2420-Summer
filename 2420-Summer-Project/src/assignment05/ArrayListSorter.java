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
	 * Takes an arrayList of generic class objects and sorts it through quicksort.
	 * 
	 * @param arr: ArrayList of generic objects to be sorted.
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		
		// Catch case for smaller arrayLists
		if (arr.size() <= 1) {
			
			return; // If the array is smaller than 2 elements, it's already sorted.
		}
		
		Comparator<T> cmp = new Comparator<T>() { // Makes the comparator to make comparisons with.
			public int compare(T e1, T e2) { return e1.compareTo(e2); } };
		
		int leftBound = 0;
		int rightBound = arr.size() - 1;
		int high = rightBound - 1;
		int low = leftBound + 1;
		int mid = pivotAtMid(arr, high, low);
		
		//T pivot = arr.get(pivotAtMid(arr, 0, arr.size()));
		//Collections.swap(arr, rightBound, arr.indexOf(pivot));
		
		while (leftBound < rightBound) {
			
			//pivot = arr.get(pivotAtMid(arr, 0, arr.size()-1));
			//System.out.println("pivot: " + pivot);
			Collections.swap(arr, high, mid);
			
			while (low < high) {
				
				high = rightBound - 1;
				low = leftBound + 1;
				mid = pivotAtMid(arr, high, low);
				
				System.out.println("high " + arr.get(high));
				System.out.println("low " + arr.get(low));
				System.out.println("mid " + arr.get(mid));
				
				while (cmp.compare(arr.get(low), arr.get(mid)) < 1) {
					System.out.println(" low not found " + arr.get(low) + " against " + arr.get(mid));
					low++;
				}
				
				System.out.println("low found: " + arr.get(low));
				
				while (cmp.compare(arr.get(high), arr.get(mid)) > -1 && low < high) {
					System.out.println(" high not found " + arr.get(high) + " against " + arr.get(mid));
					high--;
				}
				
				System.out.println("high found: " + arr.get(high));
				
				Collections.swap(arr, high, low);
				
				System.out.print("New array: ");
				
				for (int i = 0; i < arr.size(); i++) {
					System.out.print(arr.get(i) + " ");
				}
				
				System.out.println();
			}
			
			if (low < rightBound) {
				Collections.swap(arr, leftBound, mid);
				rightBound--;
				System.out.println("rightBound: " + arr.get(rightBound));
			} else {
				Collections.swap(arr, leftBound, mid);
				leftBound++;
				System.out.println("leftBound: " + arr.get(leftBound));
			}
			
		}
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
