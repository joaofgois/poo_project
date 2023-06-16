package sed;

/**
 * Class that creates abstract events.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 * 
 */
public abstract class Event {
    protected float time;
    
    /**
     * Compares two events.
     * 
     * @param arg0 - event to be compared.
     * 
     * @return 0 if the events are equal, 1 if the first event is greater than the second, -1 otherwise.
     */
    public int compareTo(Event arg0) {
        if (this.time == arg0.time) {
            return 0;
        }
        if (this.time > arg0.time) {
            return 1;
        }
        return -1;
    }

    /**
     * Creates an event.
     * 
     * @param time - time of the event.
     */
    public Event(float time) {
        this.time = time;
    }
    
    /**
     * Returns the time of the event.
     * 
     * @return time of the event.
     */
    public float getTimeStamp() {
        return time;
    }

    /**
     * Simulates the event.
     */
    protected abstract void simulateEvent();
}
