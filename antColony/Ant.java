package antColony;

import java.util.LinkedList;

/**
 * This Class ...Aqui guardamos a posicao atual e o path ate ao momento de determinada formiga
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public class Ant<T> {
    protected T position;
    protected LinkedList<T> path;

    /**
     * Sets the position of the ant. 
     * 
     * @param position - the position of the ant
     */
    public Ant(T position){
        this.position = position;
        this.path = new LinkedList<T>();
        this.path.addLast(position);
    }

}