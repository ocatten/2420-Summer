package assignment04;
/**
 * Tester class for LargestNumberSolver 
 * 
 * @author: Parker Catten @u0580588 & Everett Oglesby
 * @version 06:08:23 CS-2420_SP-2023
 */

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


public class LargestNumberSolverTester {

//--------------------------------------------INSERTION SORT TESTS -------------------------------------------------
	@Test
	public <T> void testInsertionShortOnSmall () {
		
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		Integer[] testCase = new Integer[] {1, 4, 2, 3};
		Integer[] expectedCase = new Integer[] {1, 2, 3, 4};
		// Sets up the comparator
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
		
		numberSolver.insertionSort(testCase, cmp);
		
		assertEquals(testCase[0], expectedCase[0]);
		assertEquals(testCase[1], expectedCase[1]);
		assertEquals(testCase[2], expectedCase[2]);
		assertEquals(testCase[3], expectedCase[3]);
	}
	
	
	
	@Test
	public <T> void testInsertionSortOnLarge () {
		
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		// Sets up the comparator
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
			
		Integer[] testCase = new Integer[10000];
		Random rand = new Random();
		
		for(int i = 0; i < testCase.length; i++) {
			testCase[i] = rand.nextInt();
		}
		
		numberSolver.insertionSort(testCase, cmp); 
		
		for(int i = 0; i < testCase.length - 1; i++) {
			equals(testCase[i] < testCase[i+1]);
		}
	}
	
	

//--------------------------------------------FIND LARGEST NUMBER TESTS -------------------------------------------------
	
	@Test
	public <T> void testFindLargestNumberOnEmpty() {
		
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		// Sets up the comparator
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
			
			
		Integer[] testCase = new Integer[] {};
		BigInteger expectedCase = new BigInteger("0");
		
		assertEquals(numberSolver.findLargestNumber(testCase), expectedCase);
	}
	
	
	
	@Test
	public <T> void testFindLargestNumberOnSmall() {
		
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		// Sets up the comparator
		Comparator<Integer> cmp = new Comparator<Integer>() { 
			public int compare(Integer e1, Integer e2) { return e1.compareTo(e2); } };
			
			
		Integer[] testCase = new Integer[] {1, 2, 3, 4, 12, 24};
		BigInteger expectedCase = new BigInteger("4321");
		
<<<<<<< Updated upstream
		//System.out.println("should be 4321 = " + numberSolver.findLargestNumber(testCase));
=======
		System.out.println("should be 43224112 = " + numberSolver.findLargestNumber(testCase));
>>>>>>> Stashed changes
		
 	}
	
	
	
//-----------------------------------------FIND LARGEST INT TESTS----------------------------------------------
	
	@Test
	public <T> void testFindLargestIntOnEmpty() {
		
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {1, 2, 3, 4};
		
		numberSolver.findLargestInt(testCase);
	}
	
	
	
	@Test
	public <T> void testFindLargestIntOnLarge() {
		
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
		
		try { // We expect this test to fail.
			numberSolver.findLargestInt(testCase); 
		} catch(Exception e) {
			System.out.println("findLargestIntOnLarge test found that the BigInteger is too large for an integer.");
		}
	}
	
	
	
	@Test
	public <T> void testFindLargestIntOnSmall() {
		
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {9};
		
		try {
			numberSolver.findLargestInt(testCase); 
			assertEquals(numberSolver.findLargestInt(testCase), 9);
		} catch(Exception e) {
			System.out.println("findLargestIntOnSmall test found that the BigInteger is too large for an integer.");
		}
	}
	
	
//-----------------------------------------FIND LARGEST INT TESTS----------------------------------------------
	
	@Test
	public <T> void testFindLargestLongOnEmpty() {
			
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {1, 2, 3, 4};
			
		numberSolver.findLargestLong(testCase);
	}
		
		
		
	@Test
	public <T> void testFindLargestLongOnLarge() { 
			
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 
											7, 9, 9, 9, 9, 9, 9, 9, 9, 9};
			
		try { // We expect this test to fail.
			numberSolver.findLargestInt(testCase); 
		} catch(Exception e) {
			System.out.println("findLargestLongOnLarge test found that the BigInteger is too large for a long.");
		}
	}
		
		
		
	@Test
	public <T> void testFindLargestLongOnSmall() {
			
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		Integer[] testCase = new Integer[] {9};
			
		try {
			numberSolver.findLargestInt(testCase); 
			assertEquals(numberSolver.findLargestInt(testCase), 9);
		} catch(Exception e) {
			System.out.println("findLargestLongOnSmall test found that the BigInteger is too large for a long.");
		}
	}
	
	
//-----------------------------------------FIND LARGEST INT TESTS----------------------------------------------
	
	@Test
	public <T> void testSumOnSmall() {
			
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		List<Integer[]> testCase = new ArrayList<Integer[]>();
		testCase.add(new Integer[] {1});
		testCase.add(new Integer[] {1, 2});
		
		assertEquals( numberSolver.sum(testCase), BigInteger.valueOf(22) );
	}
		
		
		
	@Test
	public <T> void testSumOnLarge() {
			
		LargestNumberSolver numberSolver  = new LargestNumberSolver();
		
		List<Integer[]> testCase = new ArrayList<Integer[]>();
		testCase.add(new Integer[] {1});
		testCase.add(new Integer[] {1, 1});
		testCase.add(new Integer[] {1, 2});
		testCase.add(new Integer[] {1, 3});
		testCase.add(new Integer[] {1, 4});
		testCase.add(new Integer[] {1, 5});
		testCase.add(new Integer[] {1, 6});
		testCase.add(new Integer[] {1, 7});
		testCase.add(new Integer[] {1, 8});
		testCase.add(new Integer[] {1, 9});
		testCase.add(new Integer[] {1, 1, 2});
		testCase.add(new Integer[] {1, 2, 2});
		testCase.add(new Integer[] {1, 3, 2});
		testCase.add(new Integer[] {1, 4, 2});
		testCase.add(new Integer[] {1, 5, 2});
		testCase.add(new Integer[] {1, 6, 2});
		testCase.add(new Integer[] {1, 7, 2});
		testCase.add(new Integer[] {1, 8, 2});
		testCase.add(new Integer[] {1, 9, 2});
		
		// 921 + 821 + 721 + 621+ 521 + 421 + 321 + 221 + 211 + 91 + 81 + 71 + 61 + 51 + 41 + 31 + 21 + 11 + 1 = 5239
		
		assertEquals( numberSolver.sum(testCase), new BigInteger("5239") );
	}
		
		
		
	@Test
	public <T> void testSumOnEmpty() {
			
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		List<Integer[]> testCase = new ArrayList<Integer[]>();
		
		assertEquals(numberSolver.sum(testCase), BigInteger.valueOf(0));
	}
	
	
//--------------------------------------------READ FILE TESTS------------------------------------------------
	
	@Test
	public <T> void testReadFileOnEmpty() {
		
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		
		try {
			
			List<Integer[]> contents = numberSolver.readFile("integers.txt");
			
		} catch(Exception e) {
			assertTrue(true == true);
		}
	}
	
	
	
	@Test
	public <T> void testReadFileOnIntegers() {
		
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		List<Integer[]> contents = new ArrayList<Integer[]>();
		
		try {
			
			contents = numberSolver.readFile("integers.txt");
			
		} catch(Exception e) {
			assertTrue(true != true);
		}
		
		for(Integer[] line : contents) {
			
			//System.out.print("Line " + contents.indexOf(line) + ": ");
			for(int i = 0; i < line.length; i++) {
				
				//System.out.print(line[i] + " ");
			}
			
			//System.out.println();
		}
		
	}
	
	
	@Test
	public <T> void testFindKthLargest() {
		Random rng = new Random();
		
		int arraySize = 10;
		int kNum = rng.nextInt(9) + 1;
		
		LargestNumberSolver numberSolver = new LargestNumberSolver();
		List<Integer[]> givenList = new ArrayList<Integer[]>();
		System.out.println("running");
		
		for(int i = 0; i < 15; i++) {
			
			//Random rng = new Random();
			Integer[] givenArray = new Integer[arraySize];
			for (int j = 0; j < arraySize; j++) {
				givenArray[j] = rng.nextInt(9) + 1;
			}
			givenList.add(givenArray);
		}

		Integer[] largestList = numberSolver.findKthLargest(givenList, kNum);
		//System.out.println(largestList[0].toString());
		
		for(int i = 0; i < givenList.size(); i++) {
			
		}
	}
}