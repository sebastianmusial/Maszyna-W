package pl.polsl.architecture.data;

import pl.polsl.utils.Primitive;

/**
 * Class representing command stored in W Machine components.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandWord implements DataWord {
	/** Currently set operation code. */
	private Integer opCode = null;
	
	/** Currently set address. */
	private Integer address = null;

	/** Currently set operation code bit count. */
	private Primitive<Integer> opCodeBitCount;
	
	/** Currently set address bit count. */
	private Primitive<Integer> addressBitCount;
	
	/**
	 * Constructor with address bit count primitive and
	 * operation code bit count primitive as arguments.
	 * @param opCodeBitCount primitive referencing to W Machine
	 * operation code bit count.
	 * @param addressBitCount primitive referencing to W Machine
	 * address bit count.
	 */
	public CommandWord(Primitive<Integer> opCodeBitCount, Primitive<Integer> addressBitCount) {
		this.addressBitCount = addressBitCount;
		this.opCodeBitCount = opCodeBitCount;
	}
	
	/**
	 * Implementation of Data interface.
	 * Allow to get stored command adjusted to proper bit count.
	 * @return Stored command adjusted to proper bit count.
	 */
	@Override
	public Integer getValue() {
		if(opCode != null) {
			Integer addressBitCount = this.addressBitCount.getValue();
			Integer opCodeBitCount = this.opCodeBitCount.getValue();
			opCode = adjust(opCode, opCodeBitCount);
			address = adjust(address, addressBitCount);
			return (opCode << addressBitCount) + address;
		}
		return null;
	}

	/**
	 * Implementation of Data interface.
	 * Allow to set new command. Value will be adjusted
	 * to proper bit count.
	 * @param value command to be set
	 */
	@Override
	public void setValue(Integer value) {
		if(value != null) {
			while(value < 0)
				value = (1 << (addressBitCount.getValue() + opCodeBitCount.getValue())) + value;
			opCode = adjust(value >> addressBitCount.getValue(), opCodeBitCount.getValue());
			address = adjust(value, addressBitCount.getValue());
		}
		else {
			opCode = null;
			address = null;
		}
	}
	
	/**
	 * Implementation of Data interface.
	 * Allow to get bit count that data occupies.
	 * @return Bit count that data occupies.
	 */
	@Override
	public Integer getBitCount() {
		return opCodeBitCount.getValue() + addressBitCount.getValue();
	}
	
	/**
	 * Allow to get currently set operation code.
	 * @return Stored operation code.
	 */
	public Integer getOpCode() {
		opCode = adjust(opCode, opCodeBitCount.getValue());
		return opCode;
	}
	
	/**
	 * Allow to get currently set address.
	 * @return Stored address.
	 */
	public Integer getAddress() {
		address = adjust(address, addressBitCount.getValue());
		return address;
	}
}
