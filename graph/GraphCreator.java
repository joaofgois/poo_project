package graph;

/**
 * Class that creates a graph using a strategy.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class GraphCreator<T,E> {
	private CreateGraphStrategy<T, E> strategy;
	
	/**
	 * Graph creator constructor.
	 */
	public GraphCreator() {
	}

	/**
	 * Graph creator with a a strategy.
	 * 
	 * @param strategy - Strategy to create the graph.
	 */
	public GraphCreator(CreateGraphStrategy<T, E> strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Sets the strategy to create the graph.
	 * 
	 * @param strategy - Strategy to create the graph.
	 */
	public void setStrategy(CreateGraphStrategy<T, E> strategy) {
		this.strategy = strategy;
	}

	/**
	 * Creates a graph with a number of vertices.
	 * 
	 * @param vertices - Number of vertices.
	 * 
	 * @return Graph with a number of vertices.
	 */
	public WeightedGraph<T,E> createGraph(int vertices) {
		return (strategy.create(vertices));
	}
}
