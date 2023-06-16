package antColony;

import java.util.List;

/**
 * Interface for the AntColony class.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public interface IAntColony<T> {
    /**
     * Returns the corresponding pheromone level for the given vertices.
     * 
     * @param v1 - the first vertex of the edge to check
     * @param v2 - the second vertex of the edge to check
     * 
     * @return pheromone level of the edge between the given vertices
     */
    public float getPheromone(T v1, T v2);

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
    public float setPheromone(T v1, T v2, float phero);

    /**
     * Returns the position of the ant with the given id. Note that this does not
     * check whether the ant is in the graph or not.
     * 
     * @param antId - the id of the antenna to look up
     * 
     * @return the position of the ant with the given id
     */
    public T getAntPosition(int antId);

    /**
     * Returns the path of the ant with the given id.
     * 
     * @param antId - Id of the ant to get the path for.
     * 
     * @return List of the ant's path or null if there is no path for the ant with
     *         the given id
     */
    public List<T> getAntPath(int antId);

    /**
     * Resets the ant with the given id to its initial state. This means that the
     * path is cleared and the ant is moved to the nest.
     * 
     * @param antId - Id of the ant to reset to its initial
     */
    public void resetAnt(int antId);

    /**
     * Adds a path to an ant. It is assumed that the position is set to v1. This can
     * be used to update the position of an ant after it has been added to the ants
     * list.
     * 
     * @param antId - Id of the ant to add path to
     * @param v1    - the vertex to add to the path
     */
    public void addAntPath(int antId, T v1);

    /**
     * Removes the last path from the ant with the given id. This is a no - op if
     * the ant has no path.
     * 
     * @param antId - Id of the Ant to
     */
    public void removeLastAntPath(int antId);

    /**
     * Adds pheromone to the edge between the given vertices.
     * <p>
     * The order of the given vertices is irrelevant.
     * 
     * @param v1 - vertex that is added to the phero
     * @param v2 - vertex that is added to the pher
     */
    public void addEdgePhero(T v1, T v2);

    /**
     * Adds an Ant to colony.
     * 
     * @param antId - Id of the Ant to
     */
    public void addAnt(int antId);

    /**
     * Returns the colony size. This is the number of ants in the collony. If there
     * are no ants the return value is 0.
     * 
     * 
     * @return the number of animals in the colony ( int ) or 0 if there are no
     *         animals
     */
    public T colonyNest();

    /**
     * @return
     *         Number of ants in the AntColony : <strong>int</strong>
     */
    public int colonySize();
}
