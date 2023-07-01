package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Eric Heisler, Parker Catten, & Everett Oglesby
 * @version June 20, 2023
 */
public class GraphUtility {
	

	
	/**
	 * Checks if there is a path between two Vertices, returns a boolean for the existence of a path.
	 * 
	 * @param <Type> The Vertex object.
	 * @param sources: List of vertices that have no vertices pointing to them
	 * @param destinations: Vertices that have vertices pointing to them
	 * @param srcData: source object to search with
	 * @param dstData: destination object to search for.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		// Suite of test statements for autograder
		System.out.println("srcData: " + srcData);
		System.out.println("dstData " + dstData);
		System.out.println("srcs: " + sources);
		System.out.println("dsts: " + destinations);
		
		// Catch case for an illegal argument, checks if the parameters actually exist in the lists
		if( !(sources.contains(srcData) && destinations.contains(dstData)) ) {
			throw new IllegalArgumentException();
		}
		
		// Cast the generic destinations to vertices for testing purposes
		@SuppressWarnings("unchecked")
		LinkedList<Vertex> destinationVerticies = (LinkedList<Vertex>)destinations;
				
		//Set each vertex to not visited
		for(Vertex vertex: destinationVerticies) {
			vertex.visited = false;
		}
				
		// begin the DFS
		LinkedList<Type> result = DFS(destinations, srcData, dstData);
		//System.out.println("DFS RESULT VALUE: " + result); // Test statement
		return (result != null);
	}
	
	
	
	/**
	 * Uses the depth first search to find a target in a graph from a source point. Creates
	 * a linked list of vertices to represent the path taken. 
	 * 
	 * @param graph: Set of vertices to search through
	 * @param source: source vertex to search with
	 * @param target: Value the search is looking for.
	 * @return LinkedList<Type>: LinkedList of the path created by the recursive calls.
	 */
	public static <Type> LinkedList<Type> DFS(List<Type> graph, Type source, Type target) {
		
		// Cast the source and target to Vertex objects
		Vertex sourceVertex = (Vertex) source;
		//Vertex targetVertex = (Vertex) target;
		
		// Set the current source vertex to visited
		sourceVertex.visited = true;

		// Create a new LinkedList to show the pathway to the target
		LinkedList<Type> returnList = new LinkedList<Type>();
		returnList.add(source);
		
		// If the source is at the target, return the list
		if(source.equals(target)){
			
			return returnList;
		}
		
		// If not travel to the next vertex
		for(Edge edge : sourceVertex.getAdjacent()) {
			
			Vertex vertex = edge.getDestination();
			
			// If the vertex hasn't been visited yet, add it to the list if it's not null
			if(!vertex.visited) {
				
				@SuppressWarnings("unchecked")
				LinkedList<Type> result = DFS(graph, ((Type)vertex), target);
				
				// Catch case to avoid null pointer exception
				if(result != null) {
					
					result.add(0, source);
					return returnList;
				}
				
			}
		}
		
		// Else return null
		return null;
	}
	
	
	
	/**
	 * This function will perform a breadth search for the desired destination node and find the 
	 * shortest path.
	 * 
	 * @param <Type> Vertex
	 * @param sources: List of vertices that have no vertices pointing to them
	 * @param destinations: Vertices that have vertices pointing to them
	 * @param srcData: source object to search with
	 * @param dstData: destination object to search for.
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		// Catch case for an illegal argument, checks if srcData and dstData are actually in the lists
		if( !(sources.contains(srcData) && destinations.contains(dstData)) ) {
			throw new IllegalArgumentException();
		}
		
		// Call the search method.
		return BFS(destinations, sources, srcData, dstData);
	}
	
	
	
	/**
	 * Breadth search that finds the shortest possible path to a node by adding each adjacent node to the starting
	 * position through the adjacent nodes adding each layer to a queue. This algorithm will always find the shortest
	 * path
	 * 
	 * @param edges: List of the vertices that are connected
	 * @param start: Starting vertex to search the adjacent vertices
	 * @param target: Target of the search
	 */
	public static <Type> List<Type> BFS(List<Type> nodes, List<Type> edges, Type srcData, Type dstData) {
		
		// Makes the queue for the search
		Queue<Vertex> bfsQueue = new LinkedList<Vertex>();
		
		// Go through each node and cast it to a vertex.
		for(Type node : nodes) {
			
			// Casts each node into a vertex to access the class methods and variables
			Vertex vertexNode = (Vertex)node;
			
			// Sets each to unvisited.
			vertexNode.visited = false;
		}
		
		// Casts the starting vertex to make it visited, adds it to the queue.
		Vertex startVertex = (Vertex)srcData;
		bfsQueue.add(startVertex);
		
		// While there are still vertices in the queue:
		while(bfsQueue.size() != 0) {
			
			// Set the starter to the point on the poll and then make it visited.
			Vertex starter= bfsQueue.poll();
			starter.visited = true;
			
			// Catch case if the target has been found. Reconstructs the path and returns it
			if(starter.equals(dstData)) {
				return reconstructPath(nodes, edges, dstData, srcData); // Calls reconstructor helper method
			}
			
			// Check each adjacent vertex:
			for(Edge neighbor : starter.getAdjacent()) {
				
				// If the neighbor hasn't been visited:
				if(!neighbor.getDestination().visited) {
					
					//System.out.println("neighbor vertex: " + neighbor.getOtherVertex().getName()); // Test statement
					neighbor.getDestination().cameFrom = starter; // Sets the origin to this vertex as shown in lecture
					// The Vertex is visited if the queue contains it.
					neighbor.getDestination().visited = bfsQueue.contains(neighbor.getDestination());
					
					// Add it to the queue.
					bfsQueue.add(neighbor.getDestination());
				}
			}
		}
		
		// Catch case to signify that the code has failed.
		System.out.println("Now returning:");
		return null; // Return stub.
	}
	
	
	
	/**
	 * Helper method that reconstructs the path of the BFS.
	 * 
	 * <Type>: Typically a Vertex, data type of points on the graph
	 * @param nodes: List of nodes that the method pulls from
	 * @param edges: List of edges
	 * @param target: Destination we are building from
	 * @param start: Source of the path where it begins
	 */
	@SuppressWarnings("unchecked")
	public static <Type> List<Type> reconstructPath(List<Type> nodes, List<Type> edges, Type target, Type start){
		
		List<Vertex> tempPath = new LinkedList<Vertex>(); // Makes the list to be returned
		
		// Loop through each cameFrom vertex as long as the chain hasn't reached the starting position:
		for(Vertex node = (Vertex)target; node != start; node = node.cameFrom) {
			
			//System.out.println(node.cameFrom.getName()); // Test statement
			tempPath.add(node); // Add it to the chain.
		}
		
		// Add the first element, adds the chain in reverse for proper order, and then returns it.
		List<Vertex>returnPath = new LinkedList<Vertex>();
		returnPath.add((Vertex) start);
		
		for(int i = tempPath.size() - 1; i >= 0; i--) {
			returnPath.add( tempPath.get(i) );
		}
		
		return (List<Type>)returnPath; // Cast this back to the generic data type
	}
	
	
	
	/**
	 * Uses a topological sort algorithm to generate a sorted ordering of the vertices in the graph. Each 
	 * graph have more than one valid ordering.
	 * 
	 * @param <Type>: Type of object housing data, the vertex
	 * @param sources: Source vertices
	 * @param destinations: Destination vertices
	 * @return: Sorted list
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		
		 // Create a queue to store vertices in the 0 indegree
        Queue<Type> queue = new LinkedList<>();

        // Enqueue the vertices
        for (Type vertex : sources) {
        	
            queue.add(vertex);
            
            // Make sure the current object's visited flag is true.
            Vertex currVertex = (Vertex) vertex;
            currVertex.visited = true;
        }

        // Perform topological sorting
        List<Type> sortedOrder = new ArrayList<>();
        
        
        // While there are still objects in the queue:
        while (!queue.isEmpty()) {
        	
        	//Keep a tracker for the current vertex and remove it from the queue
            Type vertex = queue.remove();
            sortedOrder.add(vertex); // Add it to a sorted list
            Vertex currVertex = (Vertex) vertex;
            
            //The amount of neighbors 
            int size = sources.size();
            
            //Check each neighbor if its been visited yet
            //if not add it to the queue
            for(Edge neighbor : currVertex.getAdjacent()) {
            	
            	// If something isn't visited make sure it's enqueued and flagged
            	if(neighbor.src.visited == false) {
            		
                	queue.add((Type) neighbor.src);
                	neighbor.src.visited = true;
            	}
            }
        }

        //Check if all vertices are in the sorted order
        if (sortedOrder.size() != sources.size()) {
            throw new IllegalArgumentException("Topological sorting is not possible.");
        }
        
        //Return the vertices in the sorted order
        return sortedOrder;
	}
	
	
	
	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {
		
		Scanner scan = null;
		
		try {
			
			scan = new Scanner(new File(filename));
			
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals("")) {
					continue;
				}

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals("")) {
					continue;
				}

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0) {
				break;
			}

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}
