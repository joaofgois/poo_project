package graph;

import java.util.Set;

/**
 * Interface for working with weighted graphs.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public interface WeightedGraph<V, E> {
	/**
	 * Returns a boll indicating whether two vertices are adjacent.
	 * 
	 * @param v1 - first vertex.
	 * @param v2 - second vertex.
	 * 
	 * @return true if the two vertices are adjacent, false otherwise.
	 */
	public boolean areAdjacent(V v1, V v2);
	
	/**
	 * Adds a new edge connecting two vertices.
	 * 
	 * @param v1 - first vertex.
	 * @param v2 - second vertex.
	 * @param value - weight of the edge connecting the two vertices.
	 * 
	 */
	public void addEdge(V v1, V v2, E value);
	
	/**
	 * Adds a new vertex to the graph.
	 * 
	 * @param v1 - vertex to be added.
	 * 
	 */
	public void addVertex(V v1);
	
	/**
	 * GEts adjancent vertices.
	 * 
	 * @param v1 - vertez to get adjacent vertices from.
	 * 
	 * @return set of adjacent vertices.
	 */
	public Set<V> getAdjacency(V vertex);
	
	/**
	 * Gets the weight of the edge connecting two vertices.
	 * 
	 * @param v1 - first vertex.
	 * @param v2 - second vertex.
	 * 
	 * @return weight of the edge connecting the two vertices.
	 */
	public E getEdgeWeight(V t, V t2);
	
	/**
	 * Gets the number of vertices in the graph.
	 * 
	 * @return number of vertices in the graph.
	 */
	public int nrVertices();
	
	/**
	 * Prints the graph.
	 */
	public void printGraph();
}
