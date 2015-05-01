package pl.polsl.architecture.signals;

/**
 * Stop signal will prevent tacts, commands and programs from execution.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class StopSignal implements Signal {
	/** Value indicating if signal is enabled on client side. */
    private boolean enabled = false;
	
	/**
	 * Implementation of Signal interface.
	 * Tell W Machine instance that it is stopped.
	 * @throws Exception never throws exception
	 */
	@Override
	public void activate() throws Exception {
		setEnabled(true);
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
