package pl.polsl.architecture.components;

import pl.polsl.utils.Primitive;

/**
 * Abstract class representing generic data storage.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public abstract class DataStorage implements DataSource, DataTarget {

	/** Bit count that this storage can keep. */
	private Primitive<Integer> bitCount;
	
	/**
	 * Creates data storage configured to keep
	 * bitCount long data words.
	 * @param bitCount bit count for data word.
	 */
	public DataStorage(Primitive<Integer> bitCount) {
		this.bitCount = bitCount;
	}
	
	/**
     * Function to generate mask depending on bus bit count.
     * getMask() &amp; integer_value gives value adjusted to accurate bit count.
     * @return Mask that keep specified bits in value after logical conjunction.
     */
    public Integer getMask() {
        return ~((~0) << bitCount.getValue());
    }
}
