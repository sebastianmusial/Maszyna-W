package pl.polsl.architecture.components;

import pl.polsl.architecture.data.Data;

/**
 * Abstract class representing generic data storage.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public abstract class DataStorage implements DataSource, DataTarget {

	/** Stored data. */
	private Data data;
	
	/**
	 * Creates data storage configured to keep
	 * bitCount long data words.
	 * @param data data instance to be stored
	 */
	public DataStorage(Data data) {
		this.data = data;
	}
	
	/**
     * Allow to get currently stored data.
     * @return Stored data.
     */
    public Data getData() {
        return data;
    }
}
