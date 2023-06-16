package tspACO;

import sed.Event;


//SO PARA DEBUG------------------------------------------------------------------


public class EventTest extends Event {

	public EventTest(float time) {
		super(time);
	}

	@Override
	protected void simulateEvent() {
		System.out.println(this);

	}

	@Override
	public String toString() {
		return "EventTest time : " + time;
	}
	
	
}
