package pl.polsl.architecture.signals;

/**
 * W Machine signal interface.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface Signal {
	/**
	 * Activate signal.
	 * @throws Exception when error occurs.
	 */
	void activate() throws Exception;
	
	/**
     * Check if signal is enabled on client side.
     * @return Value indicating if signal is enabled on client side.
     */
    boolean isEnabled();
    
    /**
     * Set value indicating if signal is enabled on client side.
     * @param enabled - state of signal to be set.
     */
    void setEnabled(boolean enabled);
}
