package assignment09;

/**
 * HashTable class with implemented Map interface methods provided by the instructor. Uses separate chaining to resolve collisions.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:07:23 2420_001 SUM-2023
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {

	// Fields
	ArrayList< LinkedList< MapEntry<K, V> > > hashTable = new ArrayList<LinkedList<MapEntry<K,V>>>(); // List backing the table
	int capacity = 10;
	int tableSize = 0; //Tracker for the elements in the array
	
	
	/**
	 * Constructor that sets up an initial hashTable ArrayList with 10 empty LinkedLists
	 */
	public HashTable() {
		
		// Add 10 empty LinkedLists to the backingArrayLists
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList< MapEntry<K, V> >());
		}
	} 
	
	
	
	/**
	 * @return: capacity
	 */
	public int getCapacity() { return capacity; }
	
	/**
	 * @return: Backing ArrayList for the HashTable
	 */
	public ArrayList< LinkedList< MapEntry<K, V> > > getHashTableArrayList() { return hashTable; }
	
	
	
	/**
	 * Compression function that modulates the hashCode of the object by the current capacity(length) of the hashTable
	 * 
	 * @param key: Generic object to be hashed
	 * @return: Index of the hashTable the key will be added to
	 */
	public int compression (Object key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	
	
	/**
	 * When the overhead gets too large, this function rehashes the table.
	 */
	public void rehash() {
	
		// Double the capacity to find the closest prime number
		capacity *= 2;
		boolean prime = false; // Prime flag
		
		while(!prime) { // While the current capacity isn't prime:
			prime = true; // Set the flag to true to be checked
			
			// Check each number between 1 and the current capacity (exclusive):
			for(int i = 2; i < capacity; i++) {
			
				// If something divides evenly into the current capacity:
				if(capacity % i == 0) { 
					prime = false; // The capacity is not prime.
					break; // Exit the for loop
				}
			}
			
			// Increment the capacity if the number isn't prime
			if(!prime) {
				capacity++; 
			}
		}
		
		// Create a LinkedList for the Map Entries
		List< MapEntry<K, V> > mapEntriesList = this.entries();
		
		//System.out.println("REHASHING " + entryList.size()); // Test statement
		
		// Clear the hashTable to rehash
		hashTable.clear();
		tableSize = 0;
		
		
		// Add an empty LinkedList to each index within the capacity
		for(int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList< MapEntry<K, V> >());
		}

		//System.out.println("Rehashing. New capacity = " + capacity); // Test statements
		
		// Adds all of the existing MapEntries back into the hashTable.
		for(MapEntry<K,V> mapEntry : mapEntriesList) {
			
			this.put(mapEntry.getKey(), mapEntry.getValue());
		}
	}
	
	
	
	@Override
	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	public void clear() {

		//Clear each entry from the table while keeping its size
		for(LinkedList< MapEntry<K, V> > mapEntry : hashTable) {
			mapEntry.clear();
		}
		
		tableSize = 0; // Reset tracking field
	}
	
	
	
	@Override
	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		
		// For each entry at this key's value:
		for(MapEntry<K , V> mapEntry : hashTable.get(compression(key)) ) {
				
			if(mapEntry.getKey().equals(key)) {
				return true; // Return true if a match is found
			}
		}	
		
		return false; // Else case for no match
	}

	
	
	@Override
	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {
		
		// Loops through each LinkedList and each value within to find a match
		for (LinkedList<MapEntry<K,V>> tableEntry : hashTable) {
			
			// Loop through the LinkedLists
			for (MapEntry<K,V> mapEntry : tableEntry) {
				
				// Return true if a match is found
				if (mapEntry.getValue().equals(value)) {
					return true;
				}
			}
		}
		
		return false; // Else for no matches, doesn't contain value
	}
	
	
	
	@Override
	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List< MapEntry<K, V> > entries() {
		
		// List to add to and return
		LinkedList< MapEntry<K, V> > allEntries = new LinkedList< MapEntry<K, V> >();
		
		// Checks each linkedList
		for(LinkedList< MapEntry<K, V> > tableEntry : hashTable) {
			
			if(tableEntry != null) { // Add every existing tableEntry to allEntries
				allEntries.addAll( (Collection< ? extends MapEntry<K, V> >) tableEntry );
			}
		}
		//System.out.println(allEntries.size());
		return allEntries; // Return the completed list.
	}

	
	
	@Override
	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {
		
		// Find the list of MapEntries for the compressed key
		LinkedList< MapEntry<K, V> > entryList = hashTable.get( compression(key) );
		
		// Go through each entry in the LinkedList of MapEntries
		for(MapEntry<K, V> currentEntry : entryList) {
			
			// If there's a matching key, return that entry's value
			if(currentEntry.getKey().equals(key)) {
				return currentEntry.getValue();
			}
		}
		
		return null; // Null for no match
	}
	
	
	
	@Override
	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		
		return tableSize == 0; // Check if the tracking field for the number of elements has been added to
	}

	
	
	@Override
	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value; 
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {
		
		// Finds the LinkedList at the key's hash
		LinkedList< MapEntry<K, V> > entryList = hashTable.get(this.compression(key));
		
		// Iterate through each item at the LinkedList at the compressed key
		for (MapEntry<K, V> currentEntry: entryList) {
			
			// If there's a matching key, update the MapEntry's value
			if(currentEntry.getKey().equals(key)) {
				
				//System.out.println("KEY: " + currentEntry.getKey()); // Test statement
				//System.out.println("VALUE: " + currentEntry.getValue()); // Test statement
				
				currentEntry.setValue(value);
				//System.out.println("NEW VALUE: " + currentEntry.getValue()); // Test statement
				return value; // Return the new value for that key, break from the method
			}
		}
		
		// Else case for no matching key: Add the key-value pair to the end of the LinkedList in a new MapEntry
		entryList.add(new MapEntry<K, V>(key, value));
		tableSize++; // Increase the tracking field for number of items
		
		// Test statements
		//System.out.println("Value equals " +value);
		//System.out.println("size " + table.size()); 
		
		double lambda = (double)tableSize / (double) capacity; // Find lambda for overhead
		//System.out.println("Lambda = " + (lambda));
		//System.out.println("table size equals " + tableSize);
		// Check lambda to see if the function needs to rehash

		if(lambda >= 10) {
			this.rehash();
		}
		
		return value; // Return the new value for the key
	}
	
	
	
	@Override
	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {
		
		// Finds the LinkedList at the key's hash
		LinkedList< MapEntry<K, V> > entryList = hashTable.get(this.compression(key));
		
		// Iterate through each MapEntry in the LinkedList at the key's compressed index
		for (MapEntry<K, V> currentEntry: entryList) {
			
			if (currentEntry.getKey().equals(key)) { // If there's a matching key, remove the MapEntry
				
				entryList.remove(currentEntry);
				tableSize--; // Decrement the tracking field for the number of elements

				return currentEntry.getValue(); // Return the removed MapEntry's value
			}
		}
		
		return null; // Else case for no matching key: Return null
	}
	
	
	
	@Override
	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() { return tableSize; }
	
}
