package assignment10;

/**
 * This class represents a Maximum Binary Heap, and as such implements a PriorityQueue interface written by this course's instructor.
 * A Binary Heap keeps a backing array to represent the tree, and each node added to the tree is at the next available space at the'
 * bottom of the tree (or the next available space in the array) and percolates up until the new node is less than each parent node.
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version: 07:13:23 CS-2420_001 SUM_2023
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxBinaryHeap<E> implements PriorityQueue<E> {
	
	// Fields
	ArrayList<E> maxHeap;
	
	
	/**
	 * @Constructor that instantiates the backing array
	 */
	public MaxBinaryHeap() {
		
		maxHeap = new ArrayList<E>();
	}
	
	
	
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {
		
		
	}
	
	
	
	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException{
		
		return null;
	}
	
	
	
	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException{
		
		return null;
	}
	
	

	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	public int size() {
		
		return 0;
	}
	
	
	
	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	public boolean isEmpty() {
		
		return false;
	}
	
	
	
	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	public void clear() {
		
		
	}
	
	
	
	/** 
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item 
	 * must be stored at index 0 in the returned array, regardless of 
	 * whether it is in stored there in the backing array.) 
	 */
	public Object[] toArray() {
		
		return null;
	}

}
