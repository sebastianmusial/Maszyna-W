package pl.polsl.architecture.components;

import pl.polsl.utils.Primitive;

/**
 * Volatile data storage means that in every tact
 * new value must be passed to the storage in order
 * to get value from it.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class VolatileDataStorage extends DataStorage {
	/** Current value. After reset it is set to null. */
    private Integer value = null;
    
    /**
     * Constructor with bit count as parameter. Constructs 
     * volative data storage configured to contain bitCount
     * long data word.
     * @param bitCount - bit count for data word.
     */
    public VolatileDataStorage(Primitive<Integer> bitCount) {
        super(bitCount);
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Inform object that new tact started. Cause storage to reset,
     * i.e. to set current value to null.
     */
    @Override
    public void nextTact() {
        value = null;
    }
    
    /**
     * Implementation of DataSource interface.
     * Returns value set in the storage. If in current tact value
     * has not been set, cause exception to be thrown.
     * Before value is returned it is masked by mask returned
     * from function getMask.
     * @throws Exception when no value has been set in this tact
     * @return Value set in the storage.
     */
    @Override
    public Integer getValue() throws Exception {
        if(value == null)
            throw new Exception("VolatileDataStorage is in use.");
        return getMask() & value;
    }
    
    /**
     * Implementation of DataTarget interface.
     * Set new value in the storage. If in current tact value
     * has already been set, cause exception to be thrown.
     * Before value is set it is masked by mask returned
     * from function getMask.
     * @param value - value to be set in the storage.
     * @throws Exception when value has already been set in this tact
     */
    @Override
    public void setValue(Integer value) throws Exception {
        if(this.value != null)
            throw new Exception("VolatileDataStorage is in use.");
        this.value = getMask() & value;
    }
}
