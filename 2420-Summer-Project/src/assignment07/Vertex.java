package assignment07;

import java.util.LinkedList; 
import java.util.List;

/**
 * This class represents a vertex in a directed graph. Uses a string to store its data
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 06:27:23
 */
public class Vertex {
	
	// Fields
	private String data; // Data and key of vertex for the Graph's HashMap
	public Vertex cameFrom; // Tracks previous vertex that points from it
	private LinkedList<Edge> adj; // Adjacency list
	public boolean visited = false; // Visited flag

	
	/**
	 * Constructor that creates a new Vertex object, stores given data within it
	 * 
	 * @param data: String stored in this Vertex
	 */
	public Vertex(String data) {
		
		this.data = data;
		this.adj = new LinkedList<Edge>();
	}
	
	
	
	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param dstVertex: the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex dstVertex) {
		
		adj.add(new Edge(dstVertex)); // Add the new edge to the adjacency list
		
		// Gets the new edge from the end of the adjacency list and sets its origin to this vertex
		adj.get( adj.size()-1 ).setSource(this);
	}
	
	
	
	/**
	 * @return: The string stored in this Vertex
	 */
	public String getData() { return data; }
	
	
	
	/**
	 * @return adj: List of edges around the vertex.
	 */
	public List<Edge> getAdjacent() { return adj; }
	
}