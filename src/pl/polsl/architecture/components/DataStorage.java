package pl.polsl.architecture.components;

import pl.polsl.architecture.data.DataWord;

/**
 * Abstract class representing generic data storage.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public abstract class DataStorage implements DataSource, DataTarget {

	/** Stored data. */
	private DataWord data;
	
	/**
	 * Creates data storage configured to keep
	 * bitCount long data words.
	 * @param data data instance to be stored
	 */
	public DataStorage(DataWord data) {
		this.data = data;
	}
	
	/**
     * Allow to get currently stored data.
     * @return Stored data.
     */
    public DataWord getData() {
        return data;
    }
}
