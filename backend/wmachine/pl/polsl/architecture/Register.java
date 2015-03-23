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
public class Register implements DataSource, DataTarget {
    private Integer value;
    private Integer bitCount;
    
    public Register(Integer bitCount) {
        assert bitCount > 0;
        this.bitCount = bitCount;
        this.value = 0;
    }
    
    @Override
    public void nextTact() {}
    
    @Override
    public Integer getValue() throws Exception {
        return getMask() & value;
    }
    
    @Override
    public void setValue(Integer value) throws Exception {
        this.value = getMask() & value;
    }
    
    public Integer getMask() {
        return ~((~0) << bitCount);
    }

	@Override
	public void setBitCount(Integer count) {
		if(count > 0 && count != bitCount)
			bitCount = count;
	}
}
