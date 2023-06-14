package antColony;

import java.util.LinkedList;

public class Ant<T> {
    protected T position;
    protected LinkedList<T> path;

    public Ant(T position){
        this.position = position;
        this.path = new LinkedList<T>();
        this.path.addLast(position);
    }

}