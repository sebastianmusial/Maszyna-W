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
		
		setUITextName("SITE_HEADER", "Maszyna W");
		setUITextName("SIMULATOR_TAB", "Symulator");
		setUITextName("USER_MENU_DROP_BOX", "WITAJ NIEZNAJOMY!");
		setUITextName("USER_REGISTER_TAB", "REJESTRACJA");
		setUITextName("USER_LOGIN_TAB", "LOGOWANIE");
		setUITextName("MANUAL_CONTROL_CHECK_BOX", "Sterowanie ręczne");
		setUITextName("TRACKING_LEVEL_PANEL", "Poziom śledzenia");
		setUITextName("TRACKING_LEVEL_LOW_OPTION", "Niski (program)");
		setUITextName("TRACKING_LEVEL_MEDIUM_OPTION", "Średni (rozkaz)");
		setUITextName("TRACKING_LEVEL_HIGH_OPTION", "Wysoki (takt)");
		setUITextName("TYPES_AND_COMPONENTS_PANEL", "Typy i składniki");
		setUITextName("TYPE_W_RADIO_BUTTON", "W");
		setUITextName("TYPE_W_PLUS_RADIO_BUTTON", "W+");
		setUITextName("TYPE_L_RADIO_BUTTON", "L");
		setUITextName("TYPE_EW_RADIO_BUTTON", "EW");
		setUITextName("BUS_CONNECTION_CHECK_BOX", "Połączenie międzymagistralowe");
		setUITextName("INC_DEC_ACCUMULATOR_CHECK_BOX", "Inkrementacja i dekrementacja akumulatora");
		setUITextName("LOGICAL_OPERATIONS_ALU_CHECK_BOX", "Operacje logiczne w JAL");
		setUITextName("EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX", "Rozszerzone operacje atytmetyczne w JAL");
		setUITextName("STACK_MANAGEMENT_CHECK_BOX", "Obsługa stosu");
		setUITextName("X_REGISTER_CHECK_BOX", "Rejestr X");
		setUITextName("Y_REGISTER_CHECK_BOX", "Rejestr Y");
		setUITextName("INPUT_OUTPUT_CHECK_BOX", "Wejście/wyjście");
		setUITextName("ADDITIONAL_MARKS_CHECK_BOX", "Dodatkowe znaczniki");
		setUITextName("ARCHITECTURE_TAB", "Architektura");
		setUITextName("ADDRESS_BIT_COUNT_INPUT", "Liczba bitów adresowych");
		setUITextName("CODE_BIT_COUNT_INPUT", "Liczba bitów kodu");
		setUITextName("ADDRESSES_TAB", "Adresy");
		setUITextName("ADRESS_LABEL", "Adres (port)");
		setUITextName("INTERRUPT_LABEL", "Przerwanie");
		setUITextName("ADDRESS_INPUT_LABEL", "Wejście");
		setUITextName("ADDRESS_OUTPUT_LABEL", "Wyjście");
		setUITextName("FORUM_TAB", "Forum");
		setUITextName("LOGOUT_TAB", "Wyloguj się");
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
	
	/**
	 * Add translated UI element to language.
	 * @param element name of constant defined in UIElements
	 * @param name translated UI element name
	 */
	public void setUITextName(String element, String name) {
		try {
			UIElements value = UIElements.valueOf(element);
			if(value != null)
				userInterface.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
}
