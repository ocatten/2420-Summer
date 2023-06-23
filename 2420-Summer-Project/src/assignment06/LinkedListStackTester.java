/**
 * Tester class for the LinkedListStack
 *  
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:22:23 CS-2420_001 SUM-2023
 */
package assignment06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

import assignment06.SinglyLinkedList.Node;

public class LinkedListStackTester {
	
	// Fields
	LinkedListStack<Integer> linkedList;
	
	
	public void emptyLinkedListSetUp() {
		linkedList = new LinkedListStack<Integer>();
	}
	
	
	
	public void smallLinkedListSetUp() {
		
		linkedList = new LinkedListStack<Integer>();
		linkedList.push(1);
		linkedList.push(2);
		linkedList.push(3);
		linkedList.push(4);
		linkedList.push(5);
	}
	
	
	
	public void largeLinkedListSetUp() {
		
		linkedList = new LinkedListStack<Integer>();
		
		for(int i = 0; i < 1000; i++) {
			
			linkedList.push(i);
		}
	}
	
/*----------------------------------------------LINKED LIST CONSTRUCTORS-----------------------------------------------*/
	
	@Test
	public void testEmptyLinkedListConstructor(){
		
		try {
			 
			emptyLinkedListSetUp();
			assertEquals(0, 0);
			
		} catch (Exception e) {
			
			System.out.println("Empty LinkedListStack constrctor failed: " + e);
			assertEquals(0, 1);
		}
	}
	
	
	
	@Test
	public void testSmallLinkedListConstructor() {
		
		try {
			
			smallLinkedListSetUp();
			assertEquals(0, 0);
			
		} catch (Exception e) {
			
			System.out.println("Small LinkedListStack constructor failed: " + e);
			assertEquals(0, 1);
		}
	}
	
	
	
	@Test
	public void testLargeLinkedListConstructor() {
		
		try {
			
			largeLinkedListSetUp();
			assertEquals(0, 0);
			
		} catch (Exception e) {
			
			System.out.println("Large LinkedList constructor failed: " + e);
			assertEquals(0,1);
		}
	}
	
/*----------------------------------------------------SIZE TEST------------------------------------------------------------*/
	
	@Test
	public void emptyLinkedListSizeTest () {
		
		emptyLinkedListSetUp();
		assertEquals(linkedList.size(), 0);
	}
	
	
	
	@Test
	public void smallLinkedListNodesSizeTest () {
		
		smallLinkedListSetUp();
		assertEquals(linkedList.size(), 5);
	}
	
	
	
	@Test
	public void largeLinkedListNodesSizeTest () {
		
		largeLinkedListSetUp();
		assertEquals(linkedList.size(), 1000);
	}
	
/*---------------------------------------------LINKEDLIST NODES PEEK TEST--------------------------------------------------*/
	
	@Test
	public void emptyLinkedListNodesPeekTest () {
		
		try {
			
			linkedList.peek();
			assertEquals(0, 1);
			
		} catch (Exception e) {
			
			assertEquals(0, 0);
		}
	}
	
	
	
	@Test
	public void smallLinkedListNodesPeekTest () {
		
		Integer intgr = 5;
		smallLinkedListSetUp();
		
		assertEquals(linkedList.peek(), intgr);
	}
	
	
	
	@Test
	public void largeLinkedListNodesPeekTest () {
		
		Integer intgr = 999;
		largeLinkedListSetUp();
		
		assertEquals(linkedList.peek(), intgr);
	}
	
/*---------------------------------------CLEAR AND IsEMPTY LINKEDLIST TESTS--------------------------------------------*/
	
	@Test
	public void emptyLinkedListIsClearTest () {
		
		emptyLinkedListSetUp();
		assertTrue(linkedList.isEmpty());
		
		try {
			
			linkedList.clear();
			assertTrue(true);
		
		} catch (Exception e) {
			
			System.out.println("Clear on empty failed: " + e);
			assertTrue(false);
		}
	}
	
	
	
	@Test
	public void smallLinkedListIsClearTest () {
		
		smallLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
			
		linkedList.clear();
		
		assertTrue(linkedList.isEmpty());
		assertEquals(linkedList.size(), 0);
		
		try {
			
			linkedList.peek();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}	
	
	
	
	@Test
	public void largeLinkedListIsClearTest () {
		
		largeLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
			
		linkedList.clear();
		
		assertTrue(linkedList.isEmpty());
		assertEquals(linkedList.size(), 0);
		
		try {
			
			linkedList.peek();
			assertTrue(false);
			
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	

/*----------------------------------------------------PUSH TESTS-------------------------------------------------------*/
	
	@Test
	public void emptyLinkedListPushTest () {
		
		emptyLinkedListSetUp();
		assertTrue(linkedList.isEmpty());
		
		try {
			
			linkedList.peek();
			assertTrue(false);
		
		} catch (Exception e) {
			
			assertTrue(true);
		}
		
		linkedList.push(1);
		Integer intgr = 1;
		
		assertEquals(linkedList.peek(), intgr);
		assertEquals(linkedList.size(), 1);
	}
	
	
	
	@Test
	public void smallLinkedListPushTest () {
		
		Integer intgr = 5;
		Integer newIntgr = 6;
		
		smallLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
		
		assertEquals(linkedList.size(), 5);
		assertEquals(linkedList.peek(), intgr);
		
		linkedList.push(newIntgr);
		assertEquals(linkedList.peek(), newIntgr);
		assertEquals(linkedList.size(), 6);
	}	
	
	
	
	@Test
	public void largeLinkedListPushTest () {
		
		Integer intgr = 999;
		Integer newIntgr = 1000;
		
		largeLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
		
		assertEquals(linkedList.size(), 1000);
		assertEquals(linkedList.peek(), intgr);
		
		linkedList.push(newIntgr);
		assertEquals(linkedList.peek(), newIntgr);
		assertEquals(linkedList.size(), 1001);
	}
	
/*------------------------------------------------------POP TEST------------------------------------------------------*/
	
	@Test
	public void emptyLinkedListPopTest () {
		
		emptyLinkedListSetUp();
		assertTrue(linkedList.isEmpty());
		
		try {
			
			linkedList.pop();
			assertTrue(false);
		
		} catch (Exception e) {
			
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void smallLinkedListPopTest () {
		
		Integer intgr = 5;
		Integer newIntgr = 4;
		
		smallLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
		
		assertEquals(linkedList.size(), 5);
		assertEquals(linkedList.peek(), intgr);
		
		assertEquals(linkedList.pop(), intgr);
		assertEquals(linkedList.peek(), newIntgr);
		assertEquals(linkedList.size(), 4);
	}	
	
	
	
	@Test
	public void largeLinkedListPopTest () {
		
		Integer intgr = 999;
		Integer newIntgr = 998;
		
		largeLinkedListSetUp();
		assertFalse(linkedList.isEmpty());
		
		assertEquals(linkedList.size(), 1000);
		assertEquals(linkedList.peek(), intgr);
		
		assertEquals(linkedList.pop(), intgr);
		assertEquals(linkedList.peek(), newIntgr);
		assertEquals(linkedList.size(), 999);
	}	
	
/*----------------------------------------------------GETLIST TESTS--------------------------------------------------*/
	
	@Test
	public void emptyGetListTest () {
		
		emptyLinkedListSetUp();
		SinglyLinkedList<Integer> test = linkedList.getList();
		
		assertEquals(test.head, null);
		assertEquals(test.tail, null);
		assertEquals(test.size(), 0);
	}
	
	
	
	@Test
	public void smallGetListTest () {
		
		smallLinkedListSetUp();
		SinglyLinkedList<Integer> test = linkedList.getList();
		
		assertEquals(test.head.data, new Integer(5));
		assertEquals(test.tail.data, new Integer(1));
		assertEquals(test.size(), 6);
	}
	
	
	
	@Test
	public void largeGetListTest () {
		
		largeLinkedListSetUp();
		SinglyLinkedList<Integer> test = linkedList.getList();
		
		assertEquals(test.head.data, new Integer(999));
		assertEquals(test.tail.data, new Integer(0));
		assertEquals(test.size(), 1001);
	}
	
}
