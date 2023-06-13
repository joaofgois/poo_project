package sed;

public class Simulator {
    private Event currentEvent;
    private float simulationTime;
    private float currentTime;
    protected PEC pec;

    public Simulator(float simulationTime){
        this.simulationTime = simulationTime;
        this.currentTime = 0;

    }
    
    public void simulate(){
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
}
