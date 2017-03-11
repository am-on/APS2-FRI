package aps2.hashmap;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class HashFunction {
	public static enum HashingMethod {
		DivisionMethod,
		KnuthMethod
	};
	
	/**
	 * Hash function using division method.
	 * If negative key is given, first multiply it by -1.
	 * 
	 * @param k Key
	 * @param m Table size
	 * @return Index in the table of size m.
	 */
	public static int DivisionMethod(int k, int m) {
		if (k < 0) {
			k *= -1;
		}
		throw new UnsupportedOperationException("You need to implement this function!");
	}
	
	/**
	 * Hash function using multiplication method implemented by Knuth:
	 * h(k) = m (k A mod 1)
	 * 
	 * Where A is the inverse golden ratio (\sqrt(5)-1)/2.
	 * Use double precision number type.
	 * If negative key is given, first multiply it by -1.
	 * 
	 * @param k Key
	 * @param m Table size
	 * @return Index in the table of size m.
	 */
	public static int KnuthMethod(int k, int m) {
		if (k < 0) {
			k *= -1;
		}

		Double A = (sqrt(5)-1) / 2;
		
		return (int) (m * (k * A % 1));
	}
}