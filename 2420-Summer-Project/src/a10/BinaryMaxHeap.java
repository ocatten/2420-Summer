	package assign10;
/**
 * Class created to handle the creation of a BinaryMaxHeap data structure. A BinaryMaxHeap is a binary search tree
 * that must have each child node be less than or equal to each child node, and the same must be true for each node
 * beneath each child node. Nodes are inserted top to bottom, left to right, and swapped accordingly.
 * 
 * @author Parker Catten @u0580588
 * @version: 04:16:23 CS-2420_001 SP-2023
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E extends Comparable<? super E>> implements PriorityQueue {

	// Fields
	private Comparator<? super E> cmp;
	


	/**
	 * Constructor that assumes that the comparator is used with natural ordering
	 */
	public BinaryMaxHeap () {
		
		// Comparator object used for the generic objects 
		cmp = new Comparator<E>() { 
			public int compare(E e1, E e2) { return e1.compareTo(e2); } };
	}


	/**
	 * Constructor that uses a given comparator objects for future comparisons
	 * 
	 * @param cmp: Comparator object to be used
	 */
	public BinaryMaxHeap (Comparator<? super E> cmp) {
		
		// Initializes the parameter as the comparator
		this.cmp = cmp;
	}



	/**
	 * Constructor that uses a given list to build the BinaryMaxHeap
	 * 
	 * @param paramList: List of generic objects to be turned into the max heap
	 */
	public BinaryMaxHeap( List<? extends E> paramList ) {
		
		// Loops through each item in the list and adds to the heap.
		for(E currentObject : paramList) {
			
			this.add(currentObject);
		}
	}



	/**
	 * Constructor that uses a given list and a given comparator to build the BinaryMaxHeap
	 * 
	 * @param paramList: List of generic objects to be turned into the max heap
	 * @param cmp: Comparator object to be used
	 */
	public BinaryMaxHeap( List<? extends E> paramList, Comparator<? super E> cmp ) {
		
		// Initializes the parameter as the comparator
		this.cmp = cmp;
		
		// Loops through each item in the list and adds to the heap.
		for(E currentObject : paramList) {
			
			this.add(currentObject);
		}
	}



	@Override
	public void add(Object item) {
		
	}



	@Override
	public Object peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
