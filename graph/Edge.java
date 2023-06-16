package graph;

import java.util.Objects;

/**
 * Class that represents an edge of the graph.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class Edge<T> {

	protected T v1;
	protected T v2;

	/**
	 * Function that creates an edge.
	 * 
	 * @param v1 - first vertex of the edge.
	 * @param v2 - second vertex of the edge.
	 */
	public Edge(T v1, T v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	/**
	 * Function that returns the first vertex of the edge.
	 * 
	 * @return the first vertex of the edge.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(v1) + Objects.hash(v2);
	}

	/**
	 * Function that returns a bollian value that indicates if the object is an edge.
	 * 
	 * @return the first vertex of the edge.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<T> other = (Edge<T>) obj;
		return (Objects.equals(v1, other.v1) && Objects.equals(v2, other.v2)
				|| Objects.equals(v1, other.v2) && Objects.equals(v1, other.v2));
	}

}
