package sed;

public class Simulator implements ISimulator {
    private Event currentEvent;
    private float simulationTime;
    private float currentTime;
    private PEC pec;

    public Simulator(float simulationTime){
    	this.pec = new PEC();
        this.simulationTime = simulationTime;
        this.currentTime = 0;

    }
    
    public Simulator(PEC pec, float simulationTime){
    	this.pec = pec;
        this.simulationTime = simulationTime;
        this.currentTime = 0;

    }
    
    @Override
	public void simulate(){
    	if (currentEvent == null) {
    		currentEvent = pec.nextEvPEC();
    		if (currentEvent == null) {
    			return;
    		}
		}
        while(currentTime < simulationTime){
        	currentEvent.simulateEvent();
            
            currentEvent = pec.nextEvPEC();
            if (currentEvent == null){
                //no more events in the PEC
                break;
            }
            currentTime = currentEvent.time;
        }
    }
    
    @Override
	public void addEvPEC(Event e) {
    	pec.addEvPEC(e);
    }
    
    @Override
	public Event nextEvPEC() {
    	return pec.nextEvPEC();
    }
    
    @Override
	public float getSimTime() {
    	return simulationTime;
    }
}
