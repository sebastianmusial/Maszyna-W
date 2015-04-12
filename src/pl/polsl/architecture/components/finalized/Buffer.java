package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.VolatileDataStorage;
import pl.polsl.utils.Primitive;

/**
 * Buffer to store value for a single tact.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Buffer extends VolatileDataStorage {
    /**
     * Constructor with bit count as parameter. Constructs buffer
     * configured to contain bitCount long data word.
     * @param bitCount bit count for data word.
     */
	public Buffer(Primitive<Integer> bitCount) {
		super(bitCount);
	}

}
