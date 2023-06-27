package assignment07;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * This class represents a vertex in a directed graph. Uses a string to store its data
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 06:27:23
 */
public class Vertex {

	// used to id the Vertex
	private String name;
	

	// adjacency list
	private LinkedList<Edge> adj;
	
	// Tracks previous vertex that points from it
	public Vertex cameFrom;
	
	//visited flag
	public boolean visited = false;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(String name) {
		
		this.name = name;
		this.adj = new LinkedList<Edge>();
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex otherVertex) {
		
		adj.add(new Edge(otherVertex));
		// Gets the new edge and sets its origin to this vertex.
		adj.get(adj.size()-1).setCameFrom(this);
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}
	
	
	
	/**
	 * Accessor method for finding adjacent edges
	 * @return adj
	 */
	public List<Edge> getAdjacent() {
		return adj;
	}
	
	
	
	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + name + " adjacent to vertices ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}