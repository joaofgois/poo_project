package sed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import weighted_graph.WeightedGraph;


// class CycleComparator<T,E> implements Comparator<Cycle<T,E>> {

//     @Override
//     public int compare(Cycle<T, E> arg0, Cycle<T, E> arg1) {

//     }

// }



/**
 * Simulator for ACO in a weighted graph<p>
 * <strong>T</strong> is the data type for the graph's vertices label.<p>
 * <strong>E</strong> is the data type for the graph's edge weight.<p>
 */
public class TspACOSimulation<T, E> {
    private PriorityQueue<Cycle<T,E>> bestCycles;
    protected float alpha,beta,delta,pheroLevel,eta,rho;
    protected WeightedGraph<T,E> graph;
    protected float graphWeight; //falta isto
    protected Simulator simulator;

    public TspACOSimulation(WeightedGraph<T,E> graph, float simtime, int nr_stored_cycles, float alpha, float beta, float delta, float pheroLevel, float eta, float rho){
        this.graph = graph;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.eta = eta;
        this.rho = rho;
        //this.bestCycles = new PriorityQueue<Cycle<T,E>>(new CycleComparator<T,E>());;   //falta isto
        this.simulator = new Simulator(simtime);
    }

    public void storeCycle(ArrayList<T> cycle, E cycle_weight){
        //ArrayList1.equals(ArrayList2) == true
        Cycle<T,E> hamiCycle = new Cycle<T,E>(cycle, cycle_weight);
        for (int i=0; i<bestCycles.size(); i++){
            //if(bestCycles.get(i).cycle.equals(cycle)){
                
            //}
        }
    }
}
