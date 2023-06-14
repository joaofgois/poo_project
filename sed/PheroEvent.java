package sed;

import antColony.IAntColony;
import expRandom.ExtRandom;

public class PheroEvent<T> extends Event{

    //protected float eta,rho;
    private IAntColony<T> antcolony;
    private ExtRandom rand;
    private TspACOSimulation<T, Integer> parent;

    private T v1;
    private T v2;


    public PheroEvent(float time, ExtRandom random, IAntColony<T> antcolony,TspACOSimulation<T,Integer> parent, T v1, T v2) {
        super(time);
        rand = ExtRandom.getInstance();
        this.antcolony = antcolony;
        this.v1 = v1;
        this.v2 = v2;
        this.parent = parent;
        
    }

    @Override
    void simulateEvent() {
        //parent.PheroEvCounter ++
        antcolony.setPheromone(v1, v2, -1* parent.rho);

        //parent.pec.adddEvPEC(new AntMoveEvent(time + rand.nextExp(parent.eta), xxxx, yyyy));


    }


}
