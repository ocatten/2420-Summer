package assignment04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver<T> {

	T[] backingArray;
	
	/*
	 * Sort the input array through a comparator using insertion sort.
	 * 
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
	}
	
	
	/*
	 * This method finds the largest number that can be made by arranging
	 * integers together in a given array. It combines the integers, one
	 * in front of another to return the largest number that can be created.
	 * If the list is empty, then the largest integer that can be made
	 * is 0.
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {
		return 0;
	}
	
	
	/*
	 * This method returns the largest int value that can be created by 
	 * arranging the given integers in any way. If the largest number is 
	 * too large for the int data type, throw OutOfRangeException
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
		
	}
	
	/*
	 * This method returns the largest long value that can be created by 
	 * arranging the given integers in any way. Throw OutOfRangeException 
	 * if the number is too large for the long data type.
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
		
	}
	
	/*
	 * Adds together the largest numbers formed and returns the sum
	 */
	public static BigInteger sum(List<Integer[]> list) {
		
	}
	
	
	/*
	 * 
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
		
	}
	
	/*
	 * Generates a list of Integer arrays from an input file.
	 */
	public static List<Integer[]> readFile(String filename){
		
	}
}
