package antColony;

import java.util.HashMap;
import java.util.Map;
import graph.Edge;
import java.util.List;

/**
 * This class represents an ant colony. It contains the colony's nest, ant positions, pheromone levels and the functions to manipulate them.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class AntColony<T> implements IAntColony<T> {

	private T nest;
	private Map<Edge<T>, Float> edges;
	private Map<Integer, Ant<T>> ants;

	/**
	 * Starts the ant colony with the given nest. The nest is the starting point of
	 * the ant colony.
	 * 
	 * @param nest - the nest of the ant colony
	 */
	public AntColony(T nest) {
		this.nest = nest;
		edges = new HashMap<>();
		ants = new HashMap<>();
	}

	/**
	 * Starts the ant colony with the given nest. The nest is the starting point of
	 * the ant colony.
	 * 
	 * @param nest       - the first vertex of the edge to check
	 * @param numberAnts - the first vertex of the edge to check
	 */
	public AntColony(T nest, int numberAnts) {
		this.nest = nest;
		edges = new HashMap<>();
		ants = new HashMap<>();

		// Adds all the anti numbers to the list of all the anti numbers.

		for (int i = 0; i < numberAnts; i++) {
			addAnt(i);
		}
	}

	/**
	 * Get´s the corresponding edge to the given vertices. If there is no such edge
	 * it is added to the graph and the value of the edge is returned.
	 * 
	 * @param v1 - the first vertex of the edge to check
	 * @param v2 - the second vertex of the edge to check
	 * 
	 * @return the edge that was added to the graph or the edge that was already in
	 *         the graph
	 */
	private Edge<T> getEdge(T v1, T v2) {
		Edge<T> edge = new Edge<T>(v1, v2);

		// Returns the edge that was added to the graph.
		if (edges.containsKey(edge)) {
			return edge;
		} else {
			edges.put(edge, (float) 0);
			return edge;
		}
	}

	/**
	 * Returns the corresponding pheromone level for the given vertices.
	 * 
	 * @param v1 - the first vertex of the edge to check
	 * @param v2 - the second vertex of the edge to check
	 * 
	 * @return pheromone level of the edge between the given vertices
	 */
	@Override
	public float getPheromone(T v1, T v2) {
		Edge<T> edge = getEdge(v1, v2);
		return edges.get(edge);
	}

	/**
	 * Sets the pheromone level for the edge between the given vertices to the given
	 * value.
	 * 
	 * @param v1    - the first vertex of the edge to check
	 * @param v2    - the second vertex of the edge to check
	 * @param phero - the pheromone level to set the edge to
	 * 
	 * @return returns the new pheromone level of the edge
	 */
	@Override
	public float setPheromone(T v1, T v2, float phero) {
		Edge<T> edge = getEdge(v1, v2);
		float newPhero = edges.get(edge) + phero;

		// Set the new phero to 0.
		if (newPhero < 0) {
			edges.put(edge, (float) 0);
		} else {
			edges.put(edge, newPhero);
		}
		return edges.get(edge);
	}

	/**
	 * Returns the position of the ant with the given id. Note that this does not
	 * check whether the ant is in the graph or not.
	 * 
	 * @param antId - the id of the antenna to look up
	 * 
	 * @return the position of the ant with the given id
	 */
	@Override
	public T getAntPosition(int antId) {
		return ants.get(antId).position;
	}

	/**
	 * Returns the path of the ant with the given id.
	 * 
	 * @param antId - Id of the ant to get the path for.
	 * 
	 * @return List of the ant's path or null if there is no path for the ant with
	 *         the given id
	 */
	@Override
	public List<T> getAntPath(int antId) {
		return ants.get(antId).path;
	}

	/**
	 * Resets the ant with the given id to its initial state. This means that the
	 * path is cleared and the ant is moved to the nest.
	 * 
	 * @param antId - Id of the ant to reset to its initial
	 */
	@Override
	public void resetAnt(int antId) {
		ants.get(antId).path.clear();
		this.addAntPath(antId, nest);
	}

	/**
	 * Adds a path to an ant. It is assumed that the position is set to v1. This can
	 * be used to update the position of an ant after it has been added to the ants
	 * list.
	 * 
	 * @param antId - Id of the ant to add path to
	 * @param v1    - the vertex to add to the path
	 */
	@Override
	public void addAntPath(int antId, T v1) {

		// Add a path to the path.
		if (!ants.get(antId).path.isEmpty()) {

			// Add the last path to the path.
			if (ants.get(antId).path.getLast() != v1) {
				ants.get(antId).path.addLast(v1);
			}
		} else {
			ants.get(antId).path.add(v1);
		}
		ants.get(antId).position = v1;
	}

	/**
	 * Removes the last path from the ant with the given id. This is a no - op if
	 * the ant has no path.
	 * 
	 * @param antId - Id of the Ant to
	 */
	@Override
	public void removeLastAntPath(int antId) {
		// Remove the path from the path and move the path to the last path.
		if (ants.get(antId).path.size() > 1) {
			ants.get(antId).path.removeLast();
			ants.get(antId).position = ants.get(antId).path.getLast();
		}
	}

	/**
	 * Adds pheromone to the edge between the given vertices.
	 * <p>
	 * The order of the given vertices is irrelevant.
	 * 
	 * @param v1 - vertex that is added to the phero
	 * @param v2 - vertex that is added to the pher
	 */
	@Override
	public void addEdgePhero(T v1, T v2) {
		getEdge(v1, v2);
	}

	/**
	 * Adds an Ant to colony.
	 * 
	 * @param antId - Id of the Ant to
	 */
	@Override
	public void addAnt(int antId) {
		// Add Ant to colony.
		if (!ants.containsKey(antId)) {
			ants.put(antId, new Ant<T>(nest));
			this.addAntPath(antId, nest);
		} else {
			System.out.println("Tried to add Ant that already exists in colony.");
		}
	}

	/**
	 * Returns the colony size. This is the number of ants in the collony. If there
	 * are no ants the return value is 0.
	 * 
	 * 
	 * @return the number of animals in the colony ( int ) or 0 if there are no
	 *         animals
	 */
	@Override
	public int colonySize() {
		return ants.size();
	}

	/**
	 * Returns the colony nest.
	 * 
	 * 
	 * @return The colony nest
	 */
	@Override
	public T colonyNest() {
		return nest;
	}

}
