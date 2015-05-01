/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture.components.finalized;

import java.util.HashMap;
import java.util.Map;

import pl.polsl.architecture.components.NonVolatileDataStorage;
import pl.polsl.architecture.data.DataWord;
import pl.polsl.utils.RegisterChangeListener;

/**
 * W Machine register. It's configured by bit count for data word.
 * Implements DataSource and DataTarget interfaces. In one tact
 * one can set only one value in the register. Next call of setValue
 * function will cause exception to be thrown. Calling getValue will
 * never cause exception to be thrown.
 * @author Tomasz Rzepka
 * @version 1.0
 */
final public class Register extends NonVolatileDataStorage {
	/** Registered change listeners. */
	private Map<String, RegisterChangeListener> listeners = new HashMap<>();
	
    /**
     * Constructor with bit count as parameter. Constructs register
     * configured to contain bitCount long data word.
     * @param data data instance to be stored
     */
    public Register(DataWord data) {
    	super(data);
    }
    
    /**
     * Setting value cause listeners to be notified.
     */
    @Override
    public void setValue(Integer value) throws Exception {
    	super.setValue(value);
    	notifyListeners();
    }
    
    /**
     * Replacing value cause listeners to be notified.
     */
    @Override
    public void replaceValue(Integer value) {
    	super.replaceValue(value);
    	notifyListeners();
    }
    
    /**
     * Allow to add register change listener. When value
     * stored in the register is set or replaced
     * all registered listeners will be notified about
     * the change.
     * @param command command that will be passed to listener
     * @param listener change listener to be added
     */
    public void addChangeListener(String command, RegisterChangeListener listener) {
    	listeners.put(command, listener);
    }
    
    /**
     * Notify all change listeners that value was changed.
     */
    private void notifyListeners() {
    	for(String command : listeners.keySet()) {
    		RegisterChangeListener listener = listeners.get(command);
    		listener.registerValueChanged(command, getData());
    	}
    }
}
