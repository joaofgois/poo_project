package graph;

public class IntMapWeightedGraph extends MapWeightedGraph<Integer, Integer> {

	public IntMapWeightedGraph(int vertices) {
		super();
		for (int i = 0; i < vertices; i++) {
			this.addVertex(i);
		}
	}
	
	public IntMapWeightedGraph() {
		super();
	}
	
	@Override
	public Integer getEdgeWeight(Integer v1, Integer v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return 0;
	}
}
