package weighted_graph;

public class WeightedEdge extends Edge {
	int weight;

	public WeightedEdge(int v1, int v2, int weight) {
		super(v1, v2);
		this.weight = weight;
	}

}
