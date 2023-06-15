package tspACO;
import expRandom.*;
import sed.Event;
import sed.ISimulator;


public class UpdateEvent<T> extends Event {
	ExtRandom rand;
	private int number;
	ISimulator simulator;

	public UpdateEvent(float time, ISimulator simulator, TspACOSimulation<T, Integer> parent) {
		super(time);
		this.rand = ExtRandom.getInstance();
		this.number = 1;
		this.simulator = simulator;
	}

	@Override
	protected void simulateEvent() {
		//Observation number :
		//Present instant: instant
		//Number of move events: mevents
		//Number of evaporation events: eevents
		//Top candidate cycles: cycles
		//Best Hamiltonian cycle: best
		System.out.println("Observation " + number);
		System.out.print("	Present instant " + time);
		System.out.print("	Present instant " + time);
		System.out.print("	Present instant " + time);
		System.out.print("	Present instant " + time);
		System.out.print("	Present instant " + time);
		
		number += 1;
		time += simulator.getSimTime()/20;
		if (time < simulator.getSimTime()) {
			simulator.addEvPEC(this);
		}
	}

}
