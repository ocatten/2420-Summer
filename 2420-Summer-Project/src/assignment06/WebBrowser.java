/**
 * This class represents the web app and the forward/back button to traverse webpages
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:22:23 CS-2420_001 SUM-2023
 */
package assignment06;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WebBrowser {
	
	
	// Fields
	private LinkedListStack<URL> backButton;
	private LinkedListStack<URL> forwardButton;
	
	
	/**
	 * Constructor
	 */
	public WebBrowser() {
		
		backButton = new LinkedListStack<URL>();
		forwardButton = new LinkedListStack<URL>();
	}
	
	
	/**
	 * Constructor that can be created with URLs. The top of the stack is the current webpage, 
	 * ordered for most to least recently visited
	 * 
	 * @param history: List of URLs going from most to least recently visited
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		
		// Fields
		backButton = new LinkedListStack<URL>();
		forwardButton = new LinkedListStack<URL>();
		
		// Add each URL in the parameter list to the stack
		//Object[] historyArray = history.toArray();
		
		Iterator<URL> itr = history.iterator();
			
		// While the iterator has nodes to traverse:
		while(itr.hasNext()) {
			backButton.push(itr.next());
		}
		
		//System.out.println(historyArray); // Test statement
		//System.out.println("check"); // Test statement
		//System.out.println(historyArray.length); // Test statement
		
		// The first item is the most recently visited so the first item added should be the last one,
		//  get the URLS within the nodes
		//for(int i = historyArray.length-1; i >= 0; i--) {
			
			//backButton.push(historyArray[i]); // Add history to the back button.
		//}
	}
	
	
	
	/**
	 * Visits a new webpage by adding a node to the stack and clears the forward button
	 * 
	 * @param webpage: URL that's added to the stack
	 */
	public void visit(URL webpage) {
			
		forwardButton.clear();
		backButton.push(webpage);
	}
	
	
	
	/**
	 * Adds the current webpage to the forward button and pops the back button and makes that
	 * the current webpage
	 * 
	 * @return: The new current webpage
	 * @throws NoSuchElementException: If the backButton has no URL's
	 */
	public URL back() throws NoSuchElementException {
		
		// Catch case for empty backButton
		if(backButton.isEmpty()) {
			
			throw new NoSuchElementException(); // Throw if empty
		}
		
		
		URL temp = (URL)backButton.peek(); // New current webpage from the top of the backButton stack
		forwardButton.push(temp);
		backButton.pop(); // Removes current webpage from the history and adds it to the forward button
		
		return temp;
	}
	
	
	
	/**
	 * Sets the browser a page forward
	 * 
	 * @return: New current URL
	 */
	public URL forward() throws NoSuchElementException {
		
		// Catch case if the forwardButton has no URL's
		if(forwardButton.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// Delete the element on the forwardButton and add it to the backButton
		URL temp = (URL)forwardButton.peek(); // Element for the return value.
		
		forwardButton.pop();
		backButton.push(temp);
		return temp;
	}
	
	
	
	/**
	 * Returns a SinglyLinkedList with each URL the browser has visited [O(N)]
	 * 
	 * @return: SinglyLinkedList<URL>: A SinglyLinkedList of the history
	 */
	public SinglyLinkedList<URL> history() {
		
		// Creates a new LinkedList to return
		SinglyLinkedList<URL> webHistory = backButton.getList();
		
		// Add each Node to the webHistory
		for(int i = 0; i < backButton.size(); i++) {
			
			webHistory.insertFirst( (URL) backButton.getList().get(i) );
		}
		
		return webHistory;
	}
	
	
	
	/**
	 * @return: backButton stack
	 */
	public LinkedListStack<URL> getBack() {
		return backButton;
	}
	
	
	
	/**
	 * @return: forwardButton stack
	 */
	public LinkedListStack<URL> getForward() {
		return forwardButton;
	}
	
}
