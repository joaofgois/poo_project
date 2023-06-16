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
		System.out.println("Observation " + number);
		System.out.println("	Present instant " + time);
		System.out.println("	Number of move events: " + TSP.nrAntMoveEvents);
		System.out.println("	Number of evaporation events: " + parameters.nrPheroEvents);
		// Print (up to 5) best candidate cycles:
		if (parameters.hamiCycles.size() > 0) {
			System.out.printf("%-30s ", "Top candidate cycles:");
		}
		for (int i = 1; i < 6 && i < parameters.hamiCycles.size(); i++) {
			System.out.println("  " + parameters.hamiCycles.get(i));
		}
		if (parameters.hamiCycles.size() > 0) {
			System.out.println("	Best Hamilton Cycle: " + parameters.hamiCycles.get(0));
		}
		else {
			System.out.println("	Best Hamilton Cycle: ");
		}

		number += 1;
		time += simulator.getSimTime() / 20;
		if (time < simulator.getSimTime()) {
			simulator.addEvPEC(this);
		}
	}

}
