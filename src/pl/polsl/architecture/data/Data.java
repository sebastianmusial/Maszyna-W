package pl.polsl.architecture.data;

/**
 * Interface representing data that can be stored
 * in W Machine components.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface Data {
	/**
	 * Allow to get data as single integer value.
	 * @return Value integer equivalent to stored data.
	 * Can be null.
	 */
	Integer getValue();
	
	/**
	 * Allow to set new data. Its passed as single integer
	 * value and then is converted to proper format.
	 * @param value value representing new data. Can be null.
	 */
	void setValue(Integer value);
	
	/**
	 * Helpful function to adjust value to given bit count.
	 * @param value value to be adjusted
	 * @param bitCount value bit count
	 * @return adjusted value
	 */
	default Integer adjust(Integer value, Integer bitCount) {
		if(value != null)
			value &= ~(~(0) << bitCount);
		return value; 
	}
}
