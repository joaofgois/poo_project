package weighted_graph;

public class GraphCreator {
	private CreateGraphStrategy strategy;
	
	public GraphCreator() {
	}

	public GraphCreator(CreateGraphStrategy strategy) {
		this.strategy = strategy;
	}
	
	
	public void setStrategy(CreateGraphStrategy strategy) {
		this.strategy = strategy;
	}

	public IntListWeightedGraph createGraph(int vertices) {
		return (strategy.create(vertices));
	}
}
