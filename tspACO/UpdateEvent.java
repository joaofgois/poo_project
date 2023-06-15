package tspACO;

import expRandom.*;
import sed.Event;
import sed.ISimulator;

public class UpdateEvent<T> extends Event {
	ExtRandom rand;
	private int number;
	ISimulator simulator;
	TSP parent;

	public UpdateEvent(float time, ISimulator simulator, TSP parent) {
		super(time);
		this.rand = ExtRandom.getInstance();
		this.number = 1;
		this.simulator = simulator;
		this.parent = parent;
	}

	@Override
	protected void simulateEvent() {
		System.out.println("Observation " + number);
		System.out.println("	Present instant " + time);
		System.out.println("	Number of move events: " + TSP.nrAntMoveEvents);
		System.out.println("	Number of evaporation events: " + parent.nrPheroEvents);
		// Print (up to 5) best candidate cycles:
		if (parent.hamiCycles.size() > 0) {
			System.out.printf("%-30s ", "Top candidate cycles:");
		}
		for (int i = 1; i < 6 && i < parent.hamiCycles.size(); i++) {
			System.out.println("  " + parent.hamiCycles.get(i));
		}
		if (parent.hamiCycles.size() > 0) {
			System.out.println("	Best Hamilton Cycle: " + parent.hamiCycles.get(0));
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
