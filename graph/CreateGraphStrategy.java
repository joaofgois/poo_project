package graph;

/**
 * Interface to create a weighted graph.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public interface CreateGraphStrategy<T,E> {
	MapWeightedGraph<T,E> create(int vertices);	
}
