package pl.polsl.architecture.data;

import pl.polsl.utils.Primitive;

/**
 * Class representing address stored in W Machine components.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class AddressWord implements DataWord {
	
	/** Address of memory cell. */
	private Integer address = null;
	
	/** Address bit count. */
	private Primitive<Integer> bitCount;
	
	/**
	 * Constructor with address bit count primitive as argument.
	 * @param addressBitCount primitive referencing to W Machine
	 * address bit count.
	 */
	public AddressWord(Primitive<Integer> addressBitCount) {
		bitCount = addressBitCount;
	}
	
	/**
	 * Implementation of Data interface.
	 * Allow to get stored address adjusted to proper bit count.
	 * @return Stored address adjusted to proper bit count.
	 */
	@Override
	public Integer getValue() {
		address = adjust(address, bitCount.getValue());
		return address;
	}
	
	/**
	 * Implementation of Data interface.
	 * Allow to set new address. Value will be adjusted
	 * to proper bit count.
	 * @param value address to be set
	 */
	@Override
	public void setValue(Integer value) {
		while(value != null && value < 0)
			value = (1 << bitCount.getValue()) + value;
		address = adjust(value, bitCount.getValue());
	}

	/**
	 * Implementation of Data interface.
	 * Allow to get bit count that data occupies.
	 * @return Bit count that data occupies.
	 */
	@Override
	public Integer getBitCount() {
		return bitCount.getValue();
	}
}
