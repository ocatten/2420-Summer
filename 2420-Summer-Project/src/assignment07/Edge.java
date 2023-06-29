package assignment07;

/**
 * This class exists for an edge between two vertices in a directed graph
 * 
 * @author Parker Catten, & Everett Oglesby
 * @version 06:27:23
 */
public class Edge {

	// Fields
	public Vertex src; // Source of the directed edge
	private Vertex dst; // Destination of the directed edge

	
	/**
	 * Constructor that creates the Edge object, given the source
	 * 
	 * @param dst: The destination Vertex
	 */
	public Edge(Vertex dst) {
		this.dst = dst;
	}
	
	
	
	/**
	 * Sets the parameter to the origin vertex
	 * 
	 * @param src: Source that the origin points to
	 */
	public void setSource(Vertex src){
		this.src = src;
	}
	
	
	
	/**
	 * @return: The original vertex pointing to something else
	 */
	public Vertex getSource() { return src; }

	
	
	/**
	 * @return: The destination the source is pointing to
	 */
	public Vertex getDestination() { return dst; }
	
	
	
	/**
	 * @return: Data at the destination for the console
	 */
	public String toString() { return dst.getData(); }
	
}