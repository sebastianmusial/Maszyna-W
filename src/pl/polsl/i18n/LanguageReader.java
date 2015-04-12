package pl.polsl.i18n;

import pl.polsl.servlet.ArchitectureInfo;

/**
 * Reader reads file contents and returns Language object.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class LanguageReader {
	
	/**
	 * Read language from file.
	 * @param filename path to file with language definition
	 * @return Language that was read from file.
	 */
	public Language readLanguage(String filename) {
		// TODO: read language from file
		return new Language();
	}
	
	/**
	 * Add translated resiter name to language.
	 * @param lang language the name will be added to
	 * @param register name of constant defined in ArchitectureInfo#AvailableRegisters 
	 * @param name translated register name
	 */
	public void addRegisterName(Language lang, String register, String name) {
		try {
			ArchitectureInfo.AvailableRegisters value = ArchitectureInfo.AvailableRegisters.valueOf(register);
			if(value != null)
				lang.setRegisterName(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
	
	/**
	 * Add translated signal name to language.
	 * @param lang language the name will be added to
	 * @param signal name of constant defined in ArchitectureInfo#AvailableSignals
	 * @param name translated signal name
	 */
	public void addSignalName(Language lang, String signal, String name) {
		try {
			ArchitectureInfo.AvailableSignals value = ArchitectureInfo.AvailableSignals.valueOf(signal);
			if(value != null)
				lang.signals.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
	
	/**
	 * Add translated UI element to language
	 * @param lang language the name will be added to
	 * @param uiElement name of constant defined in UIElements
	 * @param name translated UI element name
	 */
	public void addUIElementText(Language lang, String uiElement, String name) {
		try {
			UIElements value = UIElements.valueOf(uiElement);
			if(value != null)
				lang.userInterface.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}
}
