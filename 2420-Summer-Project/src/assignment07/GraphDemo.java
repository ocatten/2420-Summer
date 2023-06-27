package assignment07;

import java.util.LinkedList;
import java.util.List;

public class GraphDemo {


	public static void main(String[] args) {
		
/*----------------------------------------------- DEPTH SEARCH TESTS -----------------------------------------------------*/
		// build a sample directed graph
		Graph sample = new Graph();
		sample.addEdge("a", "b");
		sample.addEdge("b", "c");
		sample.addEdge("c", "d");
		sample.addEdge("d", "e");
		sample.addEdge("z", "b");
		sample.addEdge("z", "y");
		//sample.addEdge("d", "a");
		//sample.addEdge("c", "e");

		// print textual representation of sample graph
		System.out.println(sample);
		
		// print DOT representation of sample graph (paste into http://www.webgraphviz.com)
		System.out.println(sample.generateDot());
		
		
		//Create new graph utility
		GraphUtility utility = new GraphUtility();
		
		//create a vertex list of sources
		List<Vertex> sources = new LinkedList<Vertex>();
		sources.add( sample.getVerticies().get("a") );
		
		//create a vertex list of destinations
		List<Vertex> destinations = new LinkedList<Vertex>();
		destinations.add( sample.getVerticies().get("b") );
		destinations.add( sample.getVerticies().get("c") );
		destinations.add( sample.getVerticies().get("d") );
		destinations.add( sample.getVerticies().get("e") );
		destinations.add( sample.getVerticies().get("y") );
		
		//System.out.println( destinations.get(0).visited );
		
		System.out.println(  utility.areConnected(sources, destinations, sample.getVerticies().get("a"), sample.getVerticies().get("y"))  );
		System.out.println();
		
		
/*----------------------------------------------- SHORTEST PATH TESTS ----------------------------------------------------*/
		
		// Creates a new sample graph and lists of sources and destinations
		Graph sample2 = new Graph();
		List<Vertex> sources2 = new LinkedList<Vertex>();
		List<Vertex> destinations2 = new LinkedList<Vertex>();
		
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
		sources2.add( sample2.getVerticies().get("a") );
		sources2.add( sample2.getVerticies().get("b") );
		sources2.add( sample2.getVerticies().get("c") );
		
		// Crates the destinations list
		destinations2.add( sample2.getVerticies().get("d") );
		destinations2.add( sample2.getVerticies().get("e") );
		destinations2.add( sample2.getVerticies().get("f") );
		destinations2.add( sample2.getVerticies().get("g") );
		destinations2.add( sample2.getVerticies().get("h") );
		destinations2.add( sample2.getVerticies().get("i") );
		destinations2.add( sample2.getVerticies().get("j") );
		destinations2.add( sample2.getVerticies().get("k") );
		
		// Show the graph
		System.out.println("GRAPH 2: \n" + sample2.generateDot() + "\n");
		
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List<Vertex> shortestPathResult = 
				utility.shortestPath(sources2, destinations2, sample2.getVerticies().get("a"), sample2.getVerticies().get("i") );
		for(Vertex node : shortestPathResult) {
			System.out.print(node.getName() + " ");
		}
		System.out.println();
		
		// Creates the shortest path and prints it with a for loop to verify a successful test
		List<Vertex> shortestPathResult2 = 
				utility.shortestPath(sources2, destinations2, sample2.getVerticies().get("a"), sample2.getVerticies().get("k") );
		for(Vertex node : shortestPathResult2) {
			System.out.print(node.getName() + " ");
		}
		System.out.println();
		
		
/*----------------------------------------------- TOPO SORT TESTS --------------------------------------------------------*/
	
}
