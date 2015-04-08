package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.utils.Primitive;

final public class MemoryCell extends NonVolatileDataStorage {
	public MemoryCell(Primitive<Integer> bitCount) {
		super(bitCount);
	}
};