package sed;

public abstract class Event {//implements Comparable<Event>{
    protected float time;
    
    //@Override
    public int compareTo(Event arg0) {
        if (this.time == arg0.time){
            return 0;
        }
        if (this.time > arg0.time){
            return 1;
        }
        return -1;
    }



    public Event(float time){
        this.time = time;
    }
    
    public float getTimeStamp() {
    	return time;
    }

    protected abstract void simulateEvent();
    
}
