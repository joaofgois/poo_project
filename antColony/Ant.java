package antColony;

import java.util.LinkedList;

/**
 * @param position
 * @param LinkedList
 * 
 * Aqui guardamos a posicao atual e o path ate ao momento de determinada formiga
 */
public class Ant<T> {
    protected T position;
    protected LinkedList<T> path;

    /**
     * @param postion redefine a posicao da formiga 
     * 
     * funcao que redefine a posicao da formiga e adiciona a edge ao path feito ate ao momento
     */

    public Ant(T position){
        this.position = position;
        this.path = new LinkedList<T>();
        this.path.addLast(position);
    }

}