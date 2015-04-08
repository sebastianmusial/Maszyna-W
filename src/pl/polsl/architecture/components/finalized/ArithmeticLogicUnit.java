package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;
import pl.polsl.architecture.components.VolatileDataStorage;


/**
 * Arithemit logic unit.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class ArithmeticLogicUnit implements DataSource, DataTarget {
	
	private VolatileDataStorage inputBuffer;
	private VolatileDataStorage resultBuffer;
	
	public ArithmeticLogicUnit(VolatileDataStorage inputBuffer, VolatileDataStorage resultBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = resultBuffer;
	}

	@Override
	public void nextTact() {}

	@Override
	public void setValue(Integer value) throws Exception {
		inputBuffer.setValue(value);
	}

	@Override
	public Integer getValue() throws Exception {
		return resultBuffer.getValue();
	}
}
