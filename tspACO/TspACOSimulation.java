package tspACO;

import java.util.ArrayList;


import graph.WeightedGraph;
import sed.ISimulator;
import sed.Simulator;

//SO PARA DEBUG------------------------------------------------------------------

/**
 * Simulator for ACO in a weighted graph<p>
 * <strong>T</strong> is the data type for the graph's vertices label.<p>
 * <strong>E</strong> is the data type for the graph's edge weight.<p>
 */
public class TspACOSimulation<T, E> {
    ArrayList<Cycle<T,Integer>> hamiCycles;
    protected float alpha,beta,delta,pheroLevel,eta,rho, miu;
    protected WeightedGraph<T,E> graph;
    protected float graphWeight; //falta isto
    public ISimulator simulator;

   // Comparator<Cycle<T,E>> cycleComparator;
	//TreeSet<Student> myTreeSet = new TreeSet<>(ageComparator);
    /**
     * 
     * @param graph
     * @param simtime
     * @param nr_stored_cycles
     * @param alpha
     * @param beta
     * @param delta
     * @param pheroLevel
     * @param eta
     * @param rho
     */
    public TspACOSimulation(WeightedGraph<T,E> graph, float simtime, int nr_stored_cycles, float alpha, float beta, float delta, float pheroLevel, float eta, float rho){
        this.graph = graph;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.pheroLevel = pheroLevel;
        this.eta = eta;
        this.miu = 10;
        this.rho = rho;
        this.hamiCycles = new ArrayList<>();
        this.simulator = new Simulator(simtime);
    }

    public void storeCycle(ArrayList<T> cycle, int cycle_weight){
        //ArrayList1.equals(ArrayList2) == true
        for (int i=0; i< hamiCycles.size(); i++) {
        	if(hamiCycles.get(i).weight > cycle_weight) {
        		hamiCycles.add(i, new Cycle<T,Integer>(cycle,cycle_weight));
        		return;
        	}
        	if (hamiCycles.get(i).weight == cycle_weight) {
        		if(hamiCycles.get(i).cycle.equals(cycle)) {
        			return;
        		}
        	}
        }
        hamiCycles.add(new Cycle<T,Integer>(cycle,cycle_weight));
        //System.out.println(hamiCycles);
        
    }
    
    public void printCycles() {
    	if (hamiCycles.size() > 0) {
    		System.out.println("  " + hamiCycles.get(0));
    	}
    	for (int i=1; i<6 && i < hamiCycles.size(); i++) {
    		System.out.println("  " + hamiCycles.get(i));
    	}
    }
}
