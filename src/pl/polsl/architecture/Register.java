/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

/**
 * W Machine register. It's configured by bit count for data word.
 * Implements DataSource and DataTarget interfaces. In one tact
 * one can set only one value in the register. Next call of setValue
 * function will cause exception to be thrown. Calling getValue will
 * never cause exception to be thrown.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Register implements DataSource, DataTarget {
	/** Current value stored in register. */
    private Integer value;

    /** Bit count defining data word. */
    private Integer bitCount;
    
    /** Logical value indicating if value has been changed in current tact. */
    private Boolean valueChanged = false;
    
    /**
     * Constructor with bit count as parameter. Constructs register
     * configured to contain bitCount long data word.
     * @param bitCount - bit count for data word.
     */
    public Register(Integer bitCount) {
        assert bitCount > 0;
        this.bitCount = bitCount;
        this.value = 0;
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Resets value indicating if value has been changed in current tact.
     */
    @Override
    public void nextTact() {
    	valueChanged = false;
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Sets new bit count that register can store.
     * @param count - new bit count. Must be greater than zero.
     */
	@Override
	public void setBitCount(Integer count) {
		if(count > 0 && count != bitCount)
			bitCount = count;
	}
    
	/**
     * Implementation of DataSource interface.
     * Returns value stored in the register.
     * Before value is returned it is masked by mask returned
     * from function getMask.
     * @throws Exception will never be thrown.
     * @return Value stored in the register.
     */
    @Override
    public Integer getValue() throws Exception {
        return getMask() & value;
    }
    
    /**
     * Implementation of DataTarget interface.
     * Set new value in the register. If in current tact value
     * has been changed, cause exception to be thrown.
     * Before value is set it is masked by mask returned
     * from function getMask.
     * @param value - value to be set in the register.
     * @throws Exception when value has already been set in this tact
     */
    @Override
    public void setValue(Integer value) throws Exception {
    	if(valueChanged)
    		throw new Exception("Rejestr jest używany.");
        this.value = getMask() & value;
    }
    
    /**
     * Function to generate mask depending on bus bit count.
     * getMask() & value gives value adjusted to accurate bit count.
     * @return Mask for value to be set or returned.
     */
    public Integer getMask() {
        return ~((~0) << bitCount);
    }
}
