package weighted_graph;

//import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ListWeightedGraph implements WeightedGraph {
		
	private int vertices;
    private Map<Integer, List<WeightedEdge>> adjacencylist;
    
	public ListWeightedGraph(int vertices) {
		this.vertices = vertices;
		adjacencylist = new HashMap<>();
		// initialize graph with given number of vertices
		for (int i = 0; i < vertices; i++) {
			adjacencylist.put(i, new ArrayList<WeightedEdge>());
		}
	}
	
	@Override
	public boolean areAdjacent(int v1, int v2) {
        List<WeightedEdge> edges = adjacencylist.get(v1);
        if (edges != null){
	        for (WeightedEdge e : edges) {
	            if (e.v1 == v2) {
	                return true;
	            }
	        }
        }
        return false;
    }
	
	@Override
	public void addEdge(int v1, int v2, int weight) {
		List<WeightedEdge> edges = adjacencylist.get(v1);
		if (! this.areAdjacent(v1, v2)) {
			edges.add(new WeightedEdge(v1, v2, weight));
		}
		if (! this.areAdjacent(v2, v1)) {
			edges.add(new WeightedEdge(v2, v1, weight));
		}
	}
	

	@Override
	public int[] getAdjacency(int vertex) {
		List<WeightedEdge> edges = adjacencylist.get(vertex);
		int[] adjacency = new int[edges.size()];
		if (edges != null) {
			for (int i = 0; i < edges.size(); i++) {
				adjacency[i] = edges.get(i).v2;
			}
		}
		return adjacency;
	}

	@Override
	public int getEdgeWeight(int v1, int v2) {
		List<WeightedEdge> edges = adjacencylist.get(v1);
		if (edges != null) {
			for (WeightedEdge e: edges) {
				if(e.v2 == v2) {
					return e.weight;
				}
			}
		}
		return 0;
	}
	


	
}
