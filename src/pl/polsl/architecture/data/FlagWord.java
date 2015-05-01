package pl.polsl.architecture.data;

import pl.polsl.architecture.Flag;
import pl.polsl.utils.RegisterChangeListener;

/**
 * Data word representing value stored in flag register.
 * Allow to access single flag.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class FlagWord implements DataWord, RegisterChangeListener {
	/** Value stored in flag register. */
	private Integer value;
	
	/**
	 * Value getter.
	 * @return Value stored in flag register.
	 */
	@Override
	public Integer getValue() {
		return adjust(value, Flag.getCount());
	}

	/**
	 * Value setter.
	 * @param value value to be set in flag register
	 */
	@Override
	public void setValue(Integer value) {
		this.value = adjust(value, Flag.getCount());
	}
	
	/**
	 * Implementation of Data interface.
	 * Allow to get bit count that data occupies.
	 * @return Bit count that data occupies.
	 */
	@Override
	public Integer getBitCount() {
		return Flag.getCount();
	}
	
	/**
	 * Single flag state getter.
	 * @param flag flag which state will be returned
	 * @return State of given flag.
	 */
	public Boolean getFlag(Flag flag) {
		return ((value & (1 << flag.getBitIndex())) != 0);
	}
	
	/**
	 * Single flag state setter.
	 * @param flag flag which state will be set
	 * @param enabled if true flag will be set else flag will be unset
	 */
	public void setFlag(Flag flag, Boolean enabled) {
		if(enabled)
			value |= (1 << flag.getBitIndex());
		else
			value &= ~(1 << flag.getBitIndex());
	}

	/**
	 * Implementation of RegisterChangeListener interface.
	 * When value stored in register is changed,
	 * that register calls this function.
	 * @param command command that listener was registered with
	 * @param data data stored in the register
	 */
	@Override
	public void registerValueChanged(String command, DataWord data) {
		if(!"AccumulatorChanged".equalsIgnoreCase(command))
			return;
    	value = 0;
    	if(data.getValue() == 0)
    		setFlag(Flag.ZERO, true);
    	else {
    		Integer bitCount = data.getBitCount();
    		Integer sign = (1 << (bitCount - 1));
    		if((data.getValue() & sign) != 0)
    			setFlag(Flag.SIGN, true);
    	}
	}
}
