package a9;
/**
 * Hash table function using the Map interface. Creates an ArrayList of LinkedLists of MapEntries to create the hash table.
 * Also has functions such as add, remove, clear, etc. to assist with hashing.
 * 
 * @version 04:04:23 2420_001
 * @author Parker Catten & Everett Oglesby
 * 
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	
	
	// fields
	private int capacity = 15;
	private int n = 0;
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	
	
	/**
	 * Constructor that sets a good capacity so that lambda does not exceed 10.
	 */
	public HashTable() {
		
		// Makes the backing array for the values
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		
		// For each item within the capacity:
		for(int i = 0; i < capacity; i++) {
			
			// Add an empty list there.
			table.add(new LinkedList<MapEntry<K, V>>());
		}
	}
	
	
	
	/**
	 * Compression function that modulates the hashCode of the object by the current capacity of the hashTable.
	 * 
	 * @param key: Generic object to be hashed
	 * @return: Space of the hashTable it will be added to
	 */
	public int compression (Object key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	
	
	/**
	 * Creates a new HashTable with double the capacity of the 
	 * original table with each MapEntry being the header of a 
	 * new LinkedList of MapEntries. 
	 */
	public void rehash() {
		
		//Double the size of the capacity
		capacity *= 2;
		
		//Create a new Linked List of Map Entries
		List<MapEntry<K,V>> entryList = this.entries();
		
		//System.out.println("REHASHING " + entryList.size()); // Test size
		
		// Reset the backing array for the values
		table.clear();
		n = 0;
		
<<<<<<< Updated upstream
		//Clear the table
		table.clear();
				
		// For each item within the capacity create an empty LinkedList
=======
		// For each item within the capacity:
>>>>>>> Stashed changes
		for(int i = 0; i < capacity; i++) {
			
			// Add an empty list there.
			table.add(new LinkedList<MapEntry<K, V>>());
		}
		
<<<<<<< Updated upstream
		// For each item within the capacity add the LinkedList with the MapEntry
		//to each LinkedList
		for(int i = 0; i < capacity; i++) {
			
			//Add the corresponding MapEntry for the LinkedList of MapEntries to each
			//Empty MapEntry LinkedList in the ArrayList
			table.get(i).add(mapEntriesList.get(i));
			//this.put(mapEntriesList.get(i).getKey(), mapEntriesList.get(i).getValue());
=======
		// Puts the new elements into the backing array
		for(MapEntry<K,V> currentEntry : entryList) {
			
			this.put(currentEntry.getKey(), currentEntry.getValue());
>>>>>>> Stashed changes
		}
	}
	
	
	
	@Override
	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	public void clear() {
		
		// Empty every entry
		for (LinkedList<MapEntry<K,V>> tableEntry : table) {
			
			tableEntry.clear();
		}
		
		n = 0; // Now that there aren't any items in the list, n is 0
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
	public boolean containsKey(Object key) {
		
		// If the list at the key's index has items, it contains the key
		if (table.get(compression(key)).size() != 0) {
			return true; 
		} else { // If there are no values in that space:
			return false;
		}
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
	public boolean containsValue(Object value) {
		
		// Loops through each value in the hashTable and each array within those table and returns true if
		//  a match is found
		for (LinkedList<MapEntry<K,V>> tableEntry : table) {
			
			// Loop through the linkedLists
			for (MapEntry<K,V> mapEntry : tableEntry) {
				
				// Return true if a match is found
				if (mapEntry.getValue().equals(value)) {
					return true;
				}
			}
		}
		
		// If no match was found:
		return false;
	}
	
	
	
	@Override
	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing
	 * O(table length + N) for separate chaining
	 * 
	 * @return a List object containing all mapping (i.e., table) in this map
	 */
	public List<MapEntry<K, V>> entries() {
		
		// Empty list to be returned
		LinkedList<MapEntry<K,V>> returned = new LinkedList<MapEntry<K,V>>();
		
		// Checks each non-null element of the table
		for(LinkedList<MapEntry<K,V>> tableEntry : table) {
			
			// If this value isn't null, add each item in that list to the list to be returned.
			if(tableEntry != null) {
				returned.addAll((Collection<? extends MapEntry<K, V>>) tableEntry);
			}
		}
		
		// Return the completed list.
		return returned;
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
		
		// Compresses the key to find the right index
		int code = compression(key);
		
		// Finds the corresponding index to the key
		LinkedList<MapEntry<K, V>> entryList = table.get(code);
		
		// Check each MapEntry in the linkedList to see if there's a matching key
		for(MapEntry<K,V> currentEntry : entryList) {
			
			// If a match is found, return its value
			if(currentEntry.getKey().equals(key)) {
				return currentEntry.getValue();
			}
		}
		
		// Null if no match is found
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
		
		if(n == 0) {
			return true;
		} else {
			return false;
		}
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
		
		// Finds the linkedList at the key
		LinkedList<MapEntry<K,V>> entryList = table.get(this.compression(key));
		
		// Iterate through each item in the target list
		for (MapEntry<K,V> currentEntry: entryList) {
			
			// If there's a matching key, update the value and return the new one
			if(currentEntry.getKey().equals(key)) {
				
				System.out.println("KEY: " + currentEntry.getKey()); // Test statement
				System.out.println("VALUE: " + currentEntry.getValue()); // Test statement
				
				// Full object path needed to directly change
				currentEntry.setValue(value);
				System.out.println("NEW VALUE: " + value); // Test statement
				
				return value; // Return the value
			}
		}
		
		// If there is no match, add the key-value pair to the end of the list
		entryList.add(new MapEntry<K,V>(key, value));
		
		// There's a new item, so increase the itemTotal .
		n++;
		//System.out.println("size " + table.size()); // Test statment
		int lambda = n / table.size();
		// Check lambda to see if the function needs to rehash
		if(lambda >= 10) {
			this.rehash();
		}
		
		return value;
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
		
		// Finds the linkedList at the key
		LinkedList<MapEntry<K,V>> entryList = table.get(this.compression(key));
		
		// Iterate through each item in the target list
		for (MapEntry<K,V> currentEntry: entryList) {
			
			// If there's a matching key, remove the value
			if(currentEntry.getKey().equals(key)) {
				
				entryList.remove(currentEntry);
				
				// Decrement the element count
				n--;

				return currentEntry.getValue();
			}
		}
		
		// If there's no match, return null
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
		return n; // Counter that is tracked for O(1) behavior.
	}

}
	
	
	// CODE FOR UNIMPLEMENTED HELPER METHOD TO PRINT LIST OUT:
	// Loops through each linkedList and each mapEntry for that linkedList
	/*for (LinkedList<MapEntry<K,V>> tableEntry : table) {
		
		// Print each key for the series of values:
		System.out.print("Key: " + tableEntry.hashCode() + " -> ");
		
		// Loop through the linkedLists
		for (MapEntry<K,V> mapEntry : tableEntry) {
			
			// Print the values at the keys
			System.out.print(mapEntry.getValue() + " ");
		}
		
		// End the line.
		System.out.println();
	}*/
	
