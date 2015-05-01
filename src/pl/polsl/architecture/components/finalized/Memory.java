package pl.polsl.architecture.components.finalized;

import java.util.Vector;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;
import pl.polsl.architecture.data.CommandWord;
import pl.polsl.utils.Primitive;
import pl.polsl.utils.PrimitiveChangeListener;

/**
 * W Machine memory.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Memory implements DataSource, DataTarget, PrimitiveChangeListener<Integer> {
	/** Bit count defining data word. */
    private Primitive<Integer> opCodeBitCount;
    
    /** Bit count defining address. */
    private Primitive<Integer> addressBitCount;
    
	/** Vector containing memory cells. */
    private Vector<MemoryCell> cells = new Vector<>();
	
	/** Reference to address register. */
    private Register addressRegister;
    
	/** Logical value indicating if memory was accessed (read or writen) in current tact. */
	boolean memoryAccessed = false;

	/**
	 * Constructor with parameters. Creates Memory objec with
	 * data bit count set to <i>bitCount</i> and addressed with value
	 * of the <i>addressRegister</i>.
	 * @param addressRegister reference to address register used
	 * to address memory cell
	 * @param addressBitCount bit count used to store addresses
	 * @param opCodeBitCount bit count used to store data
	 */
	public Memory(Register addressRegister, Primitive<Integer> addressBitCount, Primitive<Integer> opCodeBitCount) {
		this.addressRegister = addressRegister;
		this.opCodeBitCount = opCodeBitCount;
		this.addressBitCount = addressBitCount;
		Integer memorySize = 1 << addressBitCount.getValue(); 
		cells.setSize(memorySize);
		for(int i = 0; i < memorySize; ++i) {
			cells.set(i, new MemoryCell(new CommandWord(opCodeBitCount, addressBitCount)));
			setValue(i, i);
		}
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
		Integer address = addressRegister.peekValue();
		return cells.get(address).getValue();
	}
	
	/**
     * Implementation of DataSource interface.
     * Returns value stored in the cell at address stored in address register.
     * Before value is returned it is masked by mask returned from function getMask.
     * @return Value stored in the memory cell at address stored in address register.
     */
	@Override
	public Integer peekValue() {
		Integer address = addressRegister.peekValue();
		return cells.get(address).peekValue();
	}
	
	/**
     * Implementation of DataSource interface.
     * Set new value in the cell at address stored in address register.
     * If memory was read in current tact exception will be thrown.
     * Before value is set it is masked by mask returned from function getMask.
     * @param value value to be set in the memory cell.
     * @throws Exception will be thrown if memory was read in current tact.
     */
	@Override
	public void setValue(Integer value) throws Exception {
		if(memoryAccessed)
    		throw new Exception("Jednoczesny odczyt i zapis do pamięci jest niemożliwy.");
		memoryAccessed = true;
		Integer address = addressRegister.peekValue();
        cells.get(address).setValue(value);
	}
	
	/**
     * Implementation of DataSource interface.
     * Set new value in the cell at address stored in address register.
     * Before value is set it is masked by mask returned from function getMask.
     * @param value value to be set in the memory cell.
     */
	@Override
	public void replaceValue(Integer value) {
		Integer address = addressRegister.peekValue();
        cells.get(address).replaceValue(value);
	}

	/**
	 * Function to read all values stored in memory.
	 * @return Array of values stored in memory. Before array is returned
	 * every value is masked by mask returned from function getMask.
	 */
	public Integer[] getValues() {
		return cells.stream().map( cell -> {
			try {
				return cell.getValue();
			}
			catch(Exception ex) {
				return 0;
			}
		} ).toArray(Integer[]::new);
	}
	
	/**
	 * Function to get value of certain memory cell.
	 * @param index index of memory cell to be returned.
	 * @return Value stored in selected cell.
	 */
	public Integer getValue(Integer index) {
		try {
			MemoryCell cell = cells.get(index); 
			return cell.getValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Function to set value of certain memory cell.
	 * @param index index of memory cell to be changed.
	 * @param value value to be set in the cell.
	 */
	public void setValue(Integer index, Integer value) {
		try {
			MemoryCell cell = cells.get(index); 
			cell.setValue(value);
			cell.nextTact();
		} catch (Exception e) {
			// ignore
		}
	}

	/**
	 * Implementation of PrimitiveChangeListener.
	 * Change memory size after address bit count has changed.
	 */
	@Override
	public void primitiveChanged(Primitive<Integer> primitive) {
		Integer newAddressBitCount = primitive.getValue();
		Integer newSize = 1 << newAddressBitCount;
		Integer oldSize = cells.size();
		if(newSize != oldSize) {
			cells.setSize(newSize);
			if(newSize > oldSize) {
				for(int i = oldSize; i < newSize; ++i)
					cells.set(i, new MemoryCell(new CommandWord(opCodeBitCount, addressBitCount)));
			}
		}
	}
	
	/**
	 * Allow to get single MemoryCell instance.
	 * @param index index of MemoryCell to be returned
	 * @return MemoryCell at given index.
	 * @throws IndexOutOfBoundsException when index is out of bounds
	 */
	public MemoryCell getCell(Integer index) throws IndexOutOfBoundsException {
		return cells.get(index);
	}
	
	/**
	 * Allow to get all memory cells at once.
	 * @return Array of MemoryCell's.
	 */
	public MemoryCell[] getCells() {
		return cells.toArray(new MemoryCell[cells.size()]);
	}
}
