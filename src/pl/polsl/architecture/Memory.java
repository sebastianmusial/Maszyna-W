package pl.polsl.architecture;

import java.util.Vector;

/**
 * 
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Memory implements DataSource, DataTarget {
	/** Bit count defining data word. */
    private Integer bitCount;
    
	/** Vector containing memory cells. */
	Vector<Integer> data = new Vector<>();
	
	/** Reference to address register. */
	Register addressRegister;
    
	/** Logical value indicating if memory was accessed (read or writen) in current tact. */
	boolean memoryAccessed = false;

	/**
	 * Constructor with parameters. Creates Memory objec with
	 * data bit count set to <i>bitCount</i> and addressed with value
	 * of the <i>addressRegister</i>.
	 * @param bitCount - bit count of every value stored in memory
	 * @param addressRegister - reference to address register used
	 * to address memory cell
	 */
	public Memory(Integer bitCount, Register addressRegister) {
		this.bitCount = bitCount;
		this.addressRegister = addressRegister;
		Integer memorySize = 1 << addressRegister.getBitCount(); 
		data.setSize(memorySize);
		for(int i = 0; i < memorySize; ++i)
			data.set(i, i);
	}
	
	/**
     * Implementation of WMachineComponent interface.
     * Resets value indicating if value has been read or written in current tact.
     */
	@Override
	public void nextTact() {
		memoryAccessed = false;
	}

	/**
     * Implementation of WMachineComponent interface.
     * Sets new bit count that single memory cell can store.
     * @param count - new bit count. Must be greater than zero.
     */
	@Override
	public void setBitCount(Integer count) {
		if(count > 0 && count != bitCount) {
			bitCount = count;
		}
	}

	/**
     * Implementation of DataSource interface.
     * Returns value stored in the cell at address stored in address register.
     * If memory was written in current tact exception will be thrown.
     * Before value is returned it is masked by mask returned from function getMask.
     * @throws Exception will be thrown if memory was written in current tact.
     * @return Value stored in the memory cell at address stored in address register.
     */
	@Override
	public Integer getValue() throws Exception {
		if(memoryAccessed)
    		throw new Exception("Jednoczesny odczyt i zapis do pamięci jest niemożliwy.");
		memoryAccessed = true;
		Integer address = addressRegister.getValue();
		return getMask(bitCount) & data.get(address);
	}
	
	/**
     * Implementation of DataSource interface.
     * Set new value in the cell at address stored in address register.
     * If memory was read in current tact exception will be thrown.
     * Before value is set it is masked by mask returned from function getMask.
     * @param value - value to be set in the memory cell.
     * @throws Exception will be thrown if memory was read in current tact.
     */
	@Override
	public void setValue(Integer value) throws Exception {
		if(memoryAccessed)
    		throw new Exception("Jednoczesny odczyt i zapis do pamięci jest niemożliwy.");
		memoryAccessed = true;
		Integer address = addressRegister.getValue();
        data.set(address, getMask(bitCount) & value);
	}

	/**
	 * Size of memory is set to 2^(current address bit count).
	 * Address bit count is read from address register.
	 * @return New memory size.
	 */
	Integer updateSize() {
		Integer addressBitCount = addressRegister.getBitCount();
		Integer memorySize = 1 << addressBitCount;
		if(memorySize != data.size())
			data.setSize(memorySize);
		return memorySize;
	}

	/**
	 * Function to read all values stored in memory.
	 * @return Array of values stored in memory. Before array is returned
	 * every value is masked by mask returned from function getMask.
	 */
	public Integer[] getValues() {
		Integer memorySize = updateSize();
		Integer mask = getMask(bitCount);
		Integer[] values = new Integer[memorySize];
		for(int i = 0; i < memorySize; ++i)
			values[i] = data.get(i) & mask;
		return values;
	}
	
	/**
	 * Function to set value of certain memory cell.
	 * @param index - index of memory to be changed.
	 * @param value - value to be set in the cell.
	 */
	public void setValue(Integer index, Integer value) {
		data.set(index, value);
	}
}
