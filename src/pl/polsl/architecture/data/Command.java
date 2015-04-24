package pl.polsl.architecture.data;

import pl.polsl.utils.Primitive;

/**
 * 
 * @author Tomasz Rzepka
 *
 */
public class Command implements Data {
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
	public Command(Primitive<Integer> opCodeBitCount, Primitive<Integer> addressBitCount) {
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
			opCode = adjust(value >> addressBitCount.getValue(), opCodeBitCount.getValue());
			address = adjust(value, addressBitCount.getValue());
		}
		else {
			opCode = null;
			address = null;
		}
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
