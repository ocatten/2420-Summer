package assignment07;

import java.util.LinkedList;
import java.util.List;

/**
 * Graph tester
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 06:27:23
 */
public class GraphTester {

	
	public static void main(String[] args) {
		
/*----------------------------------------------- DEPTH SEARCH TESTS -----------------------------------------------------*/
		// build a sample directed graph
		Graph<String> sample = new Graph<String>();
		sample.addEdge("a", "b");
		sample.addEdge("b", "c");
		sample.addEdge("c", "d");
		sample.addEdge("d", "e");
		sample.addEdge("z", "b");
		sample.addEdge("z", "y");
		//sample.addEdge("d", "a");
		//sample.addEdge("c", "e");
		
		// Give the DOT graph for an online tool
		System.out.println(sample.dotGraph());
		System.out.println();
		
		
		// Instantiate the GraphUtility object
		GraphUtility utility = new GraphUtility();
		
		// Create the sources
		List<Vertex> sources = new LinkedList<Vertex>();
		sources.add( sample.getVertices().get("a") );
		
		// Create destinations
		List<Vertex> destinations = new LinkedList<Vertex>();
		destinations.add( sample.getVertices().get("b") );
		destinations.add( sample.getVertices().get("c") );
		destinations.add( sample.getVertices().get("d") );
		destinations.add( sample.getVertices().get("e") );
		destinations.add( sample.getVertices().get("y") );
		
		System.out.println( "a connected to y: " +
							 utility.areConnected(sources, destinations, sample.getVertices().get("a"), sample.getVertices().get("y"))  );
		
		System.out.println( "a connected to d: " +
				 utility.areConnected(sources, destinations, sample.getVertices().get("a"), sample.getVertices().get("d"))  );
		
		
		System.out.println( "b connected to y: " +
				 utility.areConnected(sources, destinations, sample.getVertices().get("a"), sample.getVertices().get("y"))  );
		
		System.out.println( "b connected to d: " +
				utility.areConnected(sources, destinations, sample.getVertices().get("a"), sample.getVertices().get("d"))  );
		System.out.println();
		
		
/*----------------------------------------------- SHORTEST PATH TESTS ----------------------------------------------------*/
		
		// Creates a new sample graph and lists of sources and destinations
		Graph<String> sample2 = new Graph<String>();
		List<Vertex<String>> sources2 = new LinkedList< Vertex<String> >();
		List< Vertex<String> > destinations2 = new LinkedList< Vertex<String> >();
		
		// Makes a web with more alternate paths than the first graph.
		sample2.addEdge("a", "d");
		sample2.addEdge("a", "e");
		
		sample2.addEdge("b", "d");
		sample2.addEdge("b", "e");
		sample2.addEdge("b", "f");
		
		sample2.addEdge("c", "e");
		sample2.addEdge("c", "f");
		
		sample2.addEdge("d", "i");
		sample2.addEdge("d", "h");
		
		sample2.addEdge("e", "g");
		sample2.addEdge("e", "h");
		
		sample2.addEdge("f", "h");
		sample2.addEdge("f", "k");
		
		sample2.addEdge("g", "i");
		sample2.addEdge("g", "j");
		
		sample2.addEdge("h", "j");
		sample2.addEdge("h", "k");
		
		// Creates the sources list
		sources2.add( sample2.getVertices().get("a") );
		sources2.add( sample2.getVertices().get("b") );
		sources2.add( sample2.getVertices().get("c") );
		
		// Crates the destinations list
		destinations2.add( sample2.getVertices().get("d") );
		destinations2.add( sample2.getVertices().get("e") );
		destinations2.add( sample2.getVertices().get("f") );
		destinations2.add( sample2.getVertices().get("g") );
		destinations2.add( sample2.getVertices().get("h") );
		destinations2.add( sample2.getVertices().get("i") );
		destinations2.add( sample2.getVertices().get("j") );
		destinations2.add( sample2.getVertices().get("k") );
		
		// Show the graph
		System.out.println("GRAPH 2: \n" + sample2.dotGraph() + "\n");
		
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List< Vertex<String> > shortestPathResult = 
				utility.shortestPath(sources2, destinations2, sample2.getVertices().get("a"), sample2.getVertices().get("i") );
		System.out.print("Shortest path from a to i: ");
		for(Vertex node : shortestPathResult) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List< Vertex<String> > shortestPathResult2 = 
				utility.shortestPath(sources2, destinations2, sample2.getVertices().get("a"), sample2.getVertices().get("k") );
		System.out.print("Shortest path from a to k: ");
		for(Vertex<String> node : shortestPathResult2) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List< Vertex<String> > shortestPathResult3 = 
				GraphUtility.shortestPath(sources2, destinations2, sample2.getVertices().get("a"), sample2.getVertices().get("g") );
		System.out.print("Shortest path from a to g: ");
		for(Vertex<String> node : shortestPathResult3) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List< Vertex<String> > shortestPathResult4 = 
				utility.shortestPath(sources2, destinations2, sample2.getVertices().get("b"), sample2.getVertices().get("g") );
		System.out.print("Shortest path from b to g: ");
		for(Vertex<String>+ node : shortestPathResult4) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List< Vertex<String> > shortestPathResult5 = 
				utility.shortestPath(sources2, destinations2, sample2.getVertices().get("b"), sample2.getVertices().get("j") );
		System.out.print("Shortest path from b to j: ");
		for(Vertex<String> node : shortestPathResult5) {
			System.out.print(node.getData() + " ");
		}
		System.out.println();		
		

		System.out.println();
		
/*----------------------------------------------- TOPO SORT TESTS --------------------------------------------------------*/

	
		System.out.println("Topo sort tests");
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
				List< Vertex<String> > shortestPathResultTopo = utility.sort(sources2, destinations2);
				System.out.print("Shortest path from a to i: ");
				for(Vertex node : shortestPathResultTopo) {
					System.out.print(node.getData() + " ");
				}
				System.out.println();
	}
}