package graph;

/**
 * Class that represents a weighted graph with integer vertices.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class IntMapWeightedGraph extends MapWeightedGraph<Integer, Integer> {

	/**
	 * Creates a new weighted graph with integer vertices.
	 * 
	 * @param vertices - the number of vertices of the graph.
	 */
	public IntMapWeightedGraph(int vertices) {
		super();
		for (int i = 0; i < vertices; i++) {
			this.addVertex(i);
		}
	}
	
	/**
	 * Creates a new weighted graph with integer vertices.
	 */
	public IntMapWeightedGraph() {
		super();
	}
	
	/**
	 * Function that retrieves the weight of an edge.
	 * 
	 * @param v1 - the first vertex 
	 * @param v2 - the second vertex 
	 * 
	 * @return the weight of the edge.
	 */
	@Override
	public Integer getEdgeWeight(Integer v1, Integer v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return 0;
	}
}
