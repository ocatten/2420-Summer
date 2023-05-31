package assignment03;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.*;

/*
 * Tester for the Simple Priority Queue
 * 
 * @Author Everett Oglesby and Parker Catten
 * May 30,2023
 */
public class PriorityQueueTester {

	SimplePriorityQueue currentQueue;

	String[] mixedLetters= {"C","A","D","B","E"};
	Integer[] mixedNumbers = {7,2,5,9,8,3,10,1,4,6};
	
	ArrayList<Integer> mixedNumbersArray;
	ArrayList<Integer> largeNumberArray;

	
	public void setup() {
		currentQueue = new SimplePriorityQueue();
			
		mixedNumbersArray = new ArrayList<Integer>();
		for(Integer i : mixedNumbers) {
			mixedNumbersArray.add(i);
		}
		
		largeNumberArray = new ArrayList<Integer>();
		for(Integer i = 0; i < 250; i++) {
			largeNumberArray.add(i);

		}
	}
	
	@Test
	public void testInsertOnEmptyList() {
		setup();
		
		currentQueue.insert("A");
		assertEquals("A",currentQueue.backingArray[0]);
	}
	
	@Test
	public void testInsertOnListWithSingleItem() {
		setup();
		
		currentQueue.insert("A");
		currentQueue.insert("B");
		
		assertEquals("B",currentQueue.backingArray[1]);
	}
	
	@Test 
	public void testInsertWithSmallListOfItems() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		assertEquals("A",currentQueue.backingArray[0]);
		assertEquals("B",currentQueue.backingArray[1]);
		assertEquals("C",currentQueue.backingArray[2]);
		assertEquals("D",currentQueue.backingArray[3]);
		assertEquals("E",currentQueue.backingArray[4]);
	}
	
	@Test
	public void testInsertForMultipleItems() {
		setup();
		
		for(int i = 0; i < mixedNumbers.length; i++) {
			currentQueue.insert(mixedNumbers[i]);
		}
		for(int i = 0; i < currentQueue.size(); i++) {
			assertEquals(i+1,currentQueue.backingArray[i]);
		}
	}

	@Test
	public void testContainsOnSmallList() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		assertEquals(true,currentQueue.contains("C"));
	}

	@Test
	public void testContainsWithItemNotInTheList() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		assertEquals(false,currentQueue.contains("I"));
	}
	
	@Test
	public void testInsertAllOnEmptyArray() {
		setup();
		
		currentQueue.insertAll(mixedNumbersArray);
		
		for(int i = 0; i < currentQueue.size(); i++) {
			assertEquals(i+1,currentQueue.backingArray[i]);
		}
	}
	
	@Test
	public void testInsertAllOnArrayWithPriorItems() {
		setup();
		
		currentQueue.insert(11);
		currentQueue.insert(12);
		currentQueue.insert(13);
		
		currentQueue.insertAll(mixedNumbersArray);
		
		assertEquals(13,currentQueue.size());
	}
	
	@Test
	public void testInsertAllWithLargeList() {
		setup();
		
		currentQueue.insertAll(largeNumberArray);
		//currentQueue.insertAll(largeNumberArray);
		
		for(int i = 0; i < currentQueue.size(); i++) {
			assertEquals(i,currentQueue.backingArray[i]);
		}		
	}
	
	@Test
	public void testSize() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		assertEquals(5,currentQueue.size());
	}
	
	@Test
	public void testIsEmptyOnFullList() {
		setup();
		
		for(int i = 0; i < mixedNumbers.length; i++) {
			currentQueue.insert(mixedNumbers[i]);
		}
		assertEquals(false,currentQueue.isEmpty());
	}
	
	@Test
	public void testIsEmptyOnEmptyList() {
		setup();
		
		assertEquals(true,currentQueue.isEmpty());
	}
	
	@Test
	public void testClearOnSmallList() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		currentQueue.clear();
		assertEquals(0,currentQueue.size());
	}
	
	@Test
	public void testIsEmptyOnClearedList() {
		setup();
		
		for(int i = 0; i < mixedNumbers.length; i++) {
			currentQueue.insert(mixedNumbers[i]);
		}
		
		currentQueue.clear();
		assertEquals(true,currentQueue.isEmpty());
	}
	
	@Test
	public void testFindMax() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		assertEquals("E",currentQueue.findMax());
	}
	
	@Test
	public void testFindingTheMaxInDeleteMaxMethod() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		assertEquals("E",currentQueue.deleteMax());
	}
	
	@Test
	public void testDeleteMax() {
		setup();

		for(int i = 0; i < mixedNumbers.length; i++) {
			currentQueue.insert(mixedNumbers[i]);
		}
		currentQueue.deleteMax();
		
		assertEquals(false,currentQueue.contains(10));
	}
	
	@Test
	public void testDeletingMaxMultipleTimes() {
		setup();
		
		for(int i = 0; i < mixedNumbers.length; i++) {
			currentQueue.insert(mixedNumbers[i]);
		}
		currentQueue.deleteMax();
		currentQueue.deleteMax();
		currentQueue.deleteMax();
		
		assertEquals(7,currentQueue.findMax());
	}
	
	@Test
	public void multipleOperationsMixed() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			currentQueue.insert(mixedLetters[i]);
		}
		
		currentQueue.insert("J");
		currentQueue.insert("M");
		currentQueue.insert("H");
		
		currentQueue.deleteMax();
		assertEquals(7,currentQueue.size());
		assertEquals("J",currentQueue.findMax());
	}
}
