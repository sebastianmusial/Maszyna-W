package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.VolatileDataStorage;
import pl.polsl.architecture.data.DataWord;

/**
 * Bus used to pass value between registers in a single tact.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Bus extends VolatileDataStorage {
    /**
     * Constructor with bit count as parameter. Constructs bus
     * configured to contain bitCount long data word.
     * @param data data instance to be stored
     */
	public Bus(DataWord data) {
		super(data);
	}

}
