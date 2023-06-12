package assignment04;
/**
 * This class takes integers of randomly assorted numbers and finds the largest possible combination for the indicies.m
 * 
 * 
 * @author: Parker Catten @u0580588 & Everett Oglesby @u0779085
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
		//Catch case for empty list
		if(arr.length < 1) {
			return BigInteger.valueOf(0);
		}
		
		Double[] doubleArray = new Double[arr.length];
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < 10) {
				doubleArray[i] = (double) arr[i];
			}
			else {
				double newNum = (double) arr[i];
				while(newNum > 10) {
					newNum /= 10;
				}
				doubleArray[i] = newNum;
			}
		}
		Double[] temp = doubleArray;
		
		Comparator<Double> cmp = new Comparator<Double>() { 
			public int compare(Double e1, Double e2) { return e1.compareTo(e2); } };
		
		insertionSort(temp,cmp);
		
		
		//Create a new double list
		double[] sortedDoubles = new double[arr.length];
		//Reverse the new list
		for(int i = 0; i < arr.length; i++) {
			sortedDoubles[i] = temp[ arr.length - (1+i) ];	
		}
		
		Integer[] sortedList = new Integer[arr.length];
		//Convert each double back to an Integer
		for(int i = 0; i < sortedDoubles.length; i++) {
			double newDouble = sortedDoubles[i];
			while(sortedDoubles[i] != (int) sortedDoubles[i]) {
				newDouble = sortedDoubles[i] *= 10;
			}
			sortedList[i] = (int) newDouble;
		}
			
		String bigNumber = "";
		
		for(Integer num : sortedList){
			bigNumber = bigNumber + num.toString();
		}
		return new BigInteger(bigNumber);
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
	 * This method returns the largest concatenation out of a list of arrays. If k is 0, it will return the largest,
	 * if k is 1, it returns the second largest, and so on.
	 * 
	 * @param list: List of Integer arrays
	 * @param k: Rank of the largestNumber combination out of the arrays
	 * @return: kth largest array in the list
	 * @throws IllegalArgumentException: If k is out of range.
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		
		// Catch case for k being to large.
		if(k >= list.size()) {
			throw new IllegalArgumentException();
		}
		
		
		// Create a new array to keep the largestNumbers
		BigInteger[] largestSorted = new BigInteger[list.size()];
		
		// Find the largestNumber for each line and add it to the temporary list.
		for(int i = 0; i < list.size(); i++) {
			
			largestSorted[i] = findLargestNumber(list.get(i));
		}
		
		System.out.println("finished looping");
		// Comparator to sort the list
		Comparator<BigInteger> cmp = new Comparator<BigInteger>() { 
			public int compare(BigInteger e1, BigInteger e2) { return e1.compareTo(e2); } };
		
		// Sort the temporary list.
		insertionSort(largestSorted, cmp);
		
		// Next, find the kth object in this list:
		BigInteger target = largestSorted[k];
		
		/* Take the list of unsorted largestNumbers to find which index in the list the
		 * method will return.
		 */
		
		// Create a new array to keep the largestNumbers
		ArrayList<BigInteger> largestUnsorted = new ArrayList<BigInteger>();
		
		// Find the largestNumber for each line and add it to the temporary list.
		for(int i = 0; i < list.size(); i++) {
			
			largestUnsorted.add(findLargestNumber(list.get(i)) );
		}
		
		// Find the index the method will return
		int returnIndex = largestUnsorted.indexOf(target);
		
		return list.get(returnIndex);
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
