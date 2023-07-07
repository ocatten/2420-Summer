package assignment09;

/**
 * HashTable tests
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:07:23 CS-2420_001 SUM-2023
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HashTableTester {	
	
/*=================================================================================================================================================*/
	
	private HashTable<Integer, String> numHash = new HashTable<Integer, String>();
	
	
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
			numHash.put(i, i.toString());
		}
	}
	
	
/*============================================================== PUT TESTS =========================================================================*/
	
	
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
}
