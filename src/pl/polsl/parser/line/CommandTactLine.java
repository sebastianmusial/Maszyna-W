package pl.polsl.parser.line;

import pl.polsl.runner.tact.branch.Branch;
import pl.polsl.runner.tact.branch.ConditionalStatement;

/**
 * Class representing command tact line in command definition.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class CommandTactLine implements CommandLine {
	/** Label identifying this line. */
	private String label = null;
	
	/** Signals to be activated in this tact. */
	private String[] signals = null;
	
	/** Conditional statement. */
	private Branch branch = null;
	
	/**
	 * Constructs tact line.
	 * @param label label identifying this line
	 * @param signals signals present in this line
	 * @param branch branch present in this line
	 */
	public CommandTactLine(String label, String signals, Branch branch) {
		this.label = label;
		if(signals != null)
			this.signals = signals.split(" +");
		this.branch = branch;
	}
	
	/**
	 * Line label getter.
	 * @return Line label.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Line signals getter.
	 * @return Line signals.
	 */
	public String[] getSignals() {
		return signals;
	}
	
	/**
	 * Line branch getter.
	 * @return Line branch.
	 */
	public Branch getBranch() {
		return branch;
	}
	
	/**
	 * Implementation of CommandLine interface.
	 * @return True if line has signals or .
	 */
	@Override
	public boolean isValid() {
		if(signals.length == 0 && branch == null)
			return false;
		return true;
	}
}
