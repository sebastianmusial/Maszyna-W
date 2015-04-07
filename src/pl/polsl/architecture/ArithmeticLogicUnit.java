package pl.polsl.architecture;

/**
 * Arithemit logic unit.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class ArithmeticLogicUnit implements DataSource, DataTarget {
	
	private Buffer inputBuffer;
	private Buffer resultBuffer;
	
	public ArithmeticLogicUnit(Buffer inputBuffer, Buffer resultBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = resultBuffer;
	}

	@Override
	public void nextTact() {}

	@Override
	public void setBitCount(Integer count) {}

	@Override
	public void setValue(Integer value) throws Exception {
		inputBuffer.setValue(value);
	}

	@Override
	public Integer getValue() throws Exception {
		return resultBuffer.getValue();
	}
}
