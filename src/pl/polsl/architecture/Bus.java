/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.architecture;

/**
 *
 * @author Tomasz Rzepka
 */
public class Bus implements DataSource, DataTarget {
    private Integer value = null;
    private Integer bitCount;
    
    public Bus(Integer bitCount) {
        assert bitCount > 0;
        this.bitCount = bitCount;
    }
    
    @Override
    public void nextTact() {
        value = null;
    }
    
    @Override
    public Integer getValue() throws Exception {
        if(value == null)
            throw new Exception("Brak wartości.");
        return getMask() & value;
    }
    
    @Override
    public void setValue(Integer value) throws Exception {
        if(this.value != null)
            throw new Exception("Magistrala jest używany.");
        this.value = getMask() & value;
    }
    
    public Integer getMask() {
        return ~((~0) << (bitCount - 1));
    }
    
    @Override
	public void setBitCount(Integer count) {
		if(count > 0 && count != bitCount)
			bitCount = count;
	}
}
