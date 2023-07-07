package a9;
/**
 * Tester class for the StudentBad/Medium/GoodHash classes
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 04:03:23 CS-2420_001 SP-2023
 */

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


public class StudentHashTester {	
	
	
/*--------------------------------------------------- SET UP ----------------------------------------------------------*/
	
	
	private StudentBadHash badHasherOne = new StudentBadHash(580588, "First", "Last");
	private StudentBadHash badHasherTwo = new StudentBadHash(779085, "First", "Last");
	private StudentMediumHash mediumHasherOne = new StudentMediumHash(580588, "Parker", "Catten");
	private StudentMediumHash mediumHasherTwo = new StudentMediumHash(779085, "Everett", "Oglesby");
	private StudentGoodHash goodHasherOne = new StudentGoodHash(580588, "Parker", "Catten");
	private StudentGoodHash goodHasherTwo = new StudentGoodHash(779085, "Everett", "Oglesby");
	
	HashTable<StudentBadHash, String> badHashTable = new HashTable<StudentBadHash, String>();
	HashTable<StudentMediumHash, String> mediumHashTable = new HashTable<StudentMediumHash, String>();
	HashTable<StudentGoodHash, String> goodHashTable = new HashTable<StudentGoodHash, String>();
	
	public void setUp() {
		
		badHashTable.put(badHasherOne, "pcatten");
		badHashTable.put(badHasherTwo, "eoglesby");
		
		mediumHashTable.put(mediumHasherOne, "pcatten");
		mediumHashTable.put(mediumHasherTwo, "eoglesby");
		
		goodHashTable.put(goodHasherOne, "pcatten");
		goodHashTable.put(goodHasherTwo, "eoglesby");
	}
	
	
/*------------------------------------------------ HASH TESTS ---------------------------------------------------------*/
	
	
	@Test
	public void testBadHash() {
		setUp();
		
		assertTrue(badHashTable.containsValue("pcatten"));
		assertTrue(badHashTable.containsKey( badHashTable.compression(badHasherOne.hashCode())) );
		
		assertTrue(badHashTable.containsValue("eoglesby"));
		assertTrue(badHashTable.containsKey( badHashTable.compression(badHasherTwo.hashCode())) );
	}
	
	
	
	@Test
	public void testMediumHash() {
		setUp();
		
		assertTrue(mediumHashTable.containsValue("pcatten"));
		assertTrue(mediumHashTable.containsKey( mediumHashTable.compression(mediumHasherOne.hashCode())) );
		
		assertTrue(mediumHashTable.containsValue("eoglesby"));
		assertTrue(mediumHashTable.containsKey( mediumHashTable.compression(mediumHasherOne.hashCode())) );
	}
	
	
	
	@Test
	public void testGoodHash() {
		setUp();
		
		assertTrue(goodHashTable.containsValue("pcatten"));
		assertTrue(goodHashTable.containsKey( goodHashTable.compression(goodHasherOne.hashCode())) );
		
		assertTrue(goodHashTable.containsValue("eoglesby"));
		assertTrue(goodHashTable.containsKey( goodHashTable.compression(goodHasherOne.hashCode())) );
	}
}