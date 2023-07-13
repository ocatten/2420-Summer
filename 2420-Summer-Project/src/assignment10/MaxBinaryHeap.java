package assignment10;

import java.util.Comparator;

/**
 * This class represents a Maximum Binary Heap, and as such implements a PriorityQueue interface written by this course's instructor.
 * A Binary Heap keeps a backing array to represent the tree, and each node added to the tree is at the next available space at the'
 * bottom of the tree (or the next available space in the array) and percolates up until the new node is less than each parent node.
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version: 07:13:23 CS-2420_001 SUM_2023
 */

import java.util.NoSuchElementException;

public class MaxBinaryHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
	
	// Fields
	E[] maxHeap;
	int backingArrayLength, size;
	private Comparator<? super E> cmp;
	
	
	/**
	 * @Constructor that instantiates the backing array and relevant fields
	 */
	public MaxBinaryHeap() {
		
		maxHeap = (E[]) new Object[3];;
		backingArrayLength = 3;
		size = 0;
		
		// Comparator object used for the generic objects 
		cmp = new Comparator<E>() { 
			public int compare(E e1, E e2) { return e1.compareTo(e2); } };
	}
	
	
	
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {
		
		size++; // Track the size
		
		// Check if the backing array needs to be resized
		if (size > backingArrayLength) {
			this.resize();
		}
		
		// Add this element to the next available space
		maxHeap[ backingArrayLength-size ] = item;
		
		// Percolate up if needed
		percolateUp(backingArrayLength-size);
	}
	
	
	
	/**
	 * Changes the backing array to be an adequate length
	 */
	private void resize() {
		
		// Make a new empty array for the resized array.
		E[] newMaxHeap = (E[])new Object[ (backingArrayLength*2)+1 ];
		
		// Loop through the current backing array and copy its existing elements to the new one
		for(int i = 0; i < backingArrayLength; i++) {
			newMaxHeap[i] = maxHeap[i];
		} 
		
		// Adjust the size tracker
		backingArrayLength = (backingArrayLength*2)+1;
	}
	
	
	
	/**
	 * Move the given index up the tree till the order is correct. Swap 
	 * the elements at each position when moving up the tree.
	 * 
	 * @param index: Index of the item in the backing array to be percolated.
	 */
	private void percolateUp(int index) {
		
		// If this child node is greater than its parent (found at (i-1)/2 in a binary heap for the array):
		if ( cmp.compare(maxHeap[index], maxHeap[ (index-1)/2 ] ) > 0) {
			
			// Store the data in the child node
			E givenData = maxHeap[index]; 
			
			// Swap the two elements at the parent and child
			maxHeap[index] = maxHeap[ (index-1)/2 ];
			maxHeap[ (index-1)/2 ] = givenData;
			
			// Repeat this process at the new position of the added element
			percolateUp( (index-1)/2 );
			
		} else {
			
			return; // Base case, if there's no percolation needed its done
		}
		
		//
		/*if(index % 2 == 0) {
			priorIndex = (index - 2)/2;
		
		}
		if(index % 2 == 1) {
			priorIndex = (index - 1)/2;
		
		}
		priorData = (E) maxHeap[priorIndex];
		boolean givenDataGreater;
		if(givenDataGreater) {
			maxHeap[index] = priorData;
			maxHeap[priorIndex] = givenData;
		}
		else {
			return;
		}*/
	}



	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException{
		
		return (E)maxHeap[0];
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
