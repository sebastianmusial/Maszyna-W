package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;


/**
 * Arithemit logic unit.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class ArithmeticLogicUnit implements DataSource, DataTarget {
	/** ALU input buffer. */
	private Buffer inputBuffer;
	
	/** ALU output buffer. */
	private Buffer outputBuffer;
	
	/**
	 * Constructs arithmetic logic unit with 
	 * input and output buffer.
	 * @param inputBuffer buffer used to pass data to ALU
	 * @param outputBuffer buffer used to read results from ALU
	 */
	public ArithmeticLogicUnit(Buffer inputBuffer, Buffer outputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = outputBuffer;
	}

	/**
	 * Blank implementation of WMachineComponent interface.
	 */
	@Override
	public void nextTact() {}

	/**
	 * Implementation of DataSource interface.
	 * Read value from output buffer and returns it.
	 * @throws Exception when output buffer has no value
	 * @return Value stored in output buffer.
	 */
	@Override
	public Integer getValue() throws Exception {
		return outputBuffer.getValue();
	}
	
	/**
	 * Implementation of DataSource interface.
	 * Read value from output buffer and returns it.
	 * @return Value stored in output buffer.
	 */
	@Override
	public Integer peekValue() {
		return outputBuffer.peekValue();
	}
	
	/**
	 * Implementation of DataTarget interface.
	 * Write value to input buffer.
	 * @throws Exception when input buffer already has value
	 */
	@Override
	public void setValue(Integer value) throws Exception {
		inputBuffer.setValue(value);
	}
	
	/**
	 * Implementation of DataTarget interface.
	 * Write value to input buffer.
	 */
	@Override
	public void replaceValue(Integer value) {
		inputBuffer.replaceValue(value);
	}
}
