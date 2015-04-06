package pl.polsl.i18n;

import pl.polsl.servlet.ArchitectureInfo;

public class LanguageReader {
	
	public Language readLanguage(String filename) {
		Language lang = new Language();
		
		addRegisterName(lang, "PROGRAM_COUNTER", "L");
		
		//lang.registers.put(ArchitectureInfo.AvailableRegisters.PROGRAM_COUNTER.ID, "L");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.INSTRUCTION.ID, "I");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.ACCUMULATOR.ID, "AK");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.MEMORY_ADDRESS.ID, "A");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.MEMORY_DATA.ID, "S");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.STACK_POINTER.ID, "WS");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.FLAGS.ID, "F");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.DATA_X.ID, "X");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.DATA_Y.ID, "Y");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.IO_PORT.ID, "RB");
		lang.registers.put(ArchitectureInfo.AvailableRegisters.STROBE.ID, "G");
		
		lang.signals.put(ArchitectureInfo.AvailableSignals.PROGRAM_COUNTER_IN.ID, "wel");
		lang.signals.put(ArchitectureInfo.AvailableSignals.PROGRAM_COUNTER_OUT.ID, "wyl");
		lang.signals.put(ArchitectureInfo.AvailableSignals.PROGRAM_COUNTER_INCREMENT.ID, "il");
		lang.signals.put(ArchitectureInfo.AvailableSignals.PROGRAM_COUNTER_OUT_TO_DATA_BUS.ID, "wyls");
		lang.signals.put(ArchitectureInfo.AvailableSignals.INSTRUCTION_IN.ID, "wei");
		lang.signals.put(ArchitectureInfo.AvailableSignals.INSTRUCTION_OUT.ID, "wyad");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ACCUMULATOR_IN.ID, "weak");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ACCUMULATOR_OUT.ID, "wyak");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ACCUMULATOR_INCREMENT.ID, "iak");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ACCUMULATOR_DECREMENT.ID, "dak");
		lang.signals.put(ArchitectureInfo.AvailableSignals.MEMORY_ADDRESS_IN.ID, "wea");
		lang.signals.put(ArchitectureInfo.AvailableSignals.MEMORY_DATA_IN.ID, "wes");
		lang.signals.put(ArchitectureInfo.AvailableSignals.MEMORY_DATA_OUT.ID, "wys");
		lang.signals.put(ArchitectureInfo.AvailableSignals.MEMORY_WRITE.ID, "pisz");
		lang.signals.put(ArchitectureInfo.AvailableSignals.MEMORY_READ.ID, "czyt");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STACK_POINTER_IN.ID, "wews");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STACK_POINTER_OUT.ID, "wyws");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STACK_POINTER_INCREMENT.ID, "iws");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STACK_POINTER_DECREMENT.ID, "dws");
		lang.signals.put(ArchitectureInfo.AvailableSignals.BUS_CONNECTION.ID, "as (sa)");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_ADD.ID, "dod");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_SUBTRACT.ID, "ode");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_MULTIPLY.ID, "mno");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_DIVIDE.ID, "dziel");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_COPY.ID, "przep");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_SHIFT_RIGHT.ID, "shr");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_CONJUNCTION.ID, "i");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_ALTERNATIVE.ID, "lub");
		lang.signals.put(ArchitectureInfo.AvailableSignals.ALU_NEGATION.ID, "neg");
		lang.signals.put(ArchitectureInfo.AvailableSignals.DATA_X_IN.ID, "wex");
		lang.signals.put(ArchitectureInfo.AvailableSignals.DATA_X_OUT.ID, "wyx");
		lang.signals.put(ArchitectureInfo.AvailableSignals.DATA_Y_IN.ID, "wey");
		lang.signals.put(ArchitectureInfo.AvailableSignals.DATA_Y_OUT.ID, "wyy");
		lang.signals.put(ArchitectureInfo.AvailableSignals.IO_PORT_IN.ID, "werb");
		lang.signals.put(ArchitectureInfo.AvailableSignals.IO_PORT_OUT.ID, "wyrb");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STROBE_START.ID, "start");
		lang.signals.put(ArchitectureInfo.AvailableSignals.STROBE_OUT.ID, "wyg");
		addSignalName(lang, "ALU_IN", "weja");
		
		return lang;
	}
	
	public void addRegisterName(Language lang, String register, String name) {
		try {
			ArchitectureInfo.AvailableRegisters value = ArchitectureInfo.AvailableRegisters.valueOf(register);
			if(value != null)
				lang.registers.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
	
	public void addSignalName(Language lang, String signal, String name) {
		try {
			ArchitectureInfo.AvailableSignals value = ArchitectureInfo.AvailableSignals.valueOf(signal);
			if(value != null)
				lang.signals.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
}
