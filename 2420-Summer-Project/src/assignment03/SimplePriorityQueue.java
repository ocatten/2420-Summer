package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>{
	
	int arraySize = 0;
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
		//Find the largest element at the first index of the backing array
		//and return it.
	    E max = backingArray[0];
		return max;
	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E deleteMax() throws NoSuchElementException {
		//If the backing array is empty, throw NoSuchElementException
		if(backingArray.length == 0) {
			throw new NoSuchElementException();
		}
		
		//Find the largest element at the first index of the backing array
		E max = backingArray[0];
		//Loop through the array, bring each element up one and remove the
		//max element.
		for(int i = 1; i < backingArray.length - 1; i++) {
			backingArray[i] = backingArray[i-1];
		}
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
		
		//Loop through the backing array
		for(E object : backingArray) {
			//If any of the elements in the list match the given item, return true
			if(object.equals(item)) {
				return true;
			}
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
	public void clear() {
		backingArray = (E[]) new Object[arraySize];
	}
	
	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	public void insert(E item) {
		//Create a new generic array one index longer then the current array
		E[] newArray = (E[]) new Object[backingArray.length + 1];
		System.out.println("The length of the new array is " + newArray.length);
		
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
		
		if(backingArray.length == 1) {
			if(itemIndex <= 0) {
				newArray[1] = item;
			}
			else {
				newArray[1] = newArray[0];
				newArray[0] = item;
			}
			backingArray = newArray;
			System.out.println("Backing array 1 = " + backingArray[1]);
			return;
		}
		System.out.println("Item index = " + itemIndex );
		
		for(int i = 0; i < backingArray.length; i++) {
			//If the current index is equal to the item index, add the item
			//at the current index.
			if(i == itemIndex) {
				newArray[i] = item;
				//Add one to the item added count to push every following object 
				//back one index
				itemAdded ++;
			}
			backingArray[i + itemAdded] = backingArray[i];
		}
		//Set the new array equal the backing array
		backingArray = newArray;
	}
	
	
	/**
	 * Computes a binary search for a given item on the backing array
	 * 
	 * @param item
	 * @throws NoSuchElementException if the array doesn't have that index
	 */
	public int binarySearch(E item) throws NoSuchElementException {
		
		// Sets up fields for the binary search
		int high = backingArray.length;
		int low = 0;
		int mid = ((high - low) / 2) + low;
		System.out.println(mid);
		System.out.println(backingArray[mid]);
		//Tester for tracking if object exists
		int count = 0;
		
		while(low < high) {
			
			if(backingArray.length == 1) {
				int position = ((Comparable<? super E>)backingArray[0]).compareTo(item);
				return position;
			}
			
			// Calculate the middle of the new set of data and compare the middle of the data set with the param
			//System.out.println("mid calculated: " + mid); // Test case for out of bounds error / infinite loop case
			int midToParam = ((Comparable<? super E>)backingArray[mid]).compareTo(item);
			//System.out.println("midToParam result: " + midToParam); // Typewriting the code
			
			// If the midpoint is below the parameter:
			if (midToParam < 0) {
				low = mid + 1; // Add one to the low since it starts at 0
				count++;
			}
			
			// If the midpoint is greater than the parameter:
			if (midToParam > 0) {	
				high = mid;
				count++;
			}
			
			// If the low midToParam is equal, return the low's index plus one
			if (midToParam == 0) {
				return mid + 1;
			}
			
			mid = ((high - low) / 2) + low; // Recalculate the midpoint.
			
			if(count > backingArray.length) {
				System.out.println("The given object could not be found."
						+ "Binary search iterated " + count + " times.");
				throw new NoSuchElementException();
			}
		}
		
		//System.out.println("while loop exited");
		// Return 0 if the high is never above the low (if the list is 0)
		return mid;
	}
}
