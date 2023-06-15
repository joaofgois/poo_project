package antColony;
import java.util.HashMap;
import java.util.Map;

import graph.Edge;

import java.util.List;

public class AntColony<T> implements IAntColony<T> {

	private T nest;
	private Map<Edge<T>, Float> edges;
	private Map<Integer, Ant<T>> ants;
	
	public AntColony(T nest){
		this.nest = nest;
		edges = new HashMap<>();
		ants = new HashMap<>();
	}

	public AntColony(T nest, int numberAnts){
		this.nest=nest;
		edges = new HashMap<>();
		ants = new HashMap<>();

		for (int i=0; i < numberAnts; i++){
			addAnt(i);
		}
	}

	private Edge<T> getEdge(T v1, T v2){
		Edge<T> edge = new Edge<T>(v1, v2);
		if (edges.containsKey(edge)){
			return edge;
		}
		else {
			edges.put(edge, (float) 0);
			return edge;
		}
	}

	@Override
	public float getPheromone(T v1, T v2) {
		Edge<T> edge = getEdge(v1, v2);
		return edges.get(edge);
	}

	@Override
	public float setPheromone(T v1, T v2, float phero) {
		Edge<T> edge = getEdge(v1, v2);
		float newPhero = edges.get(edge) + phero;
		if( newPhero < 0){
			edges.put(edge , (float) 0);
		}
		else {
			edges.put(edge , newPhero);
		}
		return edges.get(edge);
	}

	@Override
	public T getAntPosition(int antId) {
		return ants.get(antId).position;
	}

	@Override
	public List<T> getAntPath(int antId) {
		return ants.get(antId).path;
	}

	@Override
	public void resetAnt(int antId) {
		ants.get(antId).path.clear();
		this.addAntPath(antId, nest);		
	}

	@Override
	public void addAntPath(int antId, T v1) {
		if (!ants.get(antId).path.isEmpty()) {
			if (ants.get(antId).path.getLast() != v1) {
				ants.get(antId).path.addLast(v1);
			}			
		}
		else {
			ants.get(antId).path.add(v1);
		}
		ants.get(antId).position = v1;			
	}

	
	@Override
	public void removeLastAntPath(int antId) {
		if (ants.get(antId).path.size() > 1) {
			ants.get(antId).path.removeLast();
			ants.get(antId).position = ants.get(antId).path.getLast();			
		}
	}

	@Override
	public void addEdgePhero(T v1, T v2) {
		getEdge(v1, v2);
	}
	

	@Override
	public void addAnt(int antId) {
		if (! ants.containsKey(antId)){
			ants.put(antId, new Ant<T>(nest));
			this.addAntPath(antId, nest);
		} else{
			System.out.println("Tried to add Ant that already exists in colony.");
		}
	}

	@Override
	public int colonySize() {
		return ants.size();
	}

	@Override
	public T colonyNest() {
		return nest;
	}


}  

