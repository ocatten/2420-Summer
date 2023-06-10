package assignment04;
/**
 * This class takes integers of randomly assorted numbers and finds the largest possible combination for the indicies.m
 * 
 * 
 * @author: Parker Catten @u0580588 & Everett Oglesby
 * @version: 06:08:23
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestNumberSolver {
	
	/**
	 * Sorts the array using an insertion sort algorithm with the comparator.
	 * 
	 * @param arr: Array of generic values, integers or doubles
	 * @param cmp: Comparator that ranks each item
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
		// Loop through every element of the array in the parameter
		for (int i = 1; i < arr.length; ++i) {
	        
			// Takes the ith element and uses it as an insertion key to make comparisons with
			T inserted = arr[i];
			// j is essentially a position before the insertion key to make comparisons with
			int j = i - 1;
            
			// Loops through every item from the insertion key downward, checks to see if the jth element is greater than the key
            while (j >= 0 && cmp.compare(arr[j], inserted) > 0) {
            	
            	// If the key is less than the jth element, the element after it is the new value.
            	// After it's looped once, it's only checking if the jth element is still above 0
            	arr[j + 1] = arr[j];
            	j = j - 1;
	        }
            
            // This makes sure elements aren't overwritten by the while loop if they're in order.
            //  It resets the loop for the next key.
            arr[j + 1] = inserted;
	     } 
	}
	
	
	
	/**
	 * This method returns the largest possible combination of numbers in the array (concatenated) without
	 * altering the array in any way. If the array has no indicies, it returns 0.
	 * 
	 * @param arr: Array of integers that will be concatenated into a BigInteger
	 * @return: Result of the largest concantenation of indicies
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		
		// Catch case for empty array.
		if(arr.length < 1) {
			return BigInteger.valueOf(0);
		}
		
		/* Sorting by descending order WILL NOT work here. Instead, each digit should be paired with another and those 
		 * concatenated numbers should be tested against each other to find the largest combination. However, this 
		 * method should be slower for larger data sets. Since single digits cause issues with the previously mentioned
		 * approach, each number will be sorted according to its leading digit and will be sorted from there (without
		 * counting 0's).
		 */
		
		// First, the program will sort and reverse the list in the parameter after copying it (to avoid altering the original)
		Integer[] temp = arr;
		
		// Sort the list
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
		
		insertionSort(temp, cmp);
		
		// Now reverse the list:
		Integer[] sortedTemp = new Integer[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			sortedTemp[i] = temp[ arr.length - (1+i) ];	
		}
		
		
		// Next, create arrayLists for single digits and non-single digits to make comparisons with later.
		ArrayList<Integer> singleDigits = new ArrayList<Integer>();
		ArrayList<Double> values = new ArrayList<Double>();
		
		// Loop through each element in the array:
		for (int i = 0; i < sortedTemp.length; i++) {
			if (0 < sortedTemp[i] && sortedTemp[i] < 10) { // If this is a digit between 1 and 9 (inclusive):
				singleDigits.add(sortedTemp[i]);
			} else { // If it isn't a single digit, add it to the other arrayList.
				values.add( (double)(sortedTemp[i]) ); // This is a double for later comparisons.
			}
		}
		
		
		// Next, the code will sort the non-single digit code according to its correct order in concatenation by sorting
		//  each value according to its digits
		for(Double index : values) {
			if (index > 10) { // If it still hasn't been "simplified:"
				index /= 10;
			}
		}
		
		// Now that each value is a decimal, make comparisons accordingly.
		Comparator<Double> doubleCmp = new Comparator<Double>() { 
			public int compare(Double e1, Double e2) { return e1.compareTo(e2); } };
		
		values.sort(doubleCmp);
		// Next, we'll convert this list back into integer values and store these values in a reversed list
		for(Double index : values) {
			if (index < 10) { // If it still hasn't been UN-"simplified:"
				index *= 10;
			}
		}
		
		// Adds the values to a new arrayList in reversed order:
		ArrayList<Integer> sortedValues = new ArrayList<Integer>();
		
		for(Double index : values) {
			sortedValues.add(0, index.intValue());
		}
		
		
		// Lastly, the function will add the two arrayLists together but add single digits first if they're equivalent
		//  to a larger value's first digit.
		String bigInteger = "";
		
		// Loop through each singleDigit and make comparisons with each sortedValue:
		int sortedValueIndex = 0;
		for(Integer singleDigitIndex : singleDigits) {
			// While the sortedValueIndex is both a valid index and shares the same first digit with the singleDigits:
			while (sortedValueIndex < sortedValues.size() && sortedValues.get(sortedValueIndex) < (singleDigitIndex + 1) ) {
				
				bigInteger += sortedValues.get(sortedValueIndex);
				sortedValueIndex++;
			}
			
			bigInteger += singleDigitIndex;
		}
		
		
			
		return new BigInteger(bigInteger);
	}
	
	
	
	/**
	 * This method returns the largest possible concatenation of numbers in the array without altering the array
	 * in any way. However, if the largest number is larger than a possible integer, it will throw an error.
	 * 
	 * @param arr: Array of integer values to be concatenated
	 * @return: Largest possible integer value that is still an integer.
	 * @throws OutOfRangeException: If the largest number is too big for an integer, it throws an exception
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		
		// Finds the largest number of the array in the parameter represented by a bigInteger and sees if it's
		//  greater than the largest possible int 
		//System.out.println(Integer.MAX_VALUE); // Used to find the largest value
		
		// Sets up the comparator to be used in the insertionSort
		Comparator<BigInteger> cmp = new Comparator<BigInteger>() { 
			public int compare(BigInteger e1, BigInteger e2) { return e1.compareTo(e2); } };
		
		
		// If the BigInteger exceeds the found Integer.MAX_VALUE, it throws an exception.
		if ( cmp.compare(findLargestNumber(arr), BigInteger.valueOf(2147483647)) > 0 ) { 
			throw new OutOfRangeException("int");
		} else { // Otherwise, return the intValue of the BigInteger
			return findLargestNumber(arr).intValue();
		}
	}
	
	
	
	/**
	 * This method returns the largest possible concatenation of numbers in the array without altering the array
	 * in any way. However, if the largest number is larger than a possible integer, it will throw an error.
	 * 
	 * @param arr: Array of integer values to be concatenated
	 * @return: Largest possible integer value that is still an integer.
	 * @throws OutOfRangeException: If the largest number is too big for a long, it throws an exception
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		
		// Finds the largest number of the array in the parameter represented by a bigInteger and sees if it's
		//  greater than the largest possible long 
		//System.out.println(Long.MAX_VALUE); // Used to find the largest value of a long
				
		// Sets up the comparator to be used in the insertionSort
		Comparator<BigInteger> cmp = new Comparator<BigInteger>() { 
			public int compare(BigInteger e1, BigInteger e2) { return e1.compareTo(e2); } };
				
				
		// If the BigInteger exceeds the found Long.MAX_VALUE, it throws an exception.
		if ( cmp.compare(findLargestNumber(arr), new BigInteger("9223372036854775807")) > 0 ) { 
			throw new OutOfRangeException("long");
		} else { // Otherwise, return the longValue of the BigInteger
			return findLargestNumber(arr).longValue();
		}
	}
	
	
	
	/**
	 * This method takes a list of integers, finds the largest concatenations of numbers for each array of the list, 
	 * and then combines them and returns the sum. The original list cannot be modified in any way.
	 * 
	 * @param list: List of arrays to find the largest numbers for and add
	 * @return: Added sum of the largest numbers from the list.
	 */
	public static BigInteger sum(List<Integer[]> list) {
		
		// Instantiates a total to be returned later
		BigInteger total = BigInteger.valueOf( (long)(0) );
		
		// Loops through each element in the list.
		for(int i = 0; i < list.size(); i++) {
			total = total.add( findLargestNumber(list.get(i)) ); // Adds each arrays biggest number to the total
			//System.out.println("largest at "+ i + ": " + findLargestNumber(list.get(i))); // test statement
		}
		
		//System.out.println("total: " + total); // Used to check the return value.
		
		// Throws the total back as the result.
		return total;
	}
	
	
	
	/**
	 * This method returns the largest possible number corresponding to k. If k is 0, if will find the largest
	 * concatenated number. If it is 1, it will find the second largest number, and so forth. Throws an
	 * IllegalArgumentException if k is out of range, and it cannot modify the original list in any way.
	 * Additionally, this method must implement the insertionShort method and use a comparator in some way.
	 * 
	 * @param list
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		
		/**
		 * This method will use a pattern in permutations of combinations: Let's say the indicies were arranged in their largest
		 * combination and subsequently ranked by the index with the largest number, for example, [5, 4, 3, 2, 1]. The
		 * first index (5, or the largest value at the first index) will have its position determined by k. 
		 */
		
		return null;
	}
	
	
	
	/**
	 * This method takes an input file and reads each line into an array that it creates, returning that file
	 * as a list of arrays
	 * 
	 * @param filename: Name of the file that will be scanned to create the list of arrays.
	 * @return: List of integer arrays created from the filename.txt file.
	 */
	public static List<Integer[]> readFile(String filename) {
		
		// Creates the List of arrays to be returned
		List<Integer[]> fileList = new ArrayList<Integer[]>();
		
		// Reader for the file
		Scanner reader;
		
		// Tries to read the file to verify that it's there:
		try {
			
			// Creates the reader for the file
			reader = new Scanner(new File(filename));
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File " + filename + " not  found");
			//e.printStackTrace();
			
			return fileList;
		}
		
		
		// While there are still lines in the file:
		while(reader.hasNextLine()) {
			
			// Convert the nextLine into a single string.
			String currentLineString = reader.nextLine();
			
			// Converts that string into an array and then converts the indicies into integers
			String[] lineStringArray = currentLineString.split(" ");
			
			Integer[] lineArray = new Integer[lineStringArray.length];
			for(int i = 0; i < lineStringArray.length; i++) {
				
				// Adds the converted strings to the array
				lineArray[i] = Integer.valueOf(lineStringArray[i]);
			}
			
			// Adds the array to the list
			fileList.add(lineArray);
			
		}
		
		return fileList;
	}

}
