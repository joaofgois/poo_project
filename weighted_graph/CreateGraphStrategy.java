package weighted_graph;

public interface CreateGraphStrategy<T,E> {
	MapWeightedGraph<T,E> create(int vertices);	
}
