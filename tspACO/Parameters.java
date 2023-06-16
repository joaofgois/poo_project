package tspACO;

import java.util.ArrayList;

import graph.WeightedGraph;

public class Parameters<T,E> {
	
    protected float alpha, beta, delta;
    protected float pheroLevel, eta, rho;
    protected int nrPheroEvents, nrAntMoveEvents;
    protected int miu;
    protected WeightedGraph<T, E> graph;
    protected float graphWeight;
    protected ArrayList<Cycle<T, Integer>> hamiCycles;

    public Parameters() {
    	this.hamiCycles = new ArrayList<>();
        this.nrAntMoveEvents = 0;
        this.nrPheroEvents = 0;
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
    /*
    //FOR DEBUG ONLY---------------------------------------
    public void printCycles() {
    	if (hamiCycles.size() > 0) {
    		System.out.println("  " + hamiCycles.get(0));
    	}
    	for (int i=1; i<6 && i < hamiCycles.size(); i++) {
    		System.out.println("  " + hamiCycles.get(i));
    	}
    }
    */
    
}
