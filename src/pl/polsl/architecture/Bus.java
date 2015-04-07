/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

/**
 * W Machine bus. It's configured by bit count for data word.
 * Implements DataSource and DataTarget interfaces.
 * Every tact starts with reset of the bus. In one tact one can
 * set only one value to the bus. Next call of setValue function
 * will cause exception to be thrown. Calling getValue before any
 * value is set will also cause exception to be thrown.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Bus implements DataSource, DataTarget {
	/** Current value. After reset it is set to null. */
    private Integer value = null;
    
    /** Bit count defining data word. */
    private Integer bitCount;
    
    /**
     * Constructor with bit count as parameter. Constructs bus
     * configured to contain bitCount long data word.
     * @param bitCount - bit count for data word.
     */
    public Bus(Integer bitCount) {
        assert bitCount > 0;
        this.bitCount = bitCount;
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Inform object that new tact started. Cause bus to reset,
     * i.e. to set current value to null.
     */
    @Override
    public void nextTact() {
        value = null;
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Sets new bit count that bus can store.
     * @param count - new bit count. Must be greater than zero.
     */
    @Override
    public void setBitCount(Integer count) {
        if(count > 0 && count != bitCount)
            bitCount = count;
    }
    
    /**
     * Implementation of DataSource interface.
     * Returns value set on bus. If in current tact value
     * has not been set, cause exception to be thrown.
     * Before value is returned it is masked by mask returned
     * from function getMask.
     * @throws Exception when no value has been set in this tact
     * @return Value set on the bus.
     */
    @Override
    public Integer getValue() throws Exception {
        if(value == null)
            throw new Exception("Magistrala jest pusta.");
        return getMask(bitCount) & value;
    }
    
    /**
     * Implementation of DataTarget interface.
     * Set new value on bus. If in current tact value
     * has already been set, cause exception to be thrown.
     * Before value is set it is masked by mask returned
     * from function getMask.
     * @param value - value to be set on the bus.
     * @throws Exception when value has already been set in this tact
     */
    @Override
    public void setValue(Integer value) throws Exception {
        if(this.value != null)
            throw new Exception("Magistrala jest używana.");
        this.value = getMask(bitCount) & value;
    }
}
