/**
 * Tester class for ArrayListSorter
 * 
 * @author: Parker Catten @u0580588 & Everett Oglesby
 * @version 02:23:23 CS-2420_SP-2023
 */
package assignment05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ArrayListSorterTester {
	
/*---------------------------------------------------ARRAYLIST GENERATOR TESTS--------------------------------------------------------*/
	
	@Test
	public void testGenerateAscendingOnSmall () {
	
		ArrayListSorter sorter = new ArrayListSorter();
		ArrayList<Integer> testCase = sorter.generateAscending(10);
	
		
		for (int i = 0; i < testCase.size(); i++) {
			
			//System.out.print(testCase.get(i) + " ");
			//assertEquals(i + 1, testCase.get(i));
		}
		
		//System.out.println();
	}
	
	
	
	@Test
	public void testGenerateDescendingOnSmall () {
		
		ArrayListSorter sorter = new ArrayListSorter();
		ArrayList<Integer> testCase = sorter.generateDescending(10);
	
		
		for (int i = 0; i < testCase.size(); i++) {
			
			//System.out.print(testCase.get(i) + " ");
			//assertEquals(i + 1, testCase.get(i));
		}
		
		//System.out.println();
	}
	
	
	
	@Test
	public void testGeneratePermutedOnSmall () {
		
		ArrayListSorter sorter = new ArrayListSorter();
		ArrayList<Integer> testCase = sorter.generatePermuted(10);
		ArrayList<Integer> uniqueValues = new ArrayList<Integer>();
	
		
		for (int i = 0; i < testCase.size(); i++) {
			
			// Verifies that all values are unique
			for (int j = 0; j < uniqueValues.size(); j++) {
							
				assertFalse(testCase.get(i) == uniqueValues.get(j));
			}
			
			//System.out.print(testCase.get(i) + " ");
			uniqueValues.add(testCase.get(i));
			
			assertTrue(1 <= testCase.get(i) && testCase.get(i) <= 10);
		}
		
		//System.out.println();
	}
	
	
/*---------------------------------------------------QUICKSORT TESTER-----------------------------------------------------*/
	
	@Test
	public void testQuickSortOnSmall () {
		
		ArrayListSorter sorter = new ArrayListSorter();
		ArrayList<Integer> testCase = sorter.generatePermuted(10);
		
		
		ArrayList<Integer> uniqueValues = new ArrayList<Integer>();
		
		for (int i = 0; i < testCase.size(); i++) {
			
			System.out.print(testCase.get(i) + " ");
			//assertEquals(i + 1, testCase.get(i));
		}
		
		System.out.println("unsorted ");
		
		
		Comparator<Integer> cmp = new Comparator<Integer>() { // Makes the comparator to make comparisons with.
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
		
	
			sorter.quicksort(testCase);
		
		for (int i = 0; i < testCase.size(); i++) {
			
			System.out.print(testCase.get(i) + " ");
			//assertEquals(i + 1, testCase.get(i));
		}
		
		
		
		System.out.println("sorted ");
	}
}
