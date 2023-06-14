package sed;

import antColony.IAntColony;
import expRandom.ExtRandom;

public class PheroEvent<T> extends Event{

    //protected float eta,rho;
    private IAntColony<T> antcolony;
    private ExtRandom rand;
    private TspACOSimulation<T, Integer> parent;
    private Simulator simulator;

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
    public PheroEvent(float time, Simulator simulator, IAntColony<T> antcolony, TspACOSimulation<T,Integer> parent, T v1, T v2) {
        super(time);
        rand = ExtRandom.getInstance();
        this.antcolony = antcolony;
        this.v1 = v1;
        this.v2 = v2;
        this.parent = parent;
        this.simulator = simulator;
        
    }

    @Override
    void simulateEvent() {
        
        if (antcolony.setPheromone(v1, v2, -1*parent.rho) != 0) {
        	this.time += rand.nextExp(parent.eta);
        	if (this.time < parent.simulator.getSimTime()) {
        		parent.simulator.getPEC().addEvPEC(this);        	
        	}
        }
        System.out.println(antcolony.getPheromone(v1, v2));


    }


}
