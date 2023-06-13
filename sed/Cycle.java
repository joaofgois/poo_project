package sed;

import java.util.ArrayList;

public class Cycle<T, E> {
    protected ArrayList<T> cycle;
    protected E weight;

    public Cycle(ArrayList<T> cycle, E weight){
        this.cycle = cycle;
        this.weight = weight;
    }

}
