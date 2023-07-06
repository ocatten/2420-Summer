package assignment08;

/**
 * This class represents a generic binary search tree. It implements the provided SortedSet interface given by the instructor.
 * This tree also uses an internal Vertex class to house the generic data.
 * 
 * @author: Everett Oglesby & Parker Catten
 */

import java.util.ArrayList; 
import java.util.Collection;
import java.util.Comparator;
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
			size++;
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
					
					size++;
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
					
					size++;
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
		
		// Uses postOrder to clear the list, empties head field, clear the size field
		postOrderClear(head);
		head = null;
		size = 0;
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
		/*if (head == null) {
			return false; // Tree not modified
		}*/
		
		// Catch case for BST with a single node
		/*if(currentNode.leftSide == null && currentNode.rightSide == null) {
			
			// If the head is the item to be removed:
			if (head.data.equals(item)) {
				
				// Empty head node, modify size field
				size--;
				head = null;
				return true; // List was modified
				
			} else {
				return false; // List was not modified
			}
		}*/
		
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
					
					//System.out.println("NODE REMOVED TO LEFT OF: " + currentNode.data); // Test statement
					
					// If a root is going to be removed:
					if(currentNode.leftSide == null && currentNode.rightSide == null) {
						
						//System.out.println("Removing the root: " + currentNode.data); // Test statement
						
						currentNode.cameFrom.leftSide = null;

						size--; // Adjust size
						currentNode = null; // Empty currentNode
						return true; // List was modified.
						
					
					// If it's not a root but has a node to the right, replace currentNode with the greater one
					} else if (currentNode.rightSide != null) {
						
						// Set the right node's came from and left side's to currentNode's and currentNode's cameFrom pointer to replacement
						currentNode.rightSide.cameFrom = currentNode.cameFrom;
						currentNode.cameFrom.leftSide = currentNode.rightSide;
						currentNode.rightSide.leftSide = currentNode.leftSide;
						
						size--; // Adjust size
						currentNode = null; // Empty currentNode
						return true; // List was modified.
					
					} else { // If the leftSide is not null:
						
						// Rearrange pointers and empty currentNode
						currentNode.cameFrom.leftSide = currentNode.leftSide;
						currentNode.leftSide.cameFrom = currentNode.cameFrom;
							
						size--; // Adjust size
						currentNode = null; // Empty currentNode
						return true; // List was modified.
					}
				}
					
			// If the item is to the right (greater than) currentNode:
			} else if(cmp.compare(item, currentNode.data) > 0) {
					
				//System.out.println("Right side found to " + currentNode.data); // Test statement
					
				// Move to the right if rightSide exists
				if (currentNode.rightSide != null) {
						
					//System.out.println("Moved to: " + currentNode.rightSide.data); // Test statement
					currentNode = currentNode.rightSide;
						
				} else {
					return false; // Reached the root of the tree, wasn't modified
				}
					
				// If a match is found:
				if(currentNode.data.equals(item)) {
						
					//System.out.println("NODE REMOVED TO RIGHT OF: " + currentNode.data); // Test statement

					// If a root is going to be removed:
					if(currentNode.leftSide == null && currentNode.rightSide == null) {
					
						//System.out.println("Removing the root: " + currentNode.data);
						currentNode.cameFrom.rightSide = null;
						currentNode = null;
						size--;
						return true; // Tree was modified
				
					// If it's not a root but has a node to the right, replace currentNode with the greater one
					} else if (currentNode.rightSide != null) {
					
						// Set the right node's came from and left side's to currentNode's and currentNode's cameFrom pointer to replacement
						currentNode.rightSide.cameFrom = currentNode.cameFrom;
						currentNode.cameFrom.rightSide = currentNode.rightSide;
						currentNode.rightSide.leftSide = currentNode.leftSide;
					
						size--; // Adjust size
						currentNode = null; // Empty currentNode
						return true; // List was modified.
				
					} else { // If the leftSide is not null:
					
						// Rearrange pointers and empty currentNode
						currentNode.cameFrom.rightSide = currentNode.leftSide;
						currentNode.leftSide.cameFrom = currentNode.cameFrom;
						
						size--; // Adjust size
						currentNode = null; // Empty currentNode
						return true; // List was modified.
					}
				}
				
			//If no match on either side, then check if the head is equal to the item given
			} else if (cmp.compare(item, currentNode.data) == 0) {
				
				//System.out.println("node equal to head"); // Test statement
				
				// Check if a node exists to the right side of the head
				if(currentNode.rightSide != null) {
					
					//System.out.println("Right side exists"); // Test statement
					
					// If the left side and the right side exist:
					if(currentNode.leftSide != null) {
					
						//System.out.println("Left side exists"); // Test statement
						
						// Tracker for find the left most node
						Vertex leftmostSideNode = currentNode.rightSide;
						
						// Move to the lowest possible value of the right subtree
						while(leftmostSideNode.leftSide != null) {
							leftmostSideNode = leftmostSideNode.leftSide;
						}
						
						// Rearrange pointers to make left subtree point to lowest right subtree
						leftmostSideNode.leftSide = head.leftSide;
						leftmostSideNode.leftSide.cameFrom = leftmostSideNode;
						
						// Move the head to the right to its new head, empty tracker
						head = currentNode.rightSide;
						currentNode = null;
						size--; // Decrement size and return true for a modified BST
						return true;
					
						
					} else { // If there is ONLY a node on the right:
						
						head = currentNode.rightSide; // Move the head to the right
						
						// Clear the currentNode, decrement the size, list was modified so return true
						currentNode = null;
						size--;
						return true;
					}
					
				// If only the left side exists:
				} else if(currentNode.leftSide != null) {
					
					// Move the new head to the left
					head = currentNode.leftSide;
					
					// Clear the currentNode, decrement the size, list was modified so return true
					currentNode = null;
					size--;
					return true;
				
				} else { // If there is only a single node, empty the head and return
					
					head = null;
					
					// Clear the currentNode, decrement the size, list was modified so return true
					currentNode = null;
					size--;
					return true;
				}
			}
		}
		
		// Catch case for an empty tree.
		//System.out.println("Empty tree."); // Test statement
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
		
		// Flag to track if the remove method ever removed anything.
		boolean modified = false;
		
		// Loop through each item in collection
		for(Type item : items) {
			
			// Call remove() for each item
			if(this.remove(item)) {
				modified = true; // If an item was ever removed, list was modified (adjust flag)
			}
		}
		
		return modified;
	}
	
	
	
	@Override
	/**
	 * @return: the number of items in this set tracked by the size field.
	 */
	public int size() { return size; }
	
	
	
	@Override
	/**
	 * @return: an ArrayList containing all of the items in this set, in sorted order.
	 */
	public ArrayList<Type> toArrayList() {
		
		// Reset the field in the class
		arrayListHolder = new ArrayList<Type>();
		//System.out.println("HEAD: " + head.data); // Test statement
		
		// Run inOrder sort for creating the list
		inOrderListBuilder(head);
		
		return arrayListHolder; // Return the modified list
	}
	
	
	
	/**
	 * Traversal helper method that uses a recursive inOrder sort to build a list of the tree
	 * 
	 * @param sourceVertex: Starts with the source of the tree
	 * @return: ArrayList with each element added to it
	 */
	private void inOrderListBuilder(BinarySearchTree<Type>.Vertex sourceVertex) {
		
		// Test statements
		/*System.out.println("Current vertex: " + sourceVertex.data);
		
		if(sourceVertex.leftSide != null) {
			//System.out.println("LEFT DATA: " + sourceVertex.leftSide.data); 
		} else {
			//System.out.println("No left vertex of the sourceVertex: " + sourceVertex.data);
		} if(sourceVertex.rightSide != null) {
			//System.out.println("RIGHT DATA: " + sourceVertex.rightSide.data);
		} else {
			//System.out.println("No right vertex of the sourceVertex: " + sourceVertex.data);
		}*/
		
		// Base case for empty parameter
		if (sourceVertex == null) {
			return;
		}
		
		// Tracking node starting at parameter
		Vertex currentVertex = sourceVertex; 
		
		// Checks to see if currentVertex is a root:
		if(currentVertex.leftSide == null && currentVertex.rightSide == null) {
			
			// If it's a root, add it to the arrayListHolder
			arrayListHolder.add(currentVertex.data);
			return; // Remove call from call stack
		}
		
		// If there's a left child, run again on the left child before completing current call
		if(currentVertex.leftSide != null) {
			//System.out.println("successful left side insert"); // Testing statement
			inOrderListBuilder(currentVertex.leftSide);
		}
		
		// If there's a right child, run again on the right child before completing current call
		if(currentVertex.rightSide != null) {
			inOrderListBuilder(currentVertex.rightSide);
		}
		
		// If every child vertex has been added, complete current call
		arrayListHolder.add(currentVertex.data);
		//return; // Remove from stack.
	}
	
	
	
	/**
	 * @return: Head of the tree
	 */
	public Vertex getHead() { return head; }
	
	
	
	/**
	 * Class method to convert the BST to a console-friendly String for tools like webgraphiz.com
	 * 
	 *  @param sourceVertex: Starting point for method call
	 */
	public void toString(Vertex sourceVertex) {
		
		// Tracking vertex for BST starting at param
		Vertex currentVertex = sourceVertex; 
	
		// Catch case for empty tree
		if(currentVertex == null) {
			return;
		}
		
		// If currentVertex is a source that points to something:
		if(currentVertex.leftSide != null || currentVertex.rightSide != null) {
			
			// Add the currentVertex to a line pointing to something else
			System.out.print("\"" + currentVertex.data + "\" -> ");
		}
		
		
		// If there is a left child, add it to the line and rerun to the left
		if(currentVertex.leftSide != null) { 
			
			System.out.println("\"" + currentVertex.leftSide.data + "\"");
			toString(currentVertex.leftSide); // Add left side to the call stack
		}
		
		// If there is a right child, add it to the line and rerun to the right
		if(currentVertex.rightSide != null) { 
			
			System.out.println("\"" + currentVertex.rightSide.data + "\"");
			toString(currentVertex.rightSide); // Add right side to call stack
		}
	}
	
	
/*========================================================== VERTEX CLASS ========================================================================*/
	
	
	/**
	 * This class represents a node or Vertex in the BST. Houses generic data.
	 * 
	 * @author Everett Oglesby, & Parker Catten
	 * @version 07:03:23 SUM-23 2420_001
	 */
	public class Vertex {

		// Fields
		private Type data; // Data housed in node
		// Right and left edges to track
		private Vertex leftSide = null;
		private Vertex rightSide = null;
		public Vertex cameFrom; // Tracks previous vertex that points from it
		public boolean visited = false; // Visited flag
		
		
		/**
		 * Creates a new Vertex object with the given data
		 * 
		 * @param data: Object within the vertex
		 */
		Vertex(Type data) {
			this.data = data;
		}
		
		
		
		/**
		 * @return data used in the Vertex
		 */
		public Type getData() { return data; }
		
		
		
		/**
		 * Adds a directed edge from this Vertex to another.
		 * 
		 * @param otherVertex: Vertex object that is the destination of the edge
		 */
		public <T extends Comparable<? super T>> void addEdge (Vertex otherVertex) {
			
			// If the new vertex belongs to the left (less than):
			if (cmp.compare(this.data, otherVertex.data) > 0) {
				
				// Gets the new edge and sets its origin to this vertex.
				otherVertex.cameFrom = this;
				leftSide = otherVertex;
				
			} else { // If the new vertex belongs to the right (greater than):
				
				// Gets the new edge and sets its origin to this vertex.
				otherVertex.cameFrom = this;
				rightSide = otherVertex;
			}
		}
	}
}