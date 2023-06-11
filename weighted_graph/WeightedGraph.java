package weighted_graph;

public interface WeightedGraph {
	public boolean areAdjacent(int v1, int v2);
	
	public void addEdge(int v1, int v2, int weight);
	
	public int[] getAdjacency(int vertex);
	
	public int getEdgeWeight(int v1, int v2);
}
