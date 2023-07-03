package assignment07;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a vertex in a directed graph. Uses a string to store its data
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 06:27:23
 */
public class Vertex<Type> {
	
	// Fields
	private Type data; // Data and key of vertex for the Graph's HashMap
	public Vertex<Type> cameFrom; // Tracks previous vertex that points from it
	private LinkedList<Edge<Type> > adj; // Adjacency list
	public boolean visited = false; // Visited flag

	
	/**
	 * Constructor that creates a new Vertex object, stores given data within it
	 * 
	 * @param data: String stored in this Vertex
	 */
	public Vertex(Type data) {
		
		this.data = data;
		this.adj = new LinkedList<Edge<Type> >();
	}
	
	
	
	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param dstVertex: the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<Type> dstVertex) {
		
		adj.add(new Edge<Type>(dstVertex)); // Add the new edge to the adjacency list
		
		// Gets the new edge from the end of the adjacency list and sets its origin to this vertex
		adj.get( adj.size()-1 ).setSource(this);
	}
	
	
	
	/**
	 * @return: The string stored in this Vertex
	 */
	public Type getData() { return data; }
	
	
	
	/**
	 * @return adj: List of edges around the vertex.
	 */
	public List< Edge<Type> > getAdjacent() { return adj; }
	
}