package pl.polsl.parser.line;

/**
 * Class representing comment line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandCommentLine implements CommandLine {
	/** Comment. */
	private String comment;
	
	/**
	 * Constructs line with comment.
	 * @param comment comment in this line
	 */
	public CommandCommentLine(String comment) {
		this.comment = comment;
	}
	
	/**
	 * Comment getter.
	 * @return Comment in this line.
	 */
	public String getComment() {
		return comment;
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
