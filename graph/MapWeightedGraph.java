package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class MapWeightedGraph<T, E> implements WeightedGraph<T, E> {
		
    protected Map<T, Map<T, E>> adjacencylist;
	
	public MapWeightedGraph() {
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
