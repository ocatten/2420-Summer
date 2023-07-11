package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	 * @param <Type> The data type to be stored in the Vertex objects.
	 * @param sources: List of vertices that have no vertices pointing to them
	 * @param destinations: Vertices that have vertices pointing to them
	 * @param srcData: source object to search with
	 * @param dstData: destination object to search for.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		// Store each value of the sources and destinations into a Graph object
		Graph<Type> newGraph = new Graph<Type>();
		
		// Iterate through all of the sources and destinations to add edges between them for the Graph object.
		for(int i = 0; i < sources.size()-1; i++) {
			newGraph.addEdge( sources.get(i), destinations.get(i) );
		}
		
		// Suite of test statements
		/*System.out.println("srcData: " + srcData);
		System.out.println("dstData " + dstData);
		System.out.println("srcs: " + sources);
		System.out.println("dsts: " + destinations);*/
		
		// Catch case for an illegal argument, checks if the parameters actually exist in the graph
		if( !(newGraph.getVertices().containsKey(srcData) && newGraph.getVertices().containsKey(srcData) ) ) {
			throw new IllegalArgumentException();
		}
		
		//System.out.println("Cleared exception"); // Test statement
		
		// Add each destination from the graph to a list of destinationVertices.
		List<Vertex<Type>> destinationVertices = new ArrayList<Vertex<Type>>();
		for(Type destination : destinations) {
			destinationVertices.add(newGraph.getVertices().get(destination));
		}
				
		// Set each vertex to not visited
		for(Vertex<Type> vertex: destinationVertices) {
			vertex.visited = false;
		}
				
		// begin the DFS
		LinkedList<Type> result = DFS(destinations, newGraph.getVertices().get(srcData), newGraph.getVertices().get(dstData));
		//System.out.println("DFS RESULT VALUE: " + result); // Test statement
		return (result != null);
	}
	
	
	
	/**
	 * Uses the depth first search to find a target in a graph from a source point. Creates
	 * a linked list of vertices to represent the path taken. 
	 * @param <Type>: Data type the graph is made of within vertices
	 * @param graph: Set of vertices to search through
	 * @param source: source vertex to search with
	 * @param target: Value the search is looking for.
	 * @return LinkedList<Type>: LinkedList of the path created by the recursive calls.
	 */
	public static <Type> LinkedList<Type> DFS(List<Type> remainingNodes, Vertex<Type> sourceVertex, Vertex<Type> targetVertex) {
				
		// Set the current source vertex to visited
		sourceVertex.visited = true;

		// Create a new LinkedList to show the pathway to the target
		LinkedList<Type> returnList = new LinkedList<Type>();
		returnList.add(sourceVertex.getData());
		
		// If the source is at the target, return the list
		if(sourceVertex.getData().equals(targetVertex.getData())){
			
			return returnList;
		}
		
		// If not travel to the next vertex
		for(Edge<Type> edge : sourceVertex.getAdjacent()) {
			
			Vertex<Type> vertex = edge.getDestination();
			
			// If the vertex hasn't been visited yet, add it to the list if it's not null
			if(!vertex.visited) {
				
				LinkedList<Type> result = DFS(remainingNodes, vertex, targetVertex);
				
				// Catch case to avoid null pointer exception
				if(result != null) {
					
					result.add(0, sourceVertex.getData());
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
	 * @param <Type>: Data type of the graph housed in vertices
	 * @param sources: List of vertices that have no vertices pointing to them
	 * @param destinations: Vertices that have vertices pointing to them
	 * @param srcData: source object to search with
	 * @param dstData: destination object to search for.
	 * @return: List of generic objects of the shortest path for srcData to the destination (inclusive)
	 * @throws IllegalArgumentException
	 */
	public <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		
		// Store each value of the sources and destinations into a Graph object
		Graph<Type> newGraph = new Graph<Type>();
		
		
		// Iterate through all of the sources and destinations to add edges between them for the Graph object.
		for(int i = 0; i < sources.size(); i++) {
			newGraph.addEdge( sources.get(i), destinations.get(i) );
		}
		
		// Suite of test statements
		System.out.println("srcData: " + srcData);
		System.out.println("dstData " + dstData);
		System.out.println("srcs: " + sources);
		System.out.println("dsts: " + destinations);
		
		// Catch case for an illegal argument, checks if the parameters actually exist in the graph
		if( !(newGraph.getVertices().containsKey(srcData) && newGraph.getVertices().containsKey(srcData) ) ) {
			throw new IllegalArgumentException();
		}
		
		// Convert the sources into vertices
		List<Vertex<Type> > sourceVertices = new ArrayList<Vertex<Type> >();
		
		for(Type source : sources) {
			sourceVertices.add(new Vertex<Type>(source)); // Make a new vertex for the source
		}
		// Convert the destinations into vertices
		List<Vertex<Type> > destinationVertices = new ArrayList<Vertex<Type> >();
		
		for(Type destination : destinations) {
			destinationVertices.add(new Vertex<Type>(destination)); // Make a new vertex for the destination
		}
		// Find the vertices for dstData and srcData
		Vertex<Type> srcVertex = newGraph.getVertices().get(srcData);
		Vertex<Type> dstVertex = newGraph.getVertices().get(dstData);
		
		// Call the search method.
		return BFS(destinationVertices, sourceVertices, srcVertex, dstVertex);
	}
	
	
	
	/**
	 * Breadth search that finds the shortest possible path to a node by adding each adjacent node to the starting
	 * position through the adjacent nodes adding each layer to a queue. This algorithm will always find the shortest
	 * path
	 * 
	 * @param <Type> Type of data inside of vertices
	 * @param nodes: Existing list of nodes to search
	 * @param edges: List of the vertices that are connected
	 * @param start: Starting vertex to search the adjacent vertices
	 * @param target: Target of the search
	 * @return: List of generic objects from the srcVertex to the dstVertex along the shortest path.
	 */
	public static <Type> List<Type> BFS(List<Vertex<Type>> nodes, List<Vertex<Type>> edges, Vertex<Type> srcVertex, Vertex<Type> dstVertex) {
		
		// Test statements
		System.out.println("srcData: " + srcVertex.getData());
		System.out.println("dstData " + dstVertex.getData());
		
		// Makes the queue for the search
		Queue<Vertex<Type> > bfsQueue = new LinkedList<Vertex<Type> >();
		
		// Creates two empty lists to store the data of the nodes/edges
		List<Type> nodesData = new LinkedList<Type>();
		List<Type> edgesData = new LinkedList<Type>();
		
		// Temporary generic value for srcVertex and dstVertex to be passed through 
		Type srcData = srcVertex.getData();
		Type dstData = dstVertex.getData();
		
		System.out.println("NODES"); // Test statement
		// Go through each node set it to visited / add it to data list
		for(Vertex<Type> node : nodes) {
			
			// Add the data to the list to be returned.
			nodesData.add(node.getData());
			System.out.println(node.getData()); // Test statement
			
			// Sets each to unvisited.
			node.visited = false;
		}
		
		System.out.println("EDGES"); // Test statement
		// Add edges to data list
		for(Vertex<Type> edge : edges) {
			
			// Add the data to the list to be returned.
			edgesData.add(edge.getData());
			System.out.println(edge.getData()); // Test statement
		}
		
		// Adds the starting vertex to the queue
		bfsQueue.add(srcVertex);
		
		// While there are still vertices in the queue:
		while(bfsQueue.size() != 0) {
			
			// Set the starter to the point on the poll and then make it visited.
			Vertex<Type> starter= bfsQueue.poll();
			starter.visited = true;
			
			// Catch case if the target has been found. Reconstructs the path and returns it
			if(starter.getData().equals(dstVertex.getData() )) {
				 
				return reconstructPath(nodesData, edgesData, dstData, srcData); // Calls reconstructor helper method
			}
			
			// Check each adjacent vertex:
			for(Edge<Type> neighbor : starter.getAdjacent()) {
				
				// If the neighbor hasn't been visited:
				if(!neighbor.getDestination().visited) {
					
					//System.out.println("neighbor vertex: " + neighbor.getOtherVertex().getData(); // Test statement
					neighbor.getDestination().cameFrom = starter; // Sets the origin to this vertex as shown in lecture
					// The Vertex is visited if the queue contains it.
					neighbor.getDestination().visited = bfsQueue.contains(neighbor.getDestination());
					
					// Add it to the queue.
					bfsQueue.add(neighbor.getDestination());
				}
			}
		}
		
		//System.out.println("Now returning - FAILURE:"); // Catch case to signify that the code has failed.
		throw new IllegalArgumentException();
	}
	
	
	
	/**
	 * Helper method that reconstructs the path of the BFS.
	 * 
	 * <Type>: Generic type inside of the vertices
	 * @param nodes: List of nodes that the method pulls from
	 * @param edges: List of edges
	 * @param target: Destination we are building from
	 * @param start: Source of the path where it begins
	 * @return: Generic list of data points along the shortest possible path from the start to the target 
	 */
	public static <Type> List<Type> reconstructPath(List<Type> nodes, List<Type> edges, Type target, Type start){
		
		// Test statements
		/*System.out.println("nodes: " + nodes);
		System.out.println("edges: " + edges);
		System.out.println("target: " + target);
		System.out.println("start: " + start);*/
		
		// Empty list to be added to and returned
		List<Type> path = new LinkedList<Type>();
		path.add(start);
		
		
		// Moves through nodes to find the target Node, and words backwards to find the start
		int count = edges.indexOf(start);
		Type currentNode = nodes.get( count );
		
		// Traverse through the nodes until reaching the target
		while(!currentNode.equals(target)) {
			
			// Changes the node to the next item and increments count
			currentNode = nodes.get(count);
			path.add(currentNode); // Add to the list to be returned.
			count++;
		}
		
		//System.out.println(path); // Test statement
		return path;
		
		
		// Store each value of the sources and destinations into a Graph object
		/*Graph<Type> newGraph = new Graph<Type>();
		
		// Iterate through all of the sources and destinations to add edges between them for the Graph object.
		for(int i = 0; i < nodes.size()-1; i++) {
			newGraph.addEdge( nodes.get(i), edges.get(i) );
		}
		
		List<Type> tempPath = new LinkedList<Type>(); // Makes the list to be returned
		
		// Loop through each cameFrom vertex as long as the chain hasn't reached the starting position:
		for(Vertex<Type> node = newGraph.getVertices().get(target); node != start; node = node.cameFrom) {
			
			//System.out.println(node.cameFrom.getData()); // Test statement
			try {
				tempPath.add(node.getData()); // Add it to the chain.
			} catch (Exception e) {
				
				System.out.println("Null value"); // Test statement
				break;
			}
		}
		
		// Add the first element, adds the chain in reverse for proper order, and then returns it.
		List<Type> returnPath = new LinkedList<Type>();
		returnPath.add(newGraph.getVertices().get(start).getData());
		
		for(int i = tempPath.size() - 1; i >= 0; i--) {
			returnPath.add( tempPath.get(i) );
		}
		
		System.out.println(returnPath); // Test statement
		return returnPath;*/
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
            Vertex<String> currVertex = (Vertex<String>) vertex;
            currVertex.visited = true;
        }

        // Perform topological sorting
        List<Type> sortedOrder = new ArrayList<>();
        
        // While there are still objects in the queue:
        while (!queue.isEmpty()) {
        	
        	//Keep a tracker for the current vertex and remove it from the queue
            Type vertex = queue.remove();
            sortedOrder.add(vertex); // Add it to a sorted list
            Vertex<Type> currVertex = (Vertex<Type>) vertex;
            
            //The amount of neighbors 
            int size = sources.size();
            
            //Check each neighbor if its been visited yet
            //if not add it to the queue
            for(Edge<Type> neighbor : currVertex.getAdjacent()) {
            	
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
