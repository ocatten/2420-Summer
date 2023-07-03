package assignment08;

/**
 * This class represents a generic binary search tree. It implements the provided SortedSet interface given by the instructor.
 * This tree also uses vertices to house the generic data.
 * 
 * @author: Everett Oglesby & Parker Catten
 * @version: 07:03:23 - SUM-2023_001
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
	
	
	// Fields
	public Comparator<Type> cmp = new Comparator<Type>() { // Instantiates comparator
		public int compare(Type e1, Type e2) { return e1.compareTo(e2); } };
	
	private Vertex head = null;
	private ArrayList<Type> arrayListHolder = new ArrayList<Type>();
	private int size = 0;
	
	
	@Override
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	public boolean add(Type item) {
		
		// Creates a new node to track the position on the graph and a return value
		Vertex currentNode = head;
		Vertex insertedVertex = new Vertex(item);
		
		// Catch case for empty tree
		if (head == null) {
			
			head = new Vertex(item);
			return true; // The tree was modified as the head was made
		}
		
		
		// Loop through each element of the tree until an empty node is found
		while(currentNode.data != null) {
			
			// Check the provided object with the currentNode
			if(cmp.compare(item, currentNode.data) < 0) {
				
				// Catch case for the end of tree
				if (currentNode.leftSide == null) {
					
					// If this element belongs on the left, create it there
					currentNode.leftSide = insertedVertex;
					insertedVertex.cameFrom = currentNode;
					
					//System.out.println(insertedVertex.data + " was added to the left."); // Test statement
					
					return true; // The tree was modified
				}
				
				// Move the currentNode down the tree
				currentNode = currentNode.leftSide; 
			
			// If the added node needs to go the right of the currentNode:
			} else if(cmp.compare(item, currentNode.data) > 0) {
				
				// Catch case to see if a root is found.
				if (currentNode.rightSide == null) {
					
					// Adds the new vertex to the end, sets its cameFrom, and returns true.
					currentNode.rightSide = insertedVertex;
					insertedVertex.cameFrom = currentNode;
					
					//System.out.println(insertedVertex.data + " was added to the right."); // Test statement
					
					return true; // List was modified
				}
				
				// Move the currentNode to the right
				currentNode = currentNode.rightSide;
				
			} else { // If it's equal the tree already has this value
				
				//System.out.println(" Equivalent "); // Test statement
				return false; // The parameter already exists in the tree, not modified
			}
		}
		
		// Catch case for failed modification
		return false;
	}
	
	
	
	@Override
	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	public boolean addAll(Collection<? extends Type> items) {
		
		// Flag to track if a modification was made
		boolean modified = false;
		
		// Loop through the collection and add each item
		for(Type item : items) {
			
			// Modifies the list through the method call, if true then list was modified
			if(this.add(item)) {
				modified = true;
			}
		}
		
		return modified; // Return result
	}
	
	
	
	@Override
	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	public void clear() {
		
		// Uses postOrder to clear the list, empties head field
		postOrderClear(head);
		head = null;
	}
	
	
	
	/**
	 * clear() helper method that uses a postOrder traversal to clear the tree.
	 * 
	 * @param rootVertex: Vertex located as a source of subtree or tree
	 */
	private void postOrderClear(BinarySearchTree<Type>.Vertex sourceVertex) {
		
		// Take root node given
		Vertex currentVertex = sourceVertex;
		
		// Catch case for a provided vertex that isn't a root:
		if(currentVertex.leftSide != null && currentVertex.rightSide != null) {
			return; // Do nothing and remove the call from the call stack
		}
		
		// If there's a child to the left re-run to the left
		if(currentVertex.leftSide != null) {
			postOrderClear(currentVertex.leftSide);
		}
		
		// If there's a child to the right re-run to the right now that the left is clear
		if(currentVertex.rightSide != null) { 
			postOrderClear(currentVertex.rightSide);
		}
		
		// Base case, set this root to null.
		currentVertex = null;
	}
	
	
	
	@Override
	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	public boolean contains(Type item) {
		
		// Creates tracker starting at head
		Vertex currentNode = head;
		
		// Catch case for empty tree
		if (head == null) {
			return false; // If empty, tree doesn't have param
		}
			
		// Loop through each element of the tree until a null value is hit
		while(currentNode.data != null) {
		
			// If parameter is less than the currentNode:
			if(cmp.compare(item, currentNode.data) < 0) {
		
				// If we've reached a root:
				if (currentNode.leftSide == null) {
					return false; // No match found, doesn't have param
				}
				
				currentNode = currentNode.leftSide; // Move to the left
				
				// If the param is greater than currentNode:
				} else if(cmp.compare(item, currentNode.data) > 0) {
					
					// If we've reached a root:
					if (currentNode.rightSide == null) {
						return false; // No match found, doesn't have param
					}
					
					currentNode = currentNode.rightSide; // Move to the right
					
				} else { // If parameter is equal to the current value:
					return true; // A match is found.
				}
		}
		
		// Catch case for an error.
		System.out.println("ERROR IN CONTAINS() BST");
		return false; // False otherwise.
	}
	
	
	
	@Override
	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	public boolean containsAll(Collection<? extends Type> items) {
		
		// Loop through collection to check contains()
		for(Type item : items) {
					
			// If one item in the collection is not found,
			if(!this.contains(item)) {
				return false; // Does not containsAll
			}
		}
		
		return true; // No missing elements found, containsAll
	}
	
	
	
	@Override
	/**
	 * @return the first (i.e., smallest) item in this set.
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type first() throws NoSuchElementException {
		
		// Catch case for empty tree
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// Tracking vertex starting at head
		Vertex currentVertex = head;
		
		// Move to the left of the tree while there's still a node there
		while(currentVertex.leftSide != null) {
			currentVertex = currentVertex.leftSide;
		}
		
		// Return at the left-most root
		return currentVertex.data;
	}
	
	
	
	@Override
	/**
	 * @return: true if this set contains no items.
	 */
	public boolean isEmpty() {
		return (head == null); // No head means vertex is empty
	}
	
	
	
	@Override
	/**
	 * @return the last (i.e., largest) item in this set.
	 * @throws NoSuchElementException if the set is empty
	 */
	public Type last() throws NoSuchElementException {
		
		// Catch case for empty tree
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// Tracking vertex starting at head
		Vertex currentVertex = head;
		
		// Move to the right of the tree while there's still a node there
		while(currentVertex.rightSide != null) {
			currentVertex = currentVertex.rightSide;
		}
		
		// Return at the right-most root
		return currentVertex.data;
	}
	
	
	
	@Override
	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	public boolean remove(Type item) {
		
		// Tracking node starting at head
		Vertex currentNode = head;
		
		// Catch case for empty tree
		if (head == null) {
			return false; // Tree not modified
		}
		
		// Catch case for BST with a single node
		if(currentNode.leftSide == null && currentNode.rightSide == null) {
			
			// If the head is the item to be removed:
			if (head.data.equals(item)) {
				
				// Empty head node
				head = null;
				return true; // List was modified
				
			} else {
				return false; // List was not modified
			}
		}
		
		// Loop through each element of the tree until a root is found:
		while(currentNode != null) {
			
			//System.out.println("While loop started"); // Test statement.
			
			// If the item is to the left (less than) currentNode:
			if(cmp.compare(item, currentNode.data) < 0) {
				
				//System.out.println("Left side found to " + currentNode.data); // Test statement
				
				// Move to the left if leftSide exists
				if (currentNode.leftSide != null) {
					
					//System.out.println("Moved to: " + currentNode.leftSide.data); // Test statement
					currentNode = currentNode.leftSide;
					
				} else {
					return false; // Reached the root of the tree, wasn't modified
				}
				
				// If a match is found:
				if(currentNode.data.equals(item)) {
					
					//System.out.println("NODE REMOVED TO LEFT: " + currentNode.data); // Test statement
					
					// If a root is going to be removed:
					if(currentNode.leftSide == null) {
						
						//System.out.println("Left side not found to: " + currentNode.data); // Test statement
						
						currentNode = null; // Empty currentNode
						return true; // Tree was modified
							
					} else {
						return false; // Root found with no match, tree wasn't changed
					}
				}
					
				// Rearrange the pointers around the current node and set the currentNode to null
				currentNode.cameFrom.leftSide = currentNode.leftSide;
				currentNode.leftSide.cameFrom = currentNode.cameFrom;
					
				currentNode = null; // Set the node to null, the list was modified so return true
				return true;
				
			} else if(cmp.compare(item, currentNode.data) > 0) {
				
					System.out.println("Right side found to " + currentNode.data); // Test statement
				
				// Move the tracker if the next position if it's valid
				if (currentNode.rightSide != null) {
					
					System.out.println("Moved to: " + currentNode.rightSide.data); // Test statement
					currentNode = currentNode.rightSide;
				} else {
					return false;
				}
				
				// If a match is found:
				if(currentNode.data.equals(item)) {
					
					System.out.println("NODE REMOVED TO RIGHT: " + currentNode.data); // Test statement
					
					// If a root is found:
					if(currentNode.rightSide == null) {
						
						System.out.println("Removing the root: " + currentNode.data);
						
						// Check if the root is a match, if it's not nothing is found
						if (currentNode.data.equals(item)) {
							
							System.out.println("match found");
							currentNode.cameFrom.rightSide = null;
							return true;
							
						} else {
							return false; // No match is found, it's not in the list
						}
					}
					
					// Rearrange the pointers around the current node and set the currentNode to null
					currentNode.cameFrom.rightSide = currentNode.rightSide;
					currentNode.rightSide.cameFrom = currentNode.cameFrom;
					
					currentNode = null; // Set the node to null, the list was modified so return true
					return true;
				}
			}
		}
		
		// If the code reaches a root:
		System.out.println("ERROR IN REMOVE FUNCTION");
		
		// If nothing was caught, nothing was changed
		return false;
	}
	
	
	
	@Override
	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	public boolean removeAll(Collection<? extends Type> items) {
		
		/*
		 * The containsAll method will not work here, since the set will be modified even if only one of the 
		 * members of the collection actually modify the data set
		 */
		
		// Flag to track if the remove method ever removed anything.
		boolean modified = false;
		
		// Simply loop through each item in the collection and run the remove method.
		for(Type item : items) {
			
			// Modifies the list merely by calling the remove method, so the evaluation happens simultaneously:
			if(this.remove(item)) {
				modified = true;
			}
		}
		
		return modified;
	}
	
	
	
	@Override
	/**
	 * @return: the number of items in this set.
	 */
	public int size() {
		
		// Creates an array list to evaluate the size of the number of elements.
		toArrayList();
		return arrayListHolder.size();
		
		/*size = 0;
		// Loops through each element in the list using an preOrder search
		int result = preOrder(head);
		
		System.out.println(result);
		return result; // Return stub.*/
	}
	
	
	
	/**
	 * -------------------------------------------------------------------------------------------------------------------
	 * Discarded preOrder traversal algorithm to count the size
	 * -------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * preOrder traversal algorithm helper method that recursively sorts through a list starting with the source value.
	 * 
	 * @param sourceVertex: Starts with the source of the tree
	 */
	private int preOrder(BinarySearchTree<Type>.Vertex sourceVertex) {
		
		Vertex currentVertex = sourceVertex;
		
		// Catch case for an empty tree
		if(currentVertex == null) {
			return 0;
		}
		
		// Checks to see if the currentVertex is a root, and if not, removes the call from the stack
		//  and moves on to the next step of the recursive traversal
		if(currentVertex.leftSide != null && currentVertex.rightSide != null) {
			
			// If the code has reached a root, add one to the code for the currentNode and reutrn the count.
			size++;
			return size;
		}
		
		
		// If there is a vertex the the left, rerun the code to the right if the left is clear
		if(currentVertex.leftSide != null) { 
			size++;
			preOrder(currentVertex.leftSide);
		}
		
		// If there is a vertex the the right, rerun the code to the right if the left is clear
		if(currentVertex.rightSide != null) { 
			size++;
			preOrder(currentVertex.rightSide);
		}
		
		return size;
	}
	
	
	
	@Override
	/**
	 * @return: an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	public ArrayList<Type> toArrayList() {
		
		// Clear the field list outside the method
		arrayListHolder = new ArrayList<Type>();
		//System.out.println("Head: " + head.data); // Test statement
		
		// Run the in order sort and return the modified list field
		inOrder(head);
		
		return arrayListHolder; // Return stub
	}
	
	
	
	/**
	 * inOrder traversal algorithm helper method that recursively sorts through a list starting with the source value.
	 * 
	 * @param sourceVertex: Starts with the source of the tree
	 * @return: ArrayList with each element added to it
	 */
	private void inOrder(BinarySearchTree<Type>.Vertex sourceVertex) {
		
		/*
		// Testing statements to know exactly what the code is doing.
		System.out.println("Current vertex: " + sourceVertex.data);
		
		if(sourceVertex.leftSide != null) {
			System.out.println("LEFT DATA: " + sourceVertex.leftSide.data); 
		} else {
			System.out.println("No left vertex of the sourceVertex: " + sourceVertex.data);
		}
		
		if(sourceVertex.rightSide != null) {
			System.out.println("RIGHT DATA: " + sourceVertex.rightSide.data); 
		} else {
			System.out.println("No right vertex of the sourceVertex: " + sourceVertex.data);
		}
		*/
		
		// Catch case for empty value
		if (sourceVertex == null) {
			return;
		}
		
		
		Vertex currentVertex = sourceVertex; // Tracking vertex.
		
		// Checks to see if the currentVertex is a root, and if not, removes the call from the stack
		//  and moves on to the next step of the recursive traversal
		if(currentVertex.leftSide == null && currentVertex.rightSide == null) {
			
			arrayListHolder.add(currentVertex.data);
			return;
		}
		
		// If there is a vertex to the left, repeat this code on that vertex.
		if(currentVertex.leftSide != null) {
			//System.out.println("successful left side insert"); // Testing statement
			inOrder(currentVertex.leftSide);
		}
		
		// If there is a vertex to the right, after the left has been checked and sorted, 
		//  make a recursive call on the right
		if(currentVertex.rightSide != null) {
			inOrder(currentVertex.rightSide);
		}
		
		// If every child vertex has been sorted, add the current node to the list and return the result.
		arrayListHolder.add(currentVertex.data);
		return;
	}
	
	
	
	/**
	 * @return: Head of the tree
	 */
	public Vertex getHead() {
		return head;
	}
	
	
	
	/**
	 * inOrder traversal algorithm helper method for printing out the graph.
	 */
	public void binaryTreeToString(Vertex sourceVertex) {
		
		Vertex currentVertex = sourceVertex; // Tracking vertex for the BST
	
		int count = 0; // Empty value that will be returned to accumulate the number of nodes.
	
		// Catch case for an empty tree
		if(currentVertex == null) {
			return;
		}
		
		// Checks to see if the currentVertex is a root, and if not, removes the call from the stack
		//  and moves on to the next step of the recursive traversal
		if(currentVertex.leftSide != null || currentVertex.rightSide != null) {
			
			// If the code has reached a root, add one to the code for the currentNode and reutrn the count.
			System.out.print("\"" + currentVertex.data + "\" -> ");
		}
		
		
		// If there is a vertex the the left, rerun the code to the right if the left is clear
		if(currentVertex.leftSide != null) { 
			
			System.out.println("\"" + currentVertex.leftSide.data + "\"");
			binaryTreeToString(currentVertex.leftSide);
		}
		
		// If there is a vertex the the right, rerun the code to the right if the left is clear
		if(currentVertex.rightSide != null) { 
			
			System.out.println("\"" + currentVertex.rightSide.data + "\"");
			binaryTreeToString(currentVertex.rightSide);
		}
	}
	
	
/*================================================= VERTEX CLASS =========================================================*/
	
	
	/**
	 * This class represents a vertex (AKA node) in a directed graph. The vertex is
	 * not generic, assumes that a string name is stored there. Includes a set of
	 * nodes
	 * 
	 * @author Erin Parker, Everett Oglesby, & Parker Catten
	 * @version March 20, 2022
	 */
	public class Vertex {

		// used to id the Vertex
		private Type data;

		// Right and left edges to track
		private Vertex leftSide = null;
		private Vertex rightSide = null;
		
		// Tracks previous vertex that points from it
		public Vertex cameFrom;
		
		//visited flag
		public boolean visited = false;
		
		
		
		/**
		 * Creates a new Vertex object, using the given name.
		 * 
		 * @param name - string used to identify this Vertex
		 */
		Vertex(Type data) {
			this.data = data;
		}
		
		
		
		/**
		 * @return the string used to identify this Vertex
		 */
		public Type getData() {
			return data;
		}
		
		
		
		/**
		 * Adds a directed edge from this Vertex to another.
		 * 
		 * @param otherVertex - the Vertex object that is the destination of the edge
		 */
		public <T extends Comparable<? super T>> void addEdge (Vertex otherVertex) {
			
			if (cmp.compare(this.data, otherVertex.data) > 0) {
				
				// Gets the new edge and sets its origin to this vertex.
				otherVertex.cameFrom = this;
				leftSide = otherVertex;
				
			} else {
				
				// Gets the new edge and sets its origin to this vertex.
				otherVertex.cameFrom = this;
				rightSide = otherVertex;
			}
		}
	}
}