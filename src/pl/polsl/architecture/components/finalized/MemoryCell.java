package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.utils.Primitive;

/**
 * Memory cell used to store single value.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class MemoryCell extends NonVolatileDataStorage {
    /**
     * Constructor with bit count as parameter. Constructs memory
     * cell configured to contain bitCount long data word.
     * @param bitCount bit count for data word.
     */
	public MemoryCell(Primitive<Integer> bitCount) {
		super(bitCount);
	}
};