package sed;

import java.util.ArrayList;

public class Cycle<T, E> {
    protected ArrayList<T> cycle;
    protected E weight;

    public Cycle(ArrayList<T> cycle, E weight){
        this.cycle = cycle;
        this.weight = weight;
    }

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
