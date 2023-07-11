package assignment09;

/**
 * StudentBadHash, MediumHash, and GoodHash tests
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:09:23 CS-2420_001 SUM-2023
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StudentHashTester {	
	
	
/*====================================================== CONSTRUCTOR TESTS ===================================================================*/
	
	
	// Fields
	private HashTable<Integer, StudentBadHash> badStudentHash = new HashTable<Integer, StudentBadHash>();
	private HashTable<Integer, StudentMediumHash> mediumStudentHash = new HashTable<Integer, StudentMediumHash>();
	private HashTable<Integer, StudentGoodHash> goodStudentHash = new HashTable<Integer, StudentGoodHash>();
	
	StudentBadHash badParker;
	StudentBadHash badEverett;
	
	StudentMediumHash mediumParker;
	StudentMediumHash mediumEverett;
	
	StudentGoodHash goodParker;
	StudentGoodHash goodEverett;
	
	
	public void setUp() {
		
		badParker = new StudentBadHash(580588, "Parker", "Catten");
		badEverett = new StudentBadHash( 000001, "Everett", "Oglesby");
		
		mediumParker = new StudentMediumHash(580588, "Parker", "Catten");
		mediumEverett = new StudentMediumHash( 000001, "Everett", "Oglesby");
		
		goodParker = new StudentGoodHash(580588, "Parker", "Catten");
		goodEverett = new StudentGoodHash( 000001, "Everett", "Oglesby");
		
		
		badStudentHash.put(badParker.hashCode(), badParker);
		badStudentHash.put(badEverett.hashCode(), badEverett);
		
		mediumStudentHash.put(mediumParker.hashCode(), mediumParker);
		mediumStudentHash.put(mediumEverett.hashCode(), mediumEverett);
		
		goodStudentHash.put(goodParker.hashCode(), goodParker);
		goodStudentHash.put(goodEverett.hashCode(), goodEverett);
		
	}
	
	
/*---------------------------------------------------------------------------------------------------------------------------------------------------
 * Most methods will only be tested with the StudentBadHash class, as each other version is copied from the StudentBadHash
 *--------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
/*============================================================ STUDENT_HASH TESTS ==================================================================*/
	
	
	@Test
	public void getUidTest() {
		setUp();
		
		assertEquals(580588, badParker.getUid());
		assertEquals(000001, badEverett.getUid());
	}
	
	
	
	@Test
	public void getFirstNamesTest() {
		setUp();
		
		assertEquals("Parker", badParker.getFirstName());
		assertEquals("Everett", badEverett.getFirstName());
	}
	
	
	
	@Test
	public void getLastNamesTest() {
		setUp();
		
		assertEquals("Catten", badParker.getLastName());
		assertEquals("Oglesby", badEverett.getLastName());
	}
	
	
	
	@Test
	public void equalsTest() {
		setUp();
		
		StudentBadHash badParkerEquals =  new StudentBadHash(580588, "Parker", "Catten");
		StudentBadHash badEverettEquals =  new StudentBadHash(000001, "Everett", "Oglesby");
		
		
		assertTrue(badParker.equals(badParker));
		assertTrue(badParker.equals(badParkerEquals));
		assertFalse(badParker.equals(badEverett));
		assertFalse(badParker.equals(mediumParker));
		assertFalse(badParker.equals(goodParker));
		
		assertTrue(badEverett.equals(badEverett));
		assertTrue(badEverett.equals(badEverettEquals));
		assertFalse(badEverett.equals(badParker));
		assertFalse(badEverett.equals(mediumEverett));
		assertFalse(badEverett.equals(goodEverett));
		
	}
	
	
	
	@Test
	public void toStringTest() {
		setUp();
		
		StudentBadHash badParkerEquals =  new StudentBadHash(580588, "Parker", "Catten");
		StudentBadHash badEverettEquals =  new StudentBadHash(000001, "Everett", "Oglesby");
		
		
		assertEquals(badParker.toString(), badParkerEquals.toString());
		assertEquals(badParker.toString(), mediumParker.toString());
		assertEquals(badParker.toString(), goodParker.toString());
		assertFalse( badParker.toString().equals( badEverett.toString() ));
		
		assertEquals(badEverett.toString(), badEverettEquals.toString());
		assertEquals(badEverett.toString(), mediumEverett.toString());
		assertEquals(badEverett.toString(), goodEverett.toString());
		assertFalse( badEverett.toString().equals( badParker.toString() ));
		
	}
	
	
/*======================================================== HASH_CODE TESTS ========================================================================*/
	
	
	@Test
	public void badHashTest() {
		setUp();
		
		StudentBadHash badParkerEquals =  new StudentBadHash(580588, "Parker", "Catten");
		StudentBadHash badEverettEquals =  new StudentBadHash(000001, "Everett", "Oglesby");
		
		assertEquals(badParker.hashCode(), badParkerEquals.hashCode());
		assertEquals(badEverett.hashCode(), badEverettEquals.hashCode());
		assertFalse(badEverett.hashCode() == badParker.hashCode());
		
	}	
	
	
	
	@Test
	public void mediumHashTest() {
		setUp();
		
		StudentMediumHash mediumParkerEquals =  new StudentMediumHash(580588, "Parker", "Catten");
		StudentMediumHash mediumEverettEquals =  new StudentMediumHash(000001, "Everett", "Oglesby");
		
		assertEquals(mediumParker.hashCode(), mediumParkerEquals.hashCode());
		assertEquals(mediumEverett.hashCode(), mediumEverettEquals.hashCode());
		assertFalse(mediumEverett.hashCode() == mediumParker.hashCode());
		
	}	
	
	
	
	@Test
	public void goodHashTest() {
		setUp();
		
		StudentGoodHash goodParkerEquals =  new StudentGoodHash(580588, "Parker", "Catten");
		StudentGoodHash goodEverettEquals =  new StudentGoodHash(000001, "Everett", "Oglesby");
		
		assertEquals(goodParker.hashCode(), goodParkerEquals.hashCode());
		assertEquals(goodEverett.hashCode(), goodEverettEquals.hashCode());
		assertFalse(goodEverett.hashCode() == goodParker.hashCode());
		
	}	
	
}
