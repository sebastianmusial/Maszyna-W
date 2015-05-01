/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture.signals;

import pl.polsl.architecture.components.DataSource;
import pl.polsl.architecture.components.DataTarget;

/**
 * W Machine data flow signal. When activated cause data to flow
 * from a DataSource to a DataTarget.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class DataFlowSignal implements Signal {
	/** Signal data source. */
    private DataSource source;
    
    /** Signal data target. */
    private DataTarget target;
    
    /** Value indicating if signal is enabled on client side. */
    private boolean enabled = false;
    
    /**
     * Constructs signal configured to pass data from source to target.
     * @param source - source of data.
     * @param target - target for data.
     */
    public DataFlowSignal(DataSource source, DataTarget target) {
        this.source = source;
        this.target = target;
    }
    
    /**
     * Implementation of Signal interface.
     * Cause data to flow from a DataSource to a DataTarget.
     * When either source or target is not set, nothing happens.
     * @throws Exception - may be thrown either by source or
     * target from getValue or setValue function. 
     */
    public void activate() throws Exception {
        if(getSource() == null || getTarget() == null)
            return;
        getTarget().setValue(getSource().getValue());
    }
    
    /**
     * Getter of data source.
     * @return the source
     */
    public DataSource getSource() {
        return source;
    }

    /**
     * Getter of data target.
     * @return the target
     */
    public DataTarget getTarget() {
        return target;
    }
    
    /**
     * Implementation of Signal interface.
     * Check if signal is enabled on client side.
     * @return Value indicating if signal is enabled on client side.
     */
    @Override
    public boolean isEnabled() {
    	return enabled;
    }
    
    /**
     * Implementation of Signal interface.
     * Set value indicating if signal is enabled on client side.
     * @param enabled - state of signal to be set.
     */
    @Override
    public void setEnabled(boolean enabled) {
    	this.enabled = enabled;
    }
}
