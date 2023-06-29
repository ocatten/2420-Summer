package assignment07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the graph object the utility class will use. This graph uses a HashMap to track
 * the source and destination vertices using key/value pairs, which was inspired by the example presented
 * in lecture.
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version: 06:27:23
 */
public class Graph {

	// Fields
	HashMap<String, Vertex> allVertices; // HashMap of all the source/destination vertices
	
	
	/**
	 * Constructor that sets up relevant fields
	 */
	public Graph() {
		allVertices = new HashMap<String, Vertex>();
	}
	
	
	
	/**
	 * Adds a directed edge between the srcData's vertex to the dstData's (if they exist) to the
	 * HashMap. If either do not exist, the function will make new vertices to house them
	 * 
	 * @param srcData: String value stored in the source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(String srcData, String dstData) {
		
		// Vertices that represent the srcData and dstData
		Vertex srcVertex = null;
		Vertex dstVertex = null;
		
		// If the vertex is already represented:
		if(allVertices.containsKey(srcData)) {
			
			srcVertex = allVertices.get(srcData); // Use its vertex.
		
		} else { // If it's not, create a new vertex for the data and add it.
			
			srcVertex = new Vertex(srcData);
			allVertices.put(srcData, srcVertex); // Add it with the data it stores
		}
		
		
		// Next, check the HashMap for dstData and its vertex
		// If the vertex is already represented:
		if(allVertices.containsKey(dstData)) {
			
			dstVertex = allVertices.get(dstData); // Use its vertex
		
		} else { // If it's not, create a new vertex for the data and add it.
			
			dstVertex = new Vertex(dstData);
			allVertices.put(dstData, dstVertex); // Add it with the data it stores
		}
		
		// Add the edge:
		srcVertex.addEdge(dstVertex);
	}
	
	
	
	/**
	 * @return: DOT String for online tools like webgraphiz.com
	 */
	public String dotGraph() {
		
		// This method's return value needs to use a StringBuilder since the final value will need to be extensively
		//  modified and added to. Giving the String mutable properties will simplify the process
		StringBuilder DOT = new StringBuilder("digraph d {\n");
		
		// Loop through each vertex's adjacent values and adds a line of the source/destination in diagraph format
		for(Vertex vertex : allVertices.values()) { // For each vertex:
			
			List<Edge> edges = vertex.getAdjacent();
			
			// Iterate through each vertex's edges
			for(Edge edge : edges)  {
				DOT.append("   \"" + vertex.getData() + "\" -> \"" + edge.getDestination().getData() + "\"\n");
			}
		}
		
		// Return the built String with the closing bracket
		return DOT.toString() + "}";
	}
	
	
	
	/**
	 * @return: the HashMap this graph uses
	 */
	public HashMap<String, Vertex> getVertices() { return allVertices; }
	
	
	
	/**
	 * @return: Console-friendly representation of the graph
	 */
	public String toString() {
		
		// Starts with a blank String that gets added to
		String result = "";
		
		// Add each vertex's toString value on its own line to the String
		for(Vertex vertex : allVertices.values()) {
			result += vertex.getData() + "\n";
		}
		
		// Return the String
		return result.toString();
	}
	
}