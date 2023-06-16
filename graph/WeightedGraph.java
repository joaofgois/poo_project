package graph;

import java.util.Set;

public interface WeightedGraph<V, E> {
	public boolean areAdjacent(V v1, V v2);
	
	public void addEdge(V v1, V v2, E value);
	
	public void addVertex(V v1);
	
	public Set<V> getAdjacency(V vertex);
	
	public E getEdgeWeight(V t, V t2);
	
	public int nrVertices();
	
	public void printGraph();
}
