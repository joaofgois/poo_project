package tspACO;

import expRandom.*;
import sed.Event;
import sed.ISimulator;

/**
 * <strong>CLASS</strong><p>
 * blabalbalbalbal mfmemfemfemfef
 */
public class UpdateEvent<T> extends Event {
	ExtRandom rand;
	private int number;
	ISimulator simulator;
	Parameters<T,Integer> parameters;

	public UpdateEvent(float time, ISimulator simulator, Parameters<T,Integer> parameters) {
		super(time);
		this.rand = ExtRandom.getInstance();
		this.number = 1;
		this.simulator = simulator;
		this.parameters = parameters;
	} 

	@Override
	protected void simulateEvent() {
		System.out.println("Observation " + number + ":");
		System.out.printf("%-10s%-30s  %f\n", " ", "Present instant", time);
		System.out.printf("%-10s%-30s  %d\n", " ", "Number of move events:", parameters.nrAntMoveEvents);
		System.out.printf("%-10s%-30s  %d\n", " ", "Number of evaporation events:", parameters.nrPheroEvents);
		// Print (up to 5) best candidate cycles:
		if (parameters.hamiCycles.size() > 2) {
			System.out.printf("%-10s%-30s  %s\n", " ", "Top candidate cycles:", parameters.hamiCycles.get(1));
			for (int i = 2; i < 6 && i < parameters.hamiCycles.size(); i++) {
				System.out.printf("%-10s%-30s  %s\n", " ", " ", parameters.hamiCycles.get(i));
			}
		} else {
			System.out.printf("%-10s%-30s\n", " ", "Top candidate cycles:");
		}
		//print best hamilton cycle
		if (parameters.hamiCycles.size() > 0) {
			System.out.printf("%-10s%-30s  %s\n", " ", "Best Hamilton Cycle:", parameters.hamiCycles.get(0));
		}
		else {
			System.out.printf("%-10s%-30s\n", " ", "Best Hamilton Cycle:");
		}
		System.out.println();
		
		number += 1;
		time += simulator.getSimTime() / 20;
		if (time < simulator.getSimTime()) {
			simulator.addEvPEC(this);
		}
	}

}
