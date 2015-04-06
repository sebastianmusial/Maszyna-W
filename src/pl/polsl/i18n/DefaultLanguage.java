package pl.polsl.i18n;

import pl.polsl.servlet.ArchitectureInfo;

/**
 * Default language.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class DefaultLanguage extends Language {

	/**
	 * Constructs default language instance.
	 */
	public DefaultLanguage() {
		setRegisterName("PROGRAM_COUNTER", "L");
		setRegisterName("INSTRUCTION", "I");
		setRegisterName("ACCUMULATOR", "AK");
		setRegisterName("MEMORY_ADDRESS", "A");
		setRegisterName("MEMORY_DATA", "S");
		setRegisterName("STACK_POINTER", "WS");
		setRegisterName("FLAGS", "F");
		setRegisterName("DATA_X", "X");
		setRegisterName("DATA_Y", "Y");
		setRegisterName("IO_PORT", "RB");
		setRegisterName("STROBE", "G");
		
		setSignalName("PROGRAM_COUNTER_IN", "wel");
		setSignalName("PROGRAM_COUNTER_OUT", "wyl");
		setSignalName("PROGRAM_COUNTER_INCREMENT", "il");
		setSignalName("PROGRAM_COUNTER_OUT_TO_DATA_BUS", "wyls");
		setSignalName("INSTRUCTION_IN", "wei");
		setSignalName("INSTRUCTION_OUT", "wyad");
		setSignalName("ACCUMULATOR_IN", "weak");
		setSignalName("ACCUMULATOR_OUT", "wyak");
		setSignalName("ACCUMULATOR_INCREMENT", "iak");
		setSignalName("ACCUMULATOR_DECREMENT", "dak");
		setSignalName("MEMORY_ADDRESS_IN", "wea");
		setSignalName("MEMORY_DATA_IN", "wes");
		setSignalName("MEMORY_DATA_OUT", "wys");
		setSignalName("MEMORY_WRITE", "pisz");
		setSignalName("MEMORY_READ", "czyt");
		setSignalName("STACK_POINTER_IN", "wews");
		setSignalName("STACK_POINTER_OUT", "wyws");
		setSignalName("STACK_POINTER_INCREMENT", "iws");
		setSignalName("STACK_POINTER_DECREMENT", "dws");
		setSignalName("BUS_CONNECTION", "as (sa)");
		setSignalName("ALU_ADD", "dod");
		setSignalName("ALU_SUBTRACT", "ode");
		setSignalName("ALU_MULTIPLY", "mno");
		setSignalName("ALU_DIVIDE", "dziel");
		setSignalName("ALU_COPY", "przep");
		setSignalName("ALU_SHIFT_RIGHT", "shr");
		setSignalName("ALU_CONJUNCTION", "i");
		setSignalName("ALU_ALTERNATIVE", "lub");
		setSignalName("ALU_NEGATION", "neg");
		setSignalName("DATA_X_IN", "wex");
		setSignalName("DATA_X_OUT", "wyx");
		setSignalName("DATA_Y_IN", "wey");
		setSignalName("DATA_Y_OUT", "wyy");
		setSignalName("IO_PORT_IN", "werb");
		setSignalName("IO_PORT_OUT", "wyrb");
		setSignalName("STROBE_START", "start");
		setSignalName("STROBE_OUT", "wyg");
		setSignalName("ALU_IN", "weja");
	}
	
	/**
	 * Add translated resiter name to language.
	 * @param register name of constant defined in ArchitectureInfo#AvailableRegisters 
	 * @param name translated register name
	 */
	public void setRegisterName(String register, String name) {
		try {
			ArchitectureInfo.AvailableRegisters value = ArchitectureInfo.AvailableRegisters.valueOf(register);
			if(value != null)
				setRegisterName(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
	
	/**
	 * Add translated signal name to language.
	 * @param signal name of constant defined in ArchitectureInfo#AvailableSignals
	 * @param name translated signal name
	 */
	public void setSignalName(String signal, String name) {
		try {
			ArchitectureInfo.AvailableSignals value = ArchitectureInfo.AvailableSignals.valueOf(signal);
			if(value != null)
				signals.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
}
