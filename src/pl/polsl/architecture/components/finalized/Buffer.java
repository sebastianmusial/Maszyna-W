package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.VolatileDataStorage;
import pl.polsl.architecture.data.Data;

/**
 * Buffer to store value for a single tact.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Buffer extends VolatileDataStorage {
    /**
     * Constructor with bit count as parameter. Constructs buffer
     * configured to contain bitCount long data word.
     * @param data data instance to be stored
     */
	public Buffer(Data data) {
		super(data);
	}

}
