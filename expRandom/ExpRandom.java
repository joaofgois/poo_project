package expRandom;

import java.util.Random;

//Thread Safe Singleton
/**
 * @author joaop
 *  Random number generator using with a Thread Safe Singleton pattern
 */
public class ExpRandom extends Random{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ExpRandom instance;
	//private Random random;
	
	private ExpRandom() {
		//random = new Random();
	}
	
	/**
	 * ola<p>
	 * ola
	 * ola
	 * @return ada
	 * @author deee
	 */
	public static synchronized ExpRandom getInstance() {
		if (instance == null) {
			instance = new ExpRandom();
		}
		return instance;
	}

	public double nextExp(double lambda) {
		return Math.log(1-this.nextDouble())/(-lambda);
	}
}
