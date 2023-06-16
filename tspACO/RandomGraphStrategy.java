package tspACO;
import java.util.Random;

import graph.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This c
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 */
public class RandomGraphStrategy implements CreateGraphStrategy<Integer, Integer> {
	private Random random;
	private int maxWeight;
	
	/**
	 * Creates a new RandomGraphStrategy object.
	 * 
	 * @param maxWeight The maximum weight of the edges.
	 */
	public RandomGraphStrategy(int maxWeight) {
		random = new Random();
		this.maxWeight = maxWeight;
	}

	/**
	 * Creates a new RandomGraphStrategy object.
	 * 
	 * @param vertices The number of vertices of the graph.
	 */
	@Override
	public MapWeightedGraph<Integer,Integer> create(int vertices) {
		MapWeightedGraph<Integer,Integer> graph = new IntMapWeightedGraph();
		for (int i = 0; i < vertices; i++) {
			graph.addVertex(i+1);
		}
		
		int nrEdges = random.nextInt( ((vertices*(vertices-1))/2) - vertices + 1);
		int v1;
		int v2;
		
		//generate possible vertices
		List<Integer> cycle = new ArrayList<Integer>(vertices);
		for (int i = 0; i < vertices; i++) {
			cycle.add(i+1);
		}
		//generate Hamilton cycle
		Collections.shuffle(cycle);
		for(int i = 0; i < vertices-1; i++) {
			graph.addEdge(cycle.get(i), cycle.get(i+1), random.nextInt(maxWeight) + 1);
		}
		graph.addEdge(cycle.get(vertices-1), cycle.get(0), random.nextInt(maxWeight) + 1);
		
		//create more random edges
		while(nrEdges != 0) {
			v1 = random.nextInt(vertices) + 1;
			v2 = random.nextInt(vertices) + 1;
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
