package assignment07;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents the graph object the utility class will use. This graph uses a HashMap to track
 * the source and destination vertices using key/value pairs, which was inspired by the example presented
 * in lecture.
 * 
 * @author: Parker Catten & Everett Oglesby
 * @version: 06:27:23
 */
public class Graph<Type> {

	// Fields
	HashMap<Type, Vertex<Type>> allVertices; // HashMap of all the source/destination vertices
	
	
	/**
	 * Constructor that sets up relevant fields
	 */
	public Graph() {
		allVertices = new HashMap<Type, Vertex<Type>>();
	}
	
	
	
	/**
	 * Adds a directed edge between the srcData's vertex to the dstData's (if they exist) to the
	 * HashMap. If either do not exist, the function will make new vertices to house them
	 * 
	 * @param srcData: Value stored in the source vertex
	 * @param dstData: Value for the destination vertex
	 */
	public void addEdge(Type srcData, Type dstData) {
		
		// Vertices that represent the srcData and dstData
		Vertex<Type> srcVertex = null;
		Vertex<Type> dstVertex = null;
		
		// If the vertex is already represented:
		if(allVertices.containsKey(srcData)) {
			
			srcVertex = allVertices.get(srcData); // Use its vertex.
		
		} else { // If it's not, create a new vertex for the data and add it.
			
			srcVertex = new Vertex<Type>(srcData);
			allVertices.put(srcData, srcVertex); // Add it with the data it stores
		}
		
		
		// Next, check the HashMap for dstData and its vertex
		// If the vertex is already represented:
		if(allVertices.containsKey(dstData)) {
			
			dstVertex = allVertices.get(dstData); // Use its vertex
		
		} else { // If it's not, create a new vertex for the data and add it.
			
			dstVertex = new Vertex<Type>(dstData);
			allVertices.put(dstData, dstVertex); // Add it with the data it stores
		}
		
		// Add the edge:
		srcVertex.addEdge(dstVertex);
	}
	
	
	
	/**
	 * @return: DOT String for online tools like webgraphiz.com
	 */
	public String dotGraph() {
		
		// Empty String to be added to and returned
		String DOT = "";
		
		// Loop through each vertex's adjacent values and adds a line of the source/destination in diagraph format
		for(Vertex<Type> vertex : allVertices.values()) { // For each vertex:
			
			List<Edge<Type>> edges = vertex.getAdjacent();
			
			// Iterate through each vertex's edges
			for(Edge<Type> edge : edges)  {
				DOT += "   \"" + vertex.getData() + "\" -> \"" + edge.getDestination().getData() + "\"\n";
			}
		}
		
		// Return the built String with the closing bracket
		return DOT + "}";
	}
	
	
	
	/**
	 * @return: the HashMap this graph uses
	 */
	public HashMap<Type, Vertex<Type>> getVertices() { return allVertices; }
	
	
	
	/**
	 * @return: Console-friendly representation of the graph
	 */
	public String toString() {
		
		// Starts with a blank String that gets added to
		String result = "";
		
		// Add each vertex's toString value on its own line to the String
		for(Vertex<Type> vertex : allVertices.values()) {
			result += vertex.getData() + "\n";
		}
		
		// Return the String
		return result.toString();
	}
	
}