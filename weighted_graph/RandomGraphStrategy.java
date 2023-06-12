package weighted_graph;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class RandomGraphStrategy implements CreateGraphStrategy {
	private Random random;
	private int maxWeight;
	
	public RandomGraphStrategy(int maxWeight) {
		random = new Random();
		this.maxWeight = maxWeight;
	}

	@Override
	public ListWeightedGraph create(int vertices) {
		ListWeightedGraph graph = new ListWeightedGraph(vertices);
		//int temp = ((vertices*(vertices-1))/2) - vertices + 1;
		int nrEdges = random.nextInt( ((vertices*(vertices-1))/2) - vertices + 1);
		System.out.println(nrEdges);
		int v1;
		int v2;
		
		//generate possible vertices
		List<Integer> cycle = new ArrayList<Integer>(vertices);
		for (int i = 0; i < vertices; i++) {
			cycle.add(i);
		}
		//generate Hamilton cycle
		Collections.shuffle(cycle);
		System.out.println(cycle);
		for(int i = 0; i < vertices-1; i++) {
			graph.addEdge(cycle.get(i), cycle.get(i+1), random.nextInt(maxWeight) + 1);
		}
		graph.addEdge(cycle.get(vertices-1), cycle.get(0), random.nextInt(maxWeight) + 1);
		
		//create more random edges
		while(nrEdges != 0) {
			v1 = random.nextInt(vertices);
			v2 = random.nextInt(vertices);
			if (v1 != v2) {
				if (! graph.areAdjacent(v1, v2)) {
					graph.addEdge(v1, v2, random.nextInt(maxWeight) + 1);
					nrEdges -= 1;
				}
				
			}
		}
		
		return graph;
	}

}
