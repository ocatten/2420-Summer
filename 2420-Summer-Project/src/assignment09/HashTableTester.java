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
	
/*====================================================== CONSTRUCTOR TESTS ===================================================================*/
	
	private HashTable<Integer, String> numHash = new HashTable<Integer, String>();
	private HashTable<Integer, String> numHashLarge = new HashTable<Integer,String>();
	
	
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
			numHashLarge.put(i, i.toString());
		}
	}
	
	
/*========================================================== COMPRESSION TEST =====================================================================*/
	
	
	// This is tested further in the put tests
	@Test
	public void compressionTest() {
		
		assertEquals(2, numHash.compression(12));
	}
	
	
/*=========================================================== REHASH TESTS =======================================================================*/
	
	@Test
	public void rehashOnEmptyTest() {
		
		assertEquals(10, numHash.getCapacity());
		
		try {
			
			numHash.rehash();
			assertTrue(false);
			
		} catch(Exception e) {
			
			assertTrue(true);
		}
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
		assertEquals(197, numHashLarge.getCapacity());
//		
//		for(Integer i = 12; i < 101; i++) {
//			numHashLarge.put(i, i.toString());
//		}
//		
//		assertEquals(23, numHashLarge.getCapacity());
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
