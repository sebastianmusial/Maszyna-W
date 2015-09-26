package pl.polsl.architecture.components;

import pl.polsl.architecture.data.DataWord;

/**
 * Volatile data storage means that in every tact
 * new value must be passed to the storage in order
 * to get value from it.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class VolatileDataStorage extends DataStorage {
    /**
     * Constructor with bit count as parameter. Constructs 
     * volative data storage configured to contain bitCount
     * long data word.
     * @param data data instance to be stored
     */
    public VolatileDataStorage(DataWord data) {
        super(data);
    }
    
    /**
     * Implementation of WMachineComponent interface.
     * Inform object that new tact started. Cause storage to reset,
     * i.e. to set current value to null.
     */
    @Override
    public void nextTact() {
        getData().setValue(null);
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
        if(getData().getValue() == null)
            throw new Exception("VolatileDataStorage is empty.");
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
     * has already been set, cause exception to be thrown.
     * Before value is set it is masked by mask returned
     * from function getMask.
     * @param value - value to be set in the storage.
     * @throws Exception when value has already been set in this tact
     */
    @Override
    public void setValue(Integer value) throws Exception {
        if(getData().getValue() != null)
            throw new Exception("VolatileDataStorage is in use.");
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
