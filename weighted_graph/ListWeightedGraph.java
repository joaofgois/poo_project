package weighted_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListWeightedGraph implements WeightedGraph<Integer, Integer> {
		
    private Map<Integer, Map<Integer, Integer>> adjacencylist;
	
	public ListWeightedGraph() {
		adjacencylist = new HashMap<>();
	}

	public ListWeightedGraph(int vertices) {
		adjacencylist = new HashMap<>();
		
		for (int i = 0; i < vertices; i++) {
			adjacencylist.put(i, new HashMap<Integer, Integer>());
		}
	}
	
	@Override
	public boolean areAdjacent(Integer v1, Integer v2) {
		if (adjacencylist.containsKey(v1)) {
			return adjacencylist.get(v1).containsKey(v2);
		}
		else return false;
	}

	@Override
	public void addEdge(Integer v1, Integer v2, Integer value) {
		if (areAdjacent(v1,v2)) {
			adjacencylist.get(v1).put(v2, value);
			adjacencylist.get(v2).put(v1, value);
		}
	}

	@Override
	public Set<Integer> getAdjacency(Integer vertex) {
		return adjacencylist.get(vertex).keySet();
	}

	@Override
	public Integer getEdgeWeight(Integer v1, Integer v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return 0;
	}


	@Override
	public void addVertex(Integer v1) {
		adjacencylist.put(v1, new HashMap<Integer, Integer>());
	}


	@Override
	public int nrVertices() {
		return adjacencylist.size();
	}
	
	
	


	
}
