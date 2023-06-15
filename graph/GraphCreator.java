package graph;

public class GraphCreator<T,E> {
	private CreateGraphStrategy<T,E> strategy;
	
	public GraphCreator() {
	}

	public GraphCreator(CreateGraphStrategy<T,E> strategy) {
		this.strategy = strategy;
	}
	
	
	public void setStrategy(CreateGraphStrategy<T,E> strategy) {
		this.strategy = strategy;
	}

	public WeightedGraph<T,E> createGraph(int vertices) {
		return (strategy.create(vertices));
	}
}
