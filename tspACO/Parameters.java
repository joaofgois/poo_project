package tspACO;

import java.util.ArrayList;

import graph.WeightedGraph;

/**
 * Parameters used by the ACO algorithm.
 * 
 * @param <T> Type of the nodes in the graph.
 * @param <E> Type of the weights of the edges in the graph.
 */
public class Parameters<T,E> {
	
    protected float alpha, beta, delta;
    protected float pheroLevel, eta, rho;
    protected int nrPheroEvents, nrAntMoveEvents;
    protected int miu;
    protected WeightedGraph<T, E> graph;
    protected float graphWeight;
    protected ArrayList<Cycle<T, Integer>> hamiCycles;

    /**
     * Function that stores de input parameters.
     */
    public Parameters() {
        this.hamiCycles = new ArrayList<>();
        this.nrAntMoveEvents = 0;
        this.nrPheroEvents = 0;
    }
    
    /**
     * function thatt stores de best cycles found so far.
     * 
     * @param cycle The cycle to be stored.
     * @param cycle_weight The weight of the cycle.
     */
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

}
