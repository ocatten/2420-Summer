package assignment09;

/**
 * HashTable tests
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:07:23 CS-2420_001 SUM-2023
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HashTableTester {	
	
/*====================================================== CONSTRUCTOR TESTS ===================================================================*/
	
	private HashTable<Integer, String> numHash = new HashTable<Integer, String>();
	private HashTable<Integer, String> largeNumHash = new HashTable<Integer,String>();
	
	
	public void smallTableSetUp() {
		
		numHash.put(1, "one");
		numHash.put(2, "two");
		numHash.put(3, "three");
		numHash.put(4, "four");
		numHash.put(5, "five");
		numHash.put(6, "six");
		numHash.put(7, "seven");
		numHash.put(8, "eight");
		numHash.put(9, "nine");
		numHash.put(10, "ten");
		numHash.put(11, "eleven");
	}
	
	
	
	public void largeTableSetUp() {
		
		int testSize = 1000;
		
		for(Integer i = 0; i < testSize; i++) {
			largeNumHash.put(i, i.toString());
		}
	}
	
	
/*========================================================== COMPRESSION TEST =====================================================================*/
	
	
	@Test
	public void compressionTest() { // This is tested further in the put tests
		
		assertEquals(2, numHash.compression(12));
	}
	
	
/*=========================================================== REHASH TESTS =======================================================================*/
	
	
	@Test
	public void rehashOnEmptyTest() {
		
		assertEquals(10, numHash.getCapacity());
		numHash.rehash();
		assertEquals(23, numHash.getCapacity());
		
	}
	
	
	
	@Test
	public void rehashOnSmallTest() {
		
		smallTableSetUp();
		assertEquals(10, numHash.getCapacity());
		
		for(Integer i = 12; i < 101; i++) {
			numHash.put(i, i.toString());
		}
		
		assertEquals(23, numHash.getCapacity());
	}
	
	
	
	@Test
	public void rehashOnLargeTest() {
		
		largeTableSetUp();
		
		assertEquals(197, largeNumHash.getCapacity());
		assertEquals(1000, largeNumHash.size());
		
		for(Integer i = 1000; i < 1970; i++) {
			largeNumHash.put(i, i.toString());
		}
		
		assertEquals(1970, largeNumHash.size());
		assertEquals(397, largeNumHash.getCapacity());
	}
	

/*=========================================================== CLEAR TESTS =======================================================================*/
	
	
	@Test
	public void clearOnEmptyTest() {
		
		assertEquals(0, numHash.size());
		numHash.clear();
		assertEquals(0, numHash.size());
	}
	
	
	
	@Test
	public void clearOnSmallTest() {
		
		smallTableSetUp();
		assertEquals(11, numHash.size());
		numHash.clear();
		assertEquals(0, numHash.size());
	}
	
	
	
	@Test
	public void clearOnLargeTest() {
		
		largeTableSetUp();
		assertEquals(1000, largeNumHash.size());
		largeNumHash.clear();
		assertEquals(0, largeNumHash.size());
	}
	
	
/*======================================================= CONTAINS_KEY TESTS =======================================================================*/
	
	
	@Test
	public void containsKeyOnEmptyTest() {
		
		assertFalse(numHash.containsKey(1));
	}
	
	
	
	@Test
	public void containsKeyOnSmallTest() {
		
		smallTableSetUp();
		
		for(int i = 1; i < 11; i++) {
			assertTrue(numHash.containsKey(i));
		}
		
		assertFalse(numHash.containsKey(12));
	}
	
	
	
	@Test
	public void containsKeyOnLargeTest() {
		
		largeTableSetUp();
		
		for(int i = 0; i < 1000; i++) {
			assertTrue(largeNumHash.containsKey(i));
		}
		
		assertFalse(largeNumHash.containsKey(1000));
	}
	
	
/*==================================================== CONTAINS_VALUE TESTS =======================================================================*/
	
	
	@Test
	public void containsValueOnEmptyTest() {
		
		assertFalse(numHash.containsValue("1"));
	}
	
	
	
	@Test
	public void containValueOnSmallTest() {
		
		smallTableSetUp();
		
		assertTrue(numHash.containsValue("one"));
		assertTrue(numHash.containsValue("two"));
		assertTrue(numHash.containsValue("three"));
		assertTrue(numHash.containsValue("four"));
		assertTrue(numHash.containsValue("five"));
		assertTrue(numHash.containsValue("six"));
		assertTrue(numHash.containsValue("seven"));
		assertTrue(numHash.containsValue("eight"));
		assertTrue(numHash.containsValue("nine"));
		assertTrue(numHash.containsValue("ten"));
		assertTrue(numHash.containsValue("eleven"));
		
		assertFalse(numHash.containsValue("twelve"));
		assertFalse(numHash.containsValue("1"));
	}
	
	
	
	@Test
	public void containsValueOnLargeTest() {
		
		largeTableSetUp();
		
		for(Integer i = 0; i < 1000; i++) {
			assertTrue(largeNumHash.containsValue(i.toString()));
		}
		
		assertFalse(largeNumHash.containsValue("1000"));
	}
	
	
	
/*=========================================================== PUT TESTS ===========================================================================*/
	
	
	@Test
	public void putOnEmptyTest() {
		
		numHash.put(12, "twelve");
		assertEquals(numHash.containsKey(12), true);
	}
	
	
	
	@Test
	public void putOnSmallTest() {
		
		smallTableSetUp();
		numHash.put(12, "twelve");
		assertEquals(numHash.containsKey(12), true);
	}
	
	
	
	@Test
	public void putOnLargeTest() {
		
		largeTableSetUp();
		numHash.put(1001, "1001");
		assertEquals(numHash.containsKey(1001), true);
	}
	
	
/*=========================================================== REHASH TESTS ====================================================================*/
	
}
