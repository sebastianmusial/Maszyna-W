package pl.polsl.architecture.components;

import pl.polsl.utils.Primitive;

public abstract class DataStorage implements DataSource, DataTarget {

	private Primitive<Integer> bitCount;
	
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
