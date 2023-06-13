package sed;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import weighted_graph.WeightedGraph;

/**
 * Simulator for ACO in a weighted graph<p>
 * <strong>T</strong> is the data type for the graph's vertices label.<p>
 * <strong>E</strong> is the data type for the graph's edge weight.<p>
 */
public class ACOSimulator<T, E> {
    private Map<Integer, Map<List<T>, E>> bestCycles;
    protected float alpha,beta,delta,pheroLevel;
    protected WeightedGraph<T,E> graph;
    protected static float graphWeight;

    public ACOSimulator(WeightedGraph<T,E> graph, int nr_stored_cycles, float alpha, float beta, float delta, float pheroLevel){
        this.graph = graph;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.bestCycles = ;
    }

    public void storeCycle(ArrayList<T> cycle, E cycle_weight){
        
    }
}
