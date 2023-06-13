package sed;

import java.util.Comparator;
import java.util.PriorityQueue;



// Custom comparator class
class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return Double.compare(event1.getTimestamp(), event2.getTimestamp());
    }
}

public class PEC {
    public static PriorityQueue<Event> PriorityQueue() {
        // Create a Priority Queue
        PriorityQueue<Event> pq = new PriorityQueue<>(new EventComparator());
        return pq;
    }

    public void addEvPEC(Event event, PriorityQueue<Event> pq) {
        // Add items to the Priority Queue (ENQUEUE)
        pq.add(event);
    }

    public Event nextEvPEC(PriorityQueue<Event> pq) {
        // Remove items from the Priority Queue (DEQUEUE)
        return pq.poll();
    }
}