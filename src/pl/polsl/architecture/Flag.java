package pl.polsl.architecture;

/**
 * Enumeration of flags available in flag register.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public enum Flag {
	/** Indicates that in accumulator is zero. */
	ZERO(0),
	/** Indicates that in accumulator is negative value. */
	SIGN(1),
	/** Indicates that interrup occured. */
	INTERRUPT(2);
	
	/**
	 * Flag count getter.
	 * @return Count of flags.
	 */
	public static Integer getCount() {
		return Flag.values().length;
	}
	
	/**
	 * Bit index getter.
	 * @return Bit index for this flag.
	 */
	public Integer getBitIndex() {
		return bitIndex;
	}
	
	/** Indicates which bit is asigned to this flag. */
	private final Integer bitIndex;
	
	/**
	 * Construct flag with index of bit assigned to this flag.
	 * @param bitIndex index of bit assigned to this flag
	 */
	private Flag(Integer bitIndex) {
		this.bitIndex = bitIndex;
	}
}
