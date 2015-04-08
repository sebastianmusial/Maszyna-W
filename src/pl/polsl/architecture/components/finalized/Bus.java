package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.VolatileDataStorage;
import pl.polsl.utils.Primitive;

final public class Bus extends VolatileDataStorage {

	public Bus(Primitive<Integer> bitCount) {
		super(bitCount);
	}

}
