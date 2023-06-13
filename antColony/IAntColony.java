package antColony;
import java.util.List;


public interface IAntColony<T> {
    public float getPheromone(T v1,T v2);
    public void setPheromone(T v1, T v2, float phero);
    public T getAntPosition(int antId);
    public List<T> getAntPath(int antId);
    public void resetAnt(int antId);
    public void addAntPath(int antId, T v1);
    public void removeLastAntPath(int antId);
    public void addEdgePhero(T v1, T v2);
    public void addAnt(int antId);
    public T colonyNest();
    /**
     * @return
     * 	Number of ants in the AntColony : <strong>int</strong>
     */
    public int colonySize();
}
