package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.VolatileDataStorage;
import pl.polsl.utils.Primitive;

final public class Buffer extends VolatileDataStorage {

	public Buffer(Primitive<Integer> bitCount) {
		super(bitCount);
	}

}
