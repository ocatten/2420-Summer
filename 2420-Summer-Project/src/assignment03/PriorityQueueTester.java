package assignment03;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class PriorityQueueTester {

	SimplePriorityQueue emptyQueue;
	SimplePriorityQueue smallQueue;
	String[] mixedLetters= {"C","A","D","B","E"};
	String[] mixedNumbers = {"H","B","D","E","A","G","C","F"};
	
	public void setup() {
		emptyQueue = new SimplePriorityQueue();
		smallQueue = new SimplePriorityQueue();
		
			
	}
	
	@Test
	public void testInsertOnEmptyList() {
		setup();
		
		emptyQueue.insert("A");
		assertEquals("A",emptyQueue.backingArray[0]);
	}
	
	@Test
	public void testInsertOnListWithSingleItem() {
		setup();
		
		emptyQueue.insert("A");
		emptyQueue.insert("B");
		
		assertEquals("B",emptyQueue.backingArray[1]);
	}
	
	@Test 
	public void testInsertWithSmallListOfItems() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			emptyQueue.insert(mixedLetters[i]);
		}
		assertEquals("A",emptyQueue.backingArray[0]);
		assertEquals("B",emptyQueue.backingArray[1]);
		assertEquals("C",emptyQueue.backingArray[2]);
		assertEquals("D",emptyQueue.backingArray[3]);
		assertEquals("E",emptyQueue.backingArray[4]);
	}
	
	@Test
	public void testInsertForMultipleItems() {
		setup();
		
		for(int i = 0; i < mixedLetters.length; i++) {
			emptyQueue.insert(mixedLetters[i]);
		}
	}

	@Test
	public void testBinarySearchOnSmallList() {
		setup();
		
		for(int i = 0; i < 10; i++) {
			//assertEquals(i,smallQueue.backingArray[i]);
		}
	}

	@Test
	public void testBinarySearch() {
		
	}
}
