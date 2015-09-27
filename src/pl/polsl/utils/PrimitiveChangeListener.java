package pl.polsl.utils;

/**
 * Change listener interface. Used to listen for value
 * changes in Primitive class.
 * @author Tomasz Rzepka
 * @version 1.0
 * @param <T> type that primitive is wrapping
 */
public interface PrimitiveChangeListener<T> {
	/**
	 * When value stored in primitive is changed,
	 * that primitive calls this function.
	 * @param primitive calling primitive
	 */
	public void primitiveChanged(Primitive<T> primitive);
	
}
