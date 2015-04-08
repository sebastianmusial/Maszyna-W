package pl.polsl.architecture.components;

import pl.polsl.utils.Primitive;

public class NonVolatileDataStorage extends DataStorage {
	/** Current value stored in the storage. */
    private Integer value = 0;

    /** Logical value indicating if value has been changed in current tact. */
    private Boolean valueChanged = false;
    
    /**
     * Constructor with bit count as parameter. Constructs non-volatile
     * data storage configured to contain bitCount long data word.
     * @param bitCount - bit count for data word.
     */
	public NonVolatileDataStorage(Primitive<Integer> bitCount) {
		super(bitCount);
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
     * Implementation of DataSource interface.
     * Returns value stored in the data storage.
     * Before value is returned it is masked by mask returned
     * from function getMask.
     * @throws Exception will never be thrown.
     * @return Value stored in the storage.
     */
    @Override
    public Integer getValue() throws Exception {
        return getMask() & value;
    }
    
    /**
     * Implementation of DataTarget interface.
     * Set new value in the storage. If in current tact value
     * has been changed, cause exception to be thrown.
     * Before value is set it is masked by mask returned
     * from function getMask.
     * @param value - value to be set in the storage.
     * @throws Exception when value has already been set in this tact
     */
    @Override
    public void setValue(Integer value) throws Exception {
    	if(valueChanged)
    		throw new Exception("NonVolatileDataStorage in use.");
        this.value = getMask() & value;
    }
}
