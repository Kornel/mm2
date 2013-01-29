package org.kornel.color;

public interface ColorGenerator {

	/**
	 * Returns a code from [0; maxAllowed)
	 * 
	 * @param maxAllowed
	 *            exclusive, upper bound
	 * @return random color
	 */
	int getRandomColor(int maxAllowed);
}
