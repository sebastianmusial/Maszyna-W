package pl.polsl.i18n;

import java.util.Map;
import java.util.HashMap;

/**
 * Class representing language. Split all translations
 * into several categories: registers, signals and UI.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Language {
	/** Translations in category "registers". */
	Map<Integer, String> registers = new HashMap<>();
	/** Translations in category "signals". */
	Map<Integer, String> signals = new HashMap<>();
	/** Translations in category "userInterface". */
	Map<Integer, String> userInterface = new HashMap<>();
	
	/**
	 * Translated registers names getter.
	 * @param registerId ID of a register
	 * @return Translated name of the register with ID <i>registerId</i>.
	 */
	public String getRegisterName(Integer registerId) {
		return registers.get(registerId);
	}
	
	/**
	 * Translated registers names setter.
	 * @param registerId ID of a register
	 * @param name translated name of a register
	 */
	public void setRegisterName(Integer registerId, String name) {
		registers.put(registerId, name);
	}
	
	/**
	 * Translated signals names getter.
	 * @param signalId ID of a signal
	 * @return Translated name of the signal with ID <i>signalId</i>.
	 */
	public String getSignalName(Integer signalId) {
		return signals.get(signalId);
	}
	
	/**
	 * Translated signals names setter.
	 * @param signalId ID of a signal
	 * @param name translated name of a signal
	 */
	public void setSignalName(Integer signalId, String name) {
		signals.put(signalId, name);
	}
	
	/**
	 * Translated UI texts getter.
	 * @param uiTextId ID of UI text
	 * @return Translated UI text with ID <i>uiTextId</i>.
	 */
	public String getUIText(Integer uiTextId) {
		return userInterface.get(uiTextId);
	}
	
	/**
	 * Translated UI texts setter.
	 * @param uiTextId ID of UI text
	 * @param text translated UI text
	 */
	public void setUITextName(Integer uiTextId, String text) {
		userInterface.put(uiTextId, text);
	}
}
