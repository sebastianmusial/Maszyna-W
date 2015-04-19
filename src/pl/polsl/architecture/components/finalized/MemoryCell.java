package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.utils.Primitive;

/**
 * Memory cell used to store single value.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class MemoryCell extends NonVolatileDataStorage {
	/** Bit count for address. */
	Primitive<Integer> addressBitCount;
	
    /**
     * Constructor with bit count as parameter. Constructs memory
     * cell configured to contain bitCount long data word.
     * @param dataBitCount bit count for data word.
     * @param addressBitCount bit count for address
     */
	public MemoryCell(Primitive<Integer> dataBitCount, Primitive<Integer> addressBitCount) {
		super(dataBitCount);
		this.addressBitCount = addressBitCount;
	}
	
	/**
	 * Function to obtain operation code from value stored in memory cell.
	 * @return Operation code.
	 */
	public Integer getOpCode() {
		return peekValue() >> addressBitCount.getValue();
	}
	
	/**
	 * Function to obtain address (or operand) from value stored in memory cell.
	 * @return Address or operand.
	 */
	public Integer getAddress() {
		return peekValue() & ~((~0) << addressBitCount.getValue());
	}
};