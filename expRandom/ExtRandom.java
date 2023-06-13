package expRandom;

import java.util.Random;

//Thread Safe Singleton
/**
 *  Random number generator using with a Thread Safe Singleton pattern.
 * <p>
 * Must be initialized with the <strong>getInstance()</strong> method wich replaces the Constructor.
 */
public class ExtRandom extends Random{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ExtRandom instance;
	//private Random random;
	
	private ExtRandom() {
		//random = new Random();
	}
	
	/**
	 * Used to initialize ExtRandom Class, replacing the Constructor
	 * @return Reference to ExpRandom object
	 */
	public static synchronized ExtRandom getInstance() {
		if (instance == null) {
			instance = new ExtRandom();
		}
		return instance;
	}

	public double nextExp(double lambda) {
		return Math.log(1-this.nextDouble())/(-lambda);
	}

	/**
	 * Chooses randomly one entry of the input array, based on the given probabilities provided in such array.
	 * @param frequency array of type int/float/double with absolute or relative frequencies, or probabilities for each index
	 * @return Randomly chosen index
	 */
	public int chooseRand(float[] frequency){
		if (frequency == null){
			return -1;
		}
		float sum = 0;
		for (float f : frequency) {
			sum += f;
		}
		float chosen = nextFloat()*sum;
		sum = 0;
		for (int i=0; i<frequency.length; i++) {
			sum += frequency[i];
			if (chosen < sum){
				return i;
			}
		}	
		return -1;
	}
	/**
	 * Chooses randomly one entry of the input array, based on the given probabilities provided in such array.
	 * @param frequency array of type int/float/double with absolute or relative frequencies, or probabilities for each index
	 * @return Randomly chosen index
	 */
	public int chooseRand(int[] frequency){
		if (frequency == null){
			return -1;
		}
		int sum = 0;
		for (int f : frequency) {
			sum += f;
		}
		int chosen = nextInt(sum);
		sum = 0;
		for (int i=0; i<frequency.length; i++) {
			sum += frequency[i];
			if (chosen < sum){
				return i;
			}
		}	
		return -1;
	}
	/**
	 * Chooses randomly one entry of the input array, based on the given probabilities provided in such array.
	 * @param frequency array of type int/float/double with absolute or relative frequencies, or probabilities for each index
	 * @return Randomly chosen index
	 */
	public int chooseRand(double[] frequency){
		if (frequency == null){
			return -1;
		}
		double sum = 0;
		for (double f : frequency) {
			sum += f;
		}
		double chosen = nextDouble()*sum;
		sum = 0;
		for (int i=0; i<frequency.length; i++) {
			sum += frequency[i];
			if (chosen < sum){
				return i;
			}
		}	
		return -1;
	}
}
