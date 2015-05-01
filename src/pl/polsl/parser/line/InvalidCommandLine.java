package pl.polsl.parser.line;

/**
 * Class representing invalid line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class InvalidCommandLine implements CommandLine {
	/**
	 * Implementation of CommandLine interface.
	 * @return Always false.
	 */
	@Override
	public boolean isValid() {
		return false;
	}
}
