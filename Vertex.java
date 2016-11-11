/**
 *  Class to represent a vertex of a graph
 *  @author Soorya Prasanna Ravichandran
 */

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Vertex {
	int name; // name of the vertex
	boolean seen; // flag to check if the vertex has already been visited
	int distance; // distance of vertex from a source
	List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
	HashMap<Vertex, Edge> adjMap = new HashMap<>(); //Helps to verify tour; also to improve efficiency given 2 vertices
	
	//To remember the last position in the adjacency list to avoid redundant searches
	Iterator<Edge> edge_iterator;
	
	public Iterator<Edge> iterator()
	{
		if(edge_iterator == null)
			edge_iterator = this.adj.iterator();
		return edge_iterator;
	}

	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		distance = Integer.MAX_VALUE;
		adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}
