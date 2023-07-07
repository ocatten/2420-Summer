package assignment09;

/**
 * HashTable class with implemented Map interface methods provided by the instructor. Uses separate chaining to resolve collisions.
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:07:23 2420_001 SUM-2023
 */

import java.util.ArrayList;
import java.util.LinkedList;

import a9.MapEntry;

public class HashTable<K, V> implements Map<K, V> {
	
	
	// Tracker for the lambda value for rehashing
	private ArrayList< LinkedList< MapEntry<K, V> > > table;
	double lambda = 0;
	
	ArrayList< LinkedList< MapEntry<K, V> > > hashTable = new ArrayList< LinkedList< MapEntry<K, V> > >();

	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	public void clear() {

		// Clear each entry from the table while keeping its size
		for(LinkedList< MapEntry<K, V> > entry : table) {
			entry.clear();
		}
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

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
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

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
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

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
		// TODO Auto-generated method stub
		return null;
	}

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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	

}
