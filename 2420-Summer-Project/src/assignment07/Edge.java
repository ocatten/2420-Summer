package assignment07;

/**
 * This class exists for an edge between two vertices in a directed graph
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 06:27:23
 */
public class Edge {

	// Fields
	private Vertex dst; // destination of this directed edge
	public Vertex cameFrom; // Origin of the directed edge

	
	/**
	 * Creates the Edge object, given the origin of the edge
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex dst) {
		this.dst = dst;
	}
	
	
	
	/**
	 * Sets the parameter to the origin vertex
	 * 
	 * @param source: Source that the origin points to
	 */
	public void setCameFrom(Vertex source){
		
		cameFrom = source;
	}
	
	
	
	/**
	 * @return: The original vertex pointing to something else
	 */
	public Vertex getCameFrom() { return cameFrom; }

	
	
	/**
	 * @return: The destination the origin is pointing to
	 */
	public Vertex getOtherVertex() {
		return this.dst;
	}
	
	
	
	/**
	 * Returns the name of the destination Vertex as a textual representation of this Edge.
	 */
	public String toString() {
		return this.dst.getName();
	}
	
}