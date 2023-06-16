package tspACO;

import java.util.ArrayList;

/**
 * This class implements a cycle, which is a list of nodes and a weight.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 */
public class Cycle<T, E> {
    protected ArrayList<T> cycle;
	protected E weight;

	/**
	 * Constructor for the Cycle class.
	 * 
	 * @param cycle The list of nodes.
	 * @param weight The weight of the cycle.
	 */
	public Cycle(ArrayList<T> cycle, E weight) {
		this.cycle = cycle;
		this.weight = weight;
	}

	/**
	 * Getter for the cycle.
	 * 
	 * @return The cycle.
	 */
	@Override
	public String toString() {
		String str = "{";
		for (int i=0; i<cycle.size(); i++) {
			str += cycle.get(i).toString();
			if (i < cycle.size()-1) {
				str += ",";
			}
		}
		return str+"}:" + weight.toString();
	}

}
