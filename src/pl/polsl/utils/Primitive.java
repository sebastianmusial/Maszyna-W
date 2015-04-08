package pl.polsl.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for primitive types to pass them by reference
 * not by value.
 * @author Tomasz Rzepka
 * @version 1.0
 * @param <T> type to be wrapped
 */
public class Primitive<T> {
	/** Wrapped value. */
	private T value;
	
	private List<PrimitiveChangeListener<T>> listeners = new LinkedList<PrimitiveChangeListener<T>>();
	
	/**
	 * Value getter.
	 * @return Wrapped value.
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Value setter.
	 * @param value new value to be wrapped
	 */
	public void setValue(T value) {
		this.value = value;
		for(PrimitiveChangeListener<T> listener : listeners)
			listener.primitiveChanged(this);
	}
	
	public void addChangeListener(PrimitiveChangeListener<T> listener) {
		listeners.add(listener);
	}
}
