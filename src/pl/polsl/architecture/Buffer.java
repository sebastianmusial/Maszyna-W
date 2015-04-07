package pl.polsl.architecture;

public class Buffer implements DataSource, DataTarget {
	/** Current value. After reset it is set to null. */
    private Integer value = null;
    
    /** Bit count defining data word. */
    private Integer bitCount;
    
    public Buffer(Integer bitCount) {
    	this.bitCount = bitCount;
    }
    
	@Override
	public void nextTact() {
		value = null;
	}

	@Override
	public void setBitCount(Integer count) {
		if(count > 0 && count != bitCount)
            bitCount = count;
	}

	@Override
	public Integer getValue() throws Exception {
		if(value == null)
            throw new Exception("Brak wartości w buforze.");
        return getMask(bitCount) & value;
	}

	@Override
	public void setValue(Integer value) throws Exception {
		if(this.value != null)
            throw new Exception("Bufor jest używany.");
        this.value = getMask(bitCount) & value;
	}
}
