package weighted_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class ListWeightedGraph<T, E> implements WeightedGraph<T, E> {
		
    protected Map<T, Map<T, E>> adjacencylist;
	
	public ListWeightedGraph() {
		adjacencylist = new HashMap<>();
	}

	
	@Override
	public boolean areAdjacent(T v1, T v2) {
		if (adjacencylist.containsKey(v1)) {
			return adjacencylist.get(v1).containsKey(v2);
		}
		else return false;
	}

	@Override
	public void addEdge(T v1, T v2, E value) {
		if (! areAdjacent(v1,v2)) {
			adjacencylist.get(v1).put(v2, value);
			adjacencylist.get(v2).put(v1, value);
			System.out.printf("New Edge: %-2d -> %-2d    weight:  %-2d %n", v1, v2, value);
		}
	}

	@Override
	public Set<T> getAdjacency(T vertex) {
		return adjacencylist.get(vertex).keySet();
	}

	@Override
	public E getEdgeWeight(T v1, T v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return null;
	}


	@Override
	public void addVertex(T v1) {
		adjacencylist.put(v1, new HashMap<T, E>());
	}


	@Override
	public int nrVertices() {
		return adjacencylist.size();
	}


}
