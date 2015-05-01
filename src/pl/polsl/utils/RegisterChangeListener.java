package pl.polsl.utils;

import pl.polsl.architecture.data.DataWord;

/**
 * Register change listener interface. Used to listen for value
 * changes in Register class.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface RegisterChangeListener {
	/**
	 * When value stored in register is changed,
	 * that register calls this function.
	 * @param command command that listener was registered with
	 * @param data data stored in the register
	 */
	void registerValueChanged(String command, DataWord data);
}
