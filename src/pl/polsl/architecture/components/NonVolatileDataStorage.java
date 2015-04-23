package pl.polsl.architecture.components;

import pl.polsl.architecture.data.Data;

/**
 * Non volatile data storage always have value,
 * exception from getValue method will never
 * be thrown.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class NonVolatileDataStorage extends DataStorage {
    /** Logical value indicating if value has been changed in current tact. */
    private Boolean valueChanged = false;
    
    /**
     * Constructor with bit count as parameter. Constructs non-volatile
     * data storage configured to contain bitCount long data word.
     * @param data data instance to be stored
     */
	public NonVolatileDataStorage(Data data) {
		super(data);
		data.setValue(0);
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
        return getData().getValue();
    }
    
    /**
     * Implementation of DataSource interface.
     * Returns value set in the storage masked by mask returned
     * from function getMask.
     * @return Value set in the storage. Value may be null.
     */
    @Override
    public Integer peekValue() {
    	return getData().getValue();
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
        getData().setValue(value);
    }
    
    /**
     * Implementation of DataTarget interface.
     * Set new value in the storage. Before value is set
     * it is masked by mask returned from function getMask.
     * @param value - value to be set in the storage. May be null.
     */
    @Override
    public void replaceValue(Integer value) {
    	getData().setValue(value);
    }
}
