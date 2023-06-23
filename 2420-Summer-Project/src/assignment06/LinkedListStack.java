/**
 * LinkedList using a series of Nodes
 * 
 * @author: Parker Catten @u0580588 & Everett Oglesby
 * @version: 06:21:23 CS-2420_001 SUM-2023
 */
package assignment06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
	
	// Fields
	private SinglyLinkedList<E> singlyLinkedList;
	private int size;
	private E top;
	
	
	/**
	 * Constructor
	 */
	public LinkedListStack() {
		// Sets-up fields
		singlyLinkedList = new SinglyLinkedList<E>();
	}
	
	
	
	/**
	 * Class for each node of the linkedList. Uses the generic type E, and has a head/tail node for directionality.
	 */
	class Node {
		
		// Sets up the relevant fields for the class.
		E data;
		Node next;
	
		
		/**
		 * Different constructor with a single parameter to determine what the Node is doing.
		 * 
		 * @param data: Generic data shared with the linkedList class for the nodes
		 */
		public Node(E data) {
		
			this.data = data;
			this.next = null;
		}
	}
	
	
	@Override
	/**
	 * Removes all of the elements from the stack.
	 */
	public void clear() {
		singlyLinkedList.clear(); // Uses already written methods
		size = 0; // Increments the size to keep the O behavior for size lower.
	}

	
	
	@Override
	/**
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return singlyLinkedList.isEmpty(); // Uses isEmpty to determine the status of the stack
	}

	
	
	@Override
	/**
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack has no elements
	 */
	public E peek() throws NoSuchElementException {
		
		if(top != null) { // Checks if the stack has an element
			//return (E)singlyLinkedList.getFirst();
			return singlyLinkedList.getFirst();
		} else { // If it doesn't, throw the exception
			throw new NoSuchElementException();
		}
	}
	
	
	
	@Override
	/**
	 * Same as the peek() method but also removes the top element of the stack
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack has no elements
	 */
	public E pop() throws NoSuchElementException {
		
		if(top != null) { // Checks if the stack has an element
			size--; // Increments the size to keep the O behavior for size lower.
			return (E)singlyLinkedList.deleteFirst();
		} else { // If it doesn't, throw the exception
			throw new NoSuchElementException();
		}
	}
	
	
	
	@Override
	/**
	 * Adds a given element to the top of the stack
	 * 
	 * @param element: Generic object to add
	 */
	public void push(E element) {
		
		singlyLinkedList.insertFirst(element); // Uses existing method
		top = element;
		size++; // Increments the size to keep the O behavior for size lower.
	}
	
	
	
	@Override
	/**
	 * @return the number of elements in the stack
	 */
	public int size() {
		return size;
	}
	
	
	
	/**
	 * @return the linkedList
	 */
	public SinglyLinkedList<E> getList() {
		return singlyLinkedList;
	}

}
