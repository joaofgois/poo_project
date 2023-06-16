package tspACO;

import antColony.IAntColony;
import expRandom.ExtRandom;
import sed.Event;
import sed.ISimulator;

public class PheroEvent<T> extends Event{

    //protected float eta,rho;
    private IAntColony<T> antcolony;
    private ExtRandom rand;
    private Parameters<T, Integer> parameters;
    private ISimulator simulator;

    private T v1;
    private T v2;

    /**
     * 
     * @param time
     * @param antcolony
     * @param parent
     * @param v1
     * @param v2
     */
    public PheroEvent(float time, ISimulator simulator, IAntColony<T> antcolony, Parameters<T,Integer> parameters, T v1, T v2) {
        super(time);
        rand = ExtRandom.getInstance();
        this.antcolony = antcolony;
        this.v1 = v1;
        this.v2 = v2;
        this.parameters = parameters;
        this.simulator = simulator;
        
    }

    @Override
    protected void simulateEvent() {
        
        if (antcolony.setPheromone(v1, v2, -1*parameters.rho) != 0) {
        	this.time += rand.nextExp(parameters.eta);
        	if (this.time < simulator.getSimTime()) {
        		simulator.addEvPEC(this);        	
        	}
        }
        //System.out.println(antcolony.getPheromone(v1, v2));


    }


}
