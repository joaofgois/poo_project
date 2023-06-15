package sed;

public interface ISimulator {

	void simulate();

	void addEvPEC(Event e);

	Event nextEvPEC();

	float getSimTime();

}