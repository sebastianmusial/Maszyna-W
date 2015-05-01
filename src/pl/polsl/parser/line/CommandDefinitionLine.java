package pl.polsl.parser.line;

/**
 * Class representing command definition line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandDefinitionLine implements CommandLine {
	/** Command name. */
	private String name;
	
	/**
	 * Constructs line with comment.
	 * @param name comment in this line
	 */
	public CommandDefinitionLine(String name) {
		this.name = name;
	}
	
	/**
	 * Command name getter.
	 * @return Command name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Implementation of CommandLine interface.
	 * @return True if name is neither null nor empty.
	 */
	@Override
	public boolean isValid() {
		if(name == null || "".equals(name))
			return false;
		return true;
	}
}
