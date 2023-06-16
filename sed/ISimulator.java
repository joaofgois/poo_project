package sed;

/**
 * Interface for the Simulator.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public interface ISimulator {

	/**
	 * Starts the simulation.
	 */
	void simulate();

	/**
	 * Adds an event to the PEC
	 * 
	 * @param e - event to be added
	 *
	 */
	void addEvPEC(Event e);

	/**
	 * Removes an event from the PEC
	 * 
	 * @param e - event to be removed
	 *
	 */
	Event nextEvPEC();

	/**
	 * Returns the current time of the simulation
	 * 
	 * @return current time
	 *
	 */
	float getSimTime();
}