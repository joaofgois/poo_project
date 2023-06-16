package sed;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Event Comparator.
 * 
 * This class is used to compare two events.
 * 
 * @see Event
 * @see Comparator
 * 
 */
class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        int i = Float.compare(event1.getTimeStamp(), event2.getTimeStamp());
        if (i == 0) {
            i = -1;
        }
        return i;
    }
}

/**
 * Priority Event Queue.
 * 
 * This class is used to store the events in a priority queue.
 * 
 * @see Event
 * @see Comparator
 * @see TreeSet
 * @see EventComparator
 * 
 */
public class PEC {
    public TreeSet<Event> pec;
    
    /** 
     * This constructor creates a new Priority Queue.
     */
    public PEC() {
        // Create a Priority Queue
        pec = new TreeSet<>(new EventComparator());
    }

    /**
     * This method adds an event to the Priority Queue.
     * 
     * @param event The event to be added.
     */
    public void addEvPEC(Event event) {
        // Add items to the Priority Queue (ENQUEUE)
        pec.add(event);
    }

    /**
     * This method removes the first event from the Priority Queue.
     * 
     * @return The first event from the Priority Queue.
     */
    public Event nextEvPEC() {
        // Remove items from the Priority Queue (DEQUEUE)
        return pec.pollFirst();
    }
}