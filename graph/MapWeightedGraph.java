package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of a weighted graph using a Map to store the adjacency list.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class MapWeightedGraph<T, E> implements WeightedGraph<T, E> {
		
	protected Map<T, Map<T, E>> adjacencylist;
	
	/**
	 * Creates an empty graph. 
	 */
	public MapWeightedGraph() {
		adjacencylist = new HashMap<>();
	}

	/**
	 * Checks with the vertices are adjacent.
	 * 
	 * @param v1 - the first vertex
	 * @param v2 - the second vertex
	 * 
	 * @return true if the vertices are adjacent, false otherwise
	 */
	@Override
	public boolean areAdjacent(T v1, T v2) {
		if (adjacencylist.containsKey(v1)) {
			return adjacencylist.get(v1).containsKey(v2);
		} else
			return false;
	}

	/**
	 * Adds an edge between two vertices.
	 * 
	 * @param v1 - the first vertex
	 * @param v2 - the second vertex
	 */
	@Override
	public void addEdge(T v1, T v2, E value) {
		if (!areAdjacent(v1, v2)) {
			adjacencylist.get(v1).put(v2, value);
			adjacencylist.get(v2).put(v1, value);
			//System.out.printf("New Edge: %-2d -> %-2d    weight:  %-2d %n", v1, v2, value);
		}
	}

	/**
	 * Gets the set of adjacent vertices of a vertex.
	 * 
	 * @param vertex - the vertex
	 * 
	 * @return the set of adjacent vertices of the vertex
	 */
	@Override
	public Set<T> getAdjacency(T vertex) {
		return adjacencylist.get(vertex).keySet();
	}

	/**
	 * Gets the weight of an edge.
	 * 
	 * @param v1 - the first vertex
	 * @param v2 - the second vertex
	 * 
	 * @return the weight of the edge
	 */
	@Override
	public E getEdgeWeight(T v1, T v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return null;
	}

	/**
	 * Adds a vertex to the graph.
	 * 
	 * @param v1 - the vertex
	 */
	@Override
	public void addVertex(T v1) {
		adjacencylist.put(v1, new HashMap<T, E>());
	}

	/**
	 * Returns the number of vertices in a graph.
	 */
	@Override
	public int nrVertices() {
		return adjacencylist.size();
	}
	
	
	@Override
	public void printGraph() {
		TreeSet<T> keys = new TreeSet<T>(adjacencylist.keySet()); 
		E weight;
		for (T i : keys) {
			for(T j : keys) {
				weight = this.getEdgeWeight(i, j);
				if (weight == null) {
					System.out.printf("%-3s ", "-");
				} else {
					System.out.printf("%-3s ", this.getEdgeWeight(i, j));				
				}
			}
			System.out.print("\n");
		}
		
	}

}
