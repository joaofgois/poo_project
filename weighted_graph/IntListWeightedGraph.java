package weighted_graph;

public class IntListWeightedGraph extends ListWeightedGraph<Integer, Integer> {

	public IntListWeightedGraph(int vertices) {
		super();
		for (int i = 0; i < vertices; i++) {
			this.addVertex(i);
		}
	}
	
	public IntListWeightedGraph() {
		super();
	}
	
	@Override
	public Integer getEdgeWeight(Integer v1, Integer v2) {
		if (areAdjacent(v1,v2)) {
			return adjacencylist.get(v1).get(v2);
		}
		return 0;
	}
	
	@Override
	public void printGraph() {
		for (int i=0; i < this.nrVertices(); i++) {
			for(int j = 0; j < this.nrVertices(); j++) {
				System.out.printf("%-3d ", this.getEdgeWeight(i, j));				
			}
			System.out.print("\n");
		}
		
	}
		

}
