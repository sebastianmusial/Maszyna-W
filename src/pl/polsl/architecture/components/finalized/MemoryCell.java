package pl.polsl.architecture.components.finalized;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.architecture.data.CommandWord;

/**
 * Memory cell used to store single value.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class MemoryCell extends NonVolatileDataStorage {
	/** Data stored in this memory cell. */
	CommandWord data;
	
    /**
     * Constructor with bit count as parameter. Constructs memory
     * cell configured to contain bitCount long data word.
     * @param data command instance to be stored
     */
	public MemoryCell(CommandWord data) {
		super(data);
		this.data = data;
	}
	
	/**
	 * Function to obtain operation code from value stored in memory cell.
	 * @return Operation code.
	 */
	public Integer getOpCode() {
		return data.getOpCode();
	}
	
	/**
	 * Function to obtain address (or operand) from value stored in memory cell.
	 * @return Address or operand.
	 */
	public Integer getAddress() {
		return data.getAddress();
	}
};