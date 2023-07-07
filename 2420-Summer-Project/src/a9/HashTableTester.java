package a9;
/**
 * Tester class for the HashTable
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 04:03:23 CS-2420_001 SP-2023
 */

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class HashTableTester {	
	
/*--------------------------------------------------- SET UP ----------------------------------------------------------*/
	
	private HashTable<Integer, String> numberHash = new HashTable<Integer, String>();
	
	
	public void setUpSmallList() {
		
		numberHash.put(1, "one");
		numberHash.put(2, "two");
		numberHash.put(3, "three");
		numberHash.put(4, "four");
		numberHash.put(5, "five");
		numberHash.put(6, "six");
		numberHash.put(7, "seven");
		numberHash.put(8, "eight");
		numberHash.put(9, "nine");
		numberHash.put(10, "ten");
		numberHash.put(11, "eleven");
	}
	
	@Test
	public void setUpLargeList() {
		//The size of the created Hash Table
		int testSize = 1000;
		
		//Put each Integer with a key of the Integer and the
		//value as a string
		for(Integer i = 0; i < testSize; i++) {
			numberHash.put(i, i.toString());
		}
	}
	
/*--------------------------------------------- HASH CREATION TESTS ----------------------------------------------------*/	
	
	@Test
	public void putSmallListTest() {
		setUpSmallList();
		numberHash.put(12, "twelve");
		assertEquals(numberHash.containsKey(12), true);
	}
	
	@Test
	public void putLargeListTest() {
		setUpLargeList();
		numberHash.put(1001, "1001");
		assertEquals(numberHash.containsKey(1001), true);
	}
	
	@Test
	public void testTwoMatchingKeys() {
		setUpSmallList();
		
		numberHash.put(5, "hi");
		System.out.println(numberHash.get(5));
		
		assertEquals(numberHash.containsValue("hi"), true);
	}
	
	@Test
	public void sizeTest() {
		setUpSmallList();
		
		assertEquals(numberHash.size(), 11);
	}
	
	
	
	@Test
	public void sizeTestLarge() {
		setUpLargeList();
		
		assertEquals(numberHash.size(), 1000);
	}
	
	@Test
	public void TestTwoMatchingKeys() {
		setUpSmallList();
		numberHash.put(5, "hi");
		
		assertEquals(numberHash.containsKey("hi"), true);
	}
	
	
	
	@Test
	public void removeTest() {
		setUpSmallList();
		
		numberHash.remove(5);
		assertEquals(numberHash.size(), 10);
		
		List<MapEntry<Integer, String>> result = numberHash.entries();
		
		/*
		System.out.println("numberHash after removing value at key 5:");
		for(MapEntry<Integer, String> currentMap : result){
			
			System.out.println(currentMap.getValue());
		}
		*/
		
		assertEquals(result.get(4).getValue(), "six");
	}
	
	@Test
	public void removeMultipleNumbersTest() {
		setUpSmallList();
		
		numberHash.remove(5);
		numberHash.remove(2);
		assertEquals(numberHash.size(), 9);
		
		List<MapEntry<Integer, String>> result = numberHash.entries();
		
		/*
		System.out.println("numberHash after removing value at key 5:");
		for(MapEntry<Integer, String> currentMap : result){
			
			System.out.println(currentMap.getValue());
		}
		*/
		
		assertEquals(result.get(4).getValue(), "seven");
		assertEquals(result.get(2).getValue(), "four");
	}
	
	
	
	@Test
	public void isEmptyTest() {
		setUpSmallList();
		
		numberHash.clear();
		assertTrue(numberHash.isEmpty());
	}
	
	@Test
	public void clearLargeList() {
		setUpLargeList();
		
		numberHash.clear();
		assertTrue(numberHash.isEmpty());
	}
	
	@Test
	public void clearEmptyList() {
		
		numberHash.clear();
		assertTrue(numberHash.isEmpty());
	}
	
	
	
	@Test
	public void containsKeyTest() {
		setUpSmallList();
		
		for(int i = 1; i <= 11; i++) {
			
			assertTrue(numberHash.containsKey(i));
			//System.out.println("Contains key " + i);
		}
	}
	
	@Test
	public void containsKeyInLargeListTest() {
		setUpLargeList();
		
		for(int i = 1; i <= 1000; i++) {
			
			assertTrue(numberHash.containsKey(i));
			//System.out.println("Contains key " + i);
		}
	}
	
	@Test
	public void containsKeyInEmptyListTest() {
		
		assertTrue(!numberHash.containsKey(10));

	}
	
	
	
	@Test
	public void containsValueTest() {
		setUpSmallList();
		
		String[] numbers = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
		
		for(String value : numbers) {
			assertTrue(numberHash.containsValue(value));
			//System.out.println("Contains value " + value);
		}
	}
	
	@Test
	public void containsValueTestForLargeList() {
		setUpLargeList();
		
		ArrayList<String> numbers = new ArrayList<String>();
		//String[] numbers = new String[]{};
		
		for(Integer i = 0; i < numberHash.size(); i++) {
			numbers.add(i.toString());

		}
		
		for(Integer i = 0; i < numberHash.size(); i++) {
			//System.out.println("numberHash contains value: " + numberHash.get(i));

		}
		
		
		for(String value : numbers) {
<<<<<<< Updated upstream
			//System.out.println("Contains value: " + value);
=======
			//System.out.println("Contains value " + value);
>>>>>>> Stashed changes
			assertTrue(numberHash.containsValue(value));

		}
	}
	
	@Test
	public void containsValueInEmptyListTest() {
		
		assertTrue(!numberHash.containsKey(10));

	}
	
	
	@Test
	public void rehashTest() {
		
		for(Integer i = 0; i < 225; i++) { // 15*15 = 225
			
			numberHash.put(i, i.toString());
			//System.out.println("Added " + i.toString());
		}
		
		assertEquals(numberHash.compression(21), 21);
	}
	
	@Test
	public void rehashTestWithLargeList() {
		
		setUpLargeList();
		
		assertEquals(numberHash.compression(23), 23);
	}
}
