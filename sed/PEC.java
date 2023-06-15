package sed;

import java.util.Comparator;
import java.util.TreeSet;

// Custom comparator class
class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return Float.compare(event1.getTimeStamp(), event2.getTimeStamp());
    }
}

public class PEC {
    public TreeSet<Event> pec;

    public PEC() {
        // Create a Priority Queue
        pec = new TreeSet<>(new EventComparator());
    }

    public void addEvPEC(Event event) {
        // Add items to the Priority Queue (ENQUEUE)
        pec.add(event);
    }

    public Event nextEvPEC() {
        // Remove items from the Priority Queue (DEQUEUE)
        return pec.pollFirst();
    }
}