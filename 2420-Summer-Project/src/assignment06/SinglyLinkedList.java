/**
 * This class will represent the stack of URL's the program will navigate through with a singly linked list to make
 * comparisons against a simple ArrayList. It's a list, so it therefore implements the list interface.
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:22:23 CS-2420_001 SUM-2023
 */
package assignment06;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.NoSuchElementException;

import assignment06.List;

public class SinglyLinkedList<E> implements List<E> {
	
	// Fields
	public Node head = null;
	public Node tail = null;
	public int listSize = 0;
	
	
	/**
	 * No param constructor
	 */
	// public SinglyLinkedList () {}
	
	
	
	/**
	 * Class for each node of the linkedList. Uses the generic type E, and has a head/tail pointer
	 */
	class Node{
		
		// Fields
		E data;
		Node next;
		
		Node previous; // Keeps remove() O behavior O(1)
		
		
		/**
		 * Constructor with a single parameter to set data type
		 * 
		 * @param data: Object with the Node's data type
		 */
		public Node(E data) {
			
			this.data = data;
			this.next = null;
			this.previous = null;
		}
		
	}
	
	
	
	/**
	 * Adds a node to the linked list by pointing the last Node's tail to the new one and adjusting
	 * fields accordingly
	 * 
	 * @param data: Object to be contained in the Node
	 */
	public void addNode(E data) {
		
		// Creates the new Node
		Node newNode = new Node(data);
		
		// Catch case to see if list is empty
		if(head != null) {
			
			// If the list is not empty the tail of the next will be equal to the new node
			newNode.previous = tail;
			//System.out.println("Current tail: " + tail.data); // Test statement
			tail.next = newNode;
			
			//System.out.println("newNode: " + newNode.data); // Test statements
			//System.out.println("Tail's tail: " + tail.next.data);
			
			tail = newNode;
			
			//System.out.println("New tail: " + tail.previous.next.data); // Test statements
			//System.out.println("New tail's prev: " + tail.previous.data);
			//System.out.println("New tail's tail: " + tail.next);
			
			listSize++; // track size
			
		} else {
			
			// If the list is empty, the whole set of nodes only point to the new Node
			head = newNode;
			tail = newNode;
			//head.next = tail;
			//tail.previous = head;
			listSize++; // track size
		}
	}
	
	
	
	@Override
	/**
	 * Inserts an element at the beginning of the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @param element - element to add
	 */
	public void insertFirst(E element) {
		
		// Catch case for empty list
		if(size() == 0) {
			addNode(element);
			
			// Make a node for data passed in the parameter
			Node newNode = new Node(element);
			Node temp = head;
			
			// If the list is not empty the head of the next node will be equal to the new node
			head = newNode;
			head.next = temp;
			
			return; // Exit the function
		}
		
		// Make a node for data passed in the parameter
		Node newNode = new Node(element);
		Node temp = head;
		
		// If the list is not empty the head of the next node will be equal to the new node
		head = newNode;
		head.next = temp;
		listSize++; // track size
	}
	
	
	
	@Override
	/**
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		
		// Catch case for empty list
		if(this.size() == 0) {
			
			addNode(element);
			return; // Exit function
		}
		
		// Make a node for data passed in the parameter
		Node newNode = new Node(element);
		Node currentNode = head;
		int indexCounter = 1;
		
		// While the index is within the number of nodes,
		while(indexCounter < this.size()) {
			
			// If the counter has reached the desired index:
			if(indexCounter == index) {
				
				// Sets the newNode in the chain of nodes
				newNode.next = currentNode.next;
				currentNode.next = newNode;	
				listSize++; // track size
				return;
			}
			
			// Sets the next position at the end to avoid making refs to the temp object itself
			currentNode = currentNode.next;
			indexCounter++;
		}
		
		// Out of bounds exception
		throw new IndexOutOfBoundsException();
	}
	
	
	
	@Override
	/**
	 * Gets the first element in the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	public E getFirst() throws IndexOutOfBoundsException {
		
		// Catch case for an empty array.
		if(this.size() == 0) {
			throw new IndexOutOfBoundsException();
		}
			
		// Return the data for the first node, the head.
		return head.data;
	}



	@Override
	/**
	 * Gets the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	public E get(int index) throws NoSuchElementException {
		
		// Sets up the relevant fields
		int indexCounter = 0;
		Node temp = head;
		
		// Catch case for the first index of the list.
		if(index == 0) {
			return temp.data;
		}
		
		
		// While there are still nodes in the list, keep tracking the number of indexes counted until the
		//  current position reaches the parameter.
		while(temp.next != null) {
			
			// Move on from the head and update the indexCounter
			temp = temp.next;
			indexCounter++;
			
			// If the indexCounter has reached the parameter's value,
			if (index == indexCounter) {
				return temp.data; // Return the current node.
			}
		}
		
		// If the index is beyond the length of the list, throw the exception
		throw new NoSuchElementException(); 
	}


	
	@Override
	/**
	 * Deletes and returns the first element from the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	public E deleteFirst() throws NoSuchElementException {
		
		// Catch case for an empty array.
		if(this.size() == 0) {
			throw new NoSuchElementException();
		}

		// Stores the head to return
		Node temp = head;
		
		// Makes the value after the head a new value
		Node newHead = head.next;
		head = newHead;
		
		return temp.data;
	}



	@Override
	/**
	 * Deletes and returns the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	public E delete(int index) throws IndexOutOfBoundsException {
		
		// Catch case for an empty list.
		if(this.size() == 0) {	
			throw new IndexOutOfBoundsException();
		}
		
		// Catch case for copies of deleteFirst
		if(index == 0) {
			
			deleteFirst();
			listSize--;
			return head.data;
		}
			
		
		// Make a node for the data passed in the parameter.
		Node previousNode = head;
		Node currentNode = head.next;
		int indexCounter = 1;
				
		// While the index is within the number of nodes,
		while(indexCounter < this.size()) {
					
			// If the counter has reached the desired index:
			if(indexCounter == index) {
						
				// Sets the newNode in the chain of nodes
				previousNode.next = currentNode.next;
				listSize--;
				return currentNode.data;
			}
					
			// Sets the next position at the end to avoid making refs to the temp object itself
			currentNode = currentNode.next;
			indexCounter++;
		}
				
		// If the desired index is too large:
		throw new IndexOutOfBoundsException();
	}



	@Override
	/**
	 * Determines the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	public int indexOf(Object element) {
		
		// Sets up a counter and goes through each node, and checks if they're equal
		int indexCounter = 0;
		Node temp = head;
		
		// While the current node is not a match and its still within bounds:
		while(indexCounter < size()) {
			
			if(element == temp.data) {
				return indexCounter;
			}
			
			indexCounter++;
			temp = temp.next;
		}
		
		// If the element isn't in the list, return -1
		return - 1;
	}



	@Override
	/**
	 * O(1) for a singly-linked list. Counts the numbers of nodes in a list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size() { return listSize; }



	@Override
	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	public boolean isEmpty() {

		// Check the head, if it's there, return true
		if(head == null) {
			return true;
		} else { // False otherwise
			return false;
		} 
	}



	@Override
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a singly-linked list.
	 */
	public void clear() {
		
		// Catch case for an empty list
		if(listSize == 0) {
			return; // Do nothing
		}
		
		listSize = 0; // Track size
		
		// Clear the head and the tail which clears the list.
		head.next = null;
		head = null;
		tail.previous = null;
		tail = null;
	}
	
	
	
	@Override
	/**
	 * Generates an array containing all of the elements in this list in proper sequence 
	 * (from first element to last element).
	 * O(N) for a singly-linked list.
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	public Object[] toArray() {
		
		//System.out.println(head);
		
		// Start at the head of the linkedList and make a new array of the size of the list.
		Node temp = head;
		Object[] nodes = new Object[size()];
		
		// Catch case for an empty list
		if(temp.data == null) {
			return nodes;
		}
		
		// Sets the first index of the nodes to the head
		int index = 0;
		nodes[index] = temp;
		
		// While there are still nodes in the list:
		while(temp.next != null) {
		
			// Increment the index, move forward in the list of nodes, 
			//  and set the nodes at the index to the current position
			index++;
			nodes[index] = temp.next;
			temp = temp.next;
		}
		
		return nodes;
	}
	
	
	
	@Override
	/**
	 * @return an iterator over the elements in this list in proper sequence (from first element to last element)
	 */
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	
	
	/**
	 * Defines the Iterator above.
	 * 
	 * @param <E>
	 */
	class LinkedListIterator implements Iterator<E> {
		
		Node currentNode = head;
		Node previousNode = head; // Created to improve O behavior for remove()
		
		
		/**
		 * Constructor to instantiate relevant fields
		 * @return 
		 */
		public LinkedListIterator() {
			
		}
		
		
		
		@Override
		/**
		 * Moves to the next item in the linkedList and returns the data at the node,
		 * or the null if there is no next node.
		 * 
		 * @return E: Data stored in the next node
		 */
		public E next() {
			
			// Catch case for empty list
			if(head == null) {
				return null;
			}
			
			//Statements for testing:
			//System.out.println("BEGINNING OF NEXT - Current node: " + currentNode.data);
			//System.out.println("BEGINNING OF NEXT - Previous node: " + previousNode.data);
			
			
			// This section checks to see if the currentNode is moving for the first time, to improve
			//  remove() O behavior (for extra credit) :D
			if(currentNode == head) {
				
				if(this.hasNext()) {
					currentNode = currentNode.next;
				}
				
				
				// More testing statements
				//System.out.println("END OF NEXT - Current node: " + currentNode.data);
				//System.out.println("END OF NEXT - Previous node: " + previousNode.data);
				
				return currentNode.data;
			}
			
			
			// Move the iterator to the next node.
			if(this.hasNext()) {
				
				currentNode = currentNode.next;
			} else {
				
				return null; // Returns null if there is no next object.
			}
			
			// Tracks the previousNode if the currentNode has moved
			previousNode = previousNode.next;
			
			
			// More testing statements
			//System.out.println("END OF NEXT - Current node: " + currentNode.data);
			//System.out.println("END OF NEXT - Previous node: " + previousNode.data);
			
			// Return the data at that point
			return currentNode.data;
		}

		
		
		@Override
		/**
		 * Checks to see if the node has a node following it.
		 * 
		 * @return boolean: T if the node has a next node, false otherwise.
		 */
		public boolean hasNext() {
			
			// Catch case for a completely empty set of Nodes
			if (currentNode == null) {
				return false;
			}
			
			return (currentNode.next != null); // Returns this boolean expression to evaluate it
		}
		

		
		/**
		 * Removes the currentNode from the series of nodes.
		 */
		public void remove() {
			
			// More testing statements
			//System.out.println("BEGINNING OF REMOVE - Current node: " + currentNode.data);
			//System.out.println("BEGINNING OF REMOVE - Previous node: " + previousNode.data);
			
			// Catch case for a single element length of nodes.
			if( currentNode == head) {
				
				deleteFirst();
				//System.out.println("END OF REMOVE - Current node: " + currentNode.data);
				//System.out.println("END OF REMOVE - Previous node: " + previousNode.data);
				return;
			}
			
			// If the iterator is at the end of the list, it deletes the last element and sets everything one back.
			if(currentNode == tail)  {
				
				currentNode = previousNode;
				
				// As long as the previousNode has an element before it, it sets it back one.
				if(previousNode.previous != null) {
					previousNode = previousNode.previous;
				}
				
				// Set the next value to null.
				currentNode.next = null;
				return;
			}
			
			// If there is no exceptional case, thread the nodes through the currentNode and reset proper values.
			previousNode.next = currentNode.next;
			currentNode = currentNode.next;
		}
		
		
		
		/**
		 * Accessor method for data within a node.
		 * @return E: Data at the currnetNode
		 */
		public E getData() {
			return currentNode.data;
		}
		
	}
}
