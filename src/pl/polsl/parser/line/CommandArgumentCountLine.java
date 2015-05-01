package pl.polsl.parser.line;

/**
 * Class representing argument count declaration line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandArgumentCountLine implements CommandLine {
	/** Declared argument count. */
	private Integer argumentCount;
	
	/**
	 * Constructs line with argument count declaration.
	 * @param argumentCount declared argument count
	 */
	public CommandArgumentCountLine(Integer argumentCount) {
		this.argumentCount = argumentCount;
	}
	
	/**
	 * Argument count getter.
	 * @return Argument count declared in this line.
	 */
	public Integer getArgumentCount() {
		return argumentCount;
	}
	
	/**
	 * Implementation of CommandLine interface.
	 * @return Always true.
	 */
	@Override
	public boolean isValid() {
		return true;
	}
}
