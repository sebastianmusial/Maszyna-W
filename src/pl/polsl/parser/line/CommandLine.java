package pl.polsl.parser.line;

/**
 * Interface representing single line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public interface CommandLine {
	/**
	 * Method to check if line is valid.
	 * @return Logical value indicating if line is valid.
	 */
	boolean isValid();
}
