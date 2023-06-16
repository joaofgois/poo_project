package sed;

/**
 * This class implements the simulation of the system.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 */
public class Simulator implements ISimulator {
    private Event currentEvent;
    private float simulationTime;
    private float currentTime;
    private PEC pec;

    /**
     * Constructor for the Simulator class.
     * 
     * @param simulationTime - The time to simulate.
     */
    public Simulator(float simulationTime) {
        this.pec = new PEC();
        this.simulationTime = simulationTime;
        this.currentTime = 0;

    }
    
    /**
     * Constructor for the Simulator class.
     * 
     * @param pec - The PEC to use.
     * @param simulationTime - The time to simulate.
     */
    public Simulator(PEC pec, float simulationTime) {
        this.pec = pec;
        this.simulationTime = simulationTime;
        this.currentTime = 0;

    }
    
    /**
     * Constructor for the Simulator class.

     */
    @Override
    public void simulate() {
        if (currentEvent == null) {
            currentEvent = pec.nextEvPEC();
            if (currentEvent == null) {
                return;
            }
        }
        while (currentTime <= simulationTime) {
            currentEvent.simulateEvent();

            currentEvent = pec.nextEvPEC();
            if (currentEvent == null) {
                //no more events in the PEC
                break;
            }
            currentTime = currentEvent.time;
        }
    }
    
    /**
     * Function to add an event to the PEC.
     * 
     * @param e - The event to add.
     */
    @Override
    public void addEvPEC(Event e) {
        pec.addEvPEC(e);
    }
    
    /**
     * Function to remove an event from the PEC.
     * 
     * @return The event removed.
     */
    @Override
    public Event nextEvPEC() {
        return pec.nextEvPEC();
    }
    
    /**
     * Function to get the current time.
     * 
     * @return The current time.
     */
    @Override
	public float getSimTime() {
    	return simulationTime;
    }
}
