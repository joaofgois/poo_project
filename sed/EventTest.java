package sed;

public class EventTest extends Event {

	public EventTest(float time) {
		super(time);
	}

	@Override
	void simulateEvent() {
		System.out.println(this);

	}

	@Override
	public String toString() {
		return "EventTest time : " + time;
	}
	
	
}
