package pl.polsl.runner.tact;

import java.util.List;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.runner.tact.branch.Branch;
import pl.polsl.runner.tact.branch.ConditionalStatement;

/**
 * Class representing single tact.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Tact {
	/** Label assigned to this tact. */
	private String label = null;
	
	/** Signals to be activated in this tact. */
	private List<Integer> signals = null;
	
	/** Branch in current tact. */
	private Branch branch = null;
	
	/**
	 * Constructs tact. Valid tact is a tact that contains:
	 * - only list of signals,
	 * - only branch,
	 * - label and list of signals,
	 * - list of signals and branch.
	 * @param label label identifying this line
	 * @param signals signals to be activated in the tact
	 * @param branch branch that will be perfomed
	 */
	public Tact(String label, List<Integer> signals, Branch branch) {
		if(label != null)
			this.label = label.trim();
		this.signals = signals;
		this.branch = branch;
	}
	
	/**
	 * Check if tact has label.
	 * @return True if tact has label, false otherwise.
	 */
	public Boolean isLabelled() {
		if(label == null || "".equals(label))
			return false;
		return true;
	}
	
	/**
	 * Label getter.
	 * @return Label.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Check if tact can branch.
	 * @return True if tact can branch, false if not.
	 */
	public Boolean canBranch() {
		if(branch == null)
			return false;
		return true;
	}
	
	/**
	 * Branch getter.
	 * @return Branch.
	 */
	public Branch getBranch() {
		return branch;
	}
	
	/**
	 * Check if tact has signals to be activated.
	 * @return True if tact will activate any signal, false otherwise.
	 */
	public Boolean hasSignals() {
		return !signals.isEmpty();
	}
	
	/**
	 * Activate signals in this tact on W Machine instance.
	 * @param machine W Machine instance that signals will be activated on
	 */
	public void run(WMachine machine) {
		try {
			// TODO begin transation, commit on success, rollback on failure
			for(Integer signalId : signals) {
				Signal signal = machine.getSignal(signalId);
				signal.activate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
