package pl.polsl.i18n.keywords;

/**
 * 
 * @author Dawid Poloczek
 * @version 1.0
 */
public enum Keywords {
	/** Command definition. Must be followed by command name. */
	COMMAND(1),
	/** Beginning of conditional statement. */
	IF(2),
	/** End of condition in conditional statement. */
	THEN(3),
	/** Alternative path if condition is not true. */
	ELSE(4),
	/** Finish command execution. */
	END(5),
	/** Declare command arguments count. */
	ARGUMENTS(6);
	
	/** Command ID. */
	public final Integer ID;
	
	/**
	 * Construct keyword with ID.
	 * @param ID ID of the keyword
	 */
	private Keywords(Integer ID){
		this.ID = ID;
	}
}
