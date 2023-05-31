package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * Creates a simple priority queue and can perform actions on it such
 * as insert, find and delete the max, find if it contains an item, return
 * its size and insert all of the items in a collection.
 * 
 * @Author Everett Oglesby and Parker Catten
 * May 30,2023
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>{
	
	int arraySize = 0;
	@SuppressWarnings("unchecked")
	E[] backingArray = (E[]) new Object[arraySize];
	
	/**
	 * Simple Priority Queue constructor\
	 */
	public SimplePriorityQueue() {
		
	}
	
	/**
	 * Simple Priority Queue constructor that will sort
	 * the array through the comparator sorting generic
	 * E objects.
	 */
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		
	}

	
	/**
	 * Retrieves, but does not remove, the maximum element in this priority
	 * queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E findMax() throws NoSuchElementException {
		//If the backing array is empty, throw NoSuchElementException
		if(backingArray.length == 0) {
			throw new NoSuchElementException();
		}
		//Find the largest element at the last index of the backing array
		//and return it.
	    E max = backingArray[size()-1];
		return max;
	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@SuppressWarnings("unchecked")
	public E deleteMax() throws NoSuchElementException {
		//If the backing array is empty, throw NoSuchElementException
		if(backingArray.length == 0) {
			throw new NoSuchElementException();
		}
		
		//Find the largest element at the last index of the backing array
		E max = backingArray[backingArray.length-1];
		
		//Create a new array and transfer every item but the last to the same index on
		//the new array and then make the backing array equal to the new array
		E[] newArray = (E[]) new Object[backingArray.length-1];
		for(int i = 0; i < backingArray.length -1 ; i++) {
			newArray[i] = backingArray[i];
		}
		backingArray = newArray;
		
		//Return the max element
		return max;
	}



	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	public void insertAll(Collection<? extends E> coll) {
		//Insert each item in the collection into the backing array.
		for(E item : coll) {
			insert(item);
		}
		
	}

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority queue
	 * @return true if the item is contained in this priority queue
	 */
	public boolean contains(E item) {
		
		//If the backing array is empty, add the given item at the first index
		if(backingArray.length == 0) {
			backingArray[0] = item;
			return false;
		}
		
		//If their is only one item in the array, return the item at the first index
		if(backingArray.length == 1) {
			if(backingArray[0].equals(item)) {
				return true;
			}
			return false;
		}
		
		//Find the index of the element
		int itemIndex = binarySearch(item);
		
		//If the item at the item index matches the give item, return true
		if(backingArray[itemIndex-1].equals(item)) {
			return true;
		}
		//If none of the elements match the given element, return false
		return false;
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	public int size() {
		return backingArray.length;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		//If the length is greater than zero return true
		if(backingArray.length <= 0) {
			clear(); // for edge cases
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be
	 * empty when this call returns.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		backingArray = (E[]) new Object[arraySize];
	}
	
	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	public void insert(E item) {
		
		//Create a new generic array one index longer then the current array
		E[] newArray = (E[]) new Object[backingArray.length + 1];
		//System.out.println("The length of the new array is " + newArray.length);
		
		//If the backing array is empty, add the given item at the first index
		if(backingArray.length == 0) {
			newArray[0] = item;
			backingArray = newArray;
			return;
		}
		
		
		//Flag for if the item has been added to the new array
		int itemAdded = 0;
		//Finds the index of the item in the array
		int itemIndex = binarySearch(item);	
		
		//System.out.println(); //Return the index of the current item and the array length to the console
		//System.out.println("Item index = " + itemIndex + " and the array length is " + newArray.length);
		
		for(int i = 0; i < backingArray.length; i++) {
			//If the current index is equal to the item index, add the item
			//at the current index.
			if(i == itemIndex || itemIndex < 0) {
				newArray[i] = item;
				//Add one to the item added count to push every following object 
				//back one index
				itemAdded ++;
			}
			//Add the current index of the backing array plus one if the item
			//has been added
			newArray[i + itemAdded] = backingArray[i];
			
			//If at the second to last index and the item hasn't been added yet,
			//add the item to the next index.
			if(i+1 == backingArray.length && itemAdded == 0) {
				newArray[i+1] = item;
			}
		}

		//Set the new array equal the backing array
		backingArray = newArray;
		
		//Print out the array as a string to the console
//		for(int i = 0; i < backingArray.length; i++) {
//			System.out.print(backingArray[i] + " | ");
//		}
	}
	
	
	/**
	 * Computes a binary search to find the index of a given item in
	 * the backing array.
	 * 
	 * @param item
	 * @throws NoSuchElementException if the array doesn't have that index
	 */
	public int binarySearch(E item) throws NoSuchElementException {
		
		// Sets up fields for the binary search
		int high = backingArray.length;
		int low = 0;
		int mid = ((high - low) / 2) + low;

		//Tracker for if the index can be found
		int count = 0;
		
		while(low < high) {
			
			//If the length of the backing array is one item long, return the inverse of the
			//first position of the backing array compared to the given item
			if(backingArray.length == 1) {
				int position = ((Comparable<? super E>)backingArray[0]).compareTo(item);

				return -position;
			}
			
			// Calculate the middle of the new set of data and compare the middle of the data set with the param
			int midOfSublist = ((Comparable<? super E>)backingArray[mid]).compareTo(item);
			
			// If the midpoint is below the parameter:
			if (midOfSublist < 0) {
				low = mid + 1; // Add one to the low since it starts at 0
				count++;
			}
			
			// If the midpoint is greater than the parameter:
			if (midOfSublist > 0) {	
				high = mid;
				count++;
			}
			
			// If the low midOfSublist is equal, return the low's index plus one
			if (midOfSublist == 0) {
				return mid + 1;
			}
			
			mid = ((high - low) / 2) + low; // Recalculate the midpoint.
			
			//If the index can't be found, throw NoSuchElementException
			if(count > backingArray.length) {
				System.out.println("The given object index could not be found."
						+ " Binary search iterated " + count + " times.");
				throw new NoSuchElementException();
			}
		}
		
		// Return 0 if the high is never above the low (if the list is 0)
		return mid;
	}
}
