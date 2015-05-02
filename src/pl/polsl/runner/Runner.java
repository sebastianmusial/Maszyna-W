package pl.polsl.runner;

import pl.polsl.architecture.WMachine;
import pl.polsl.architecture.components.finalized.MemoryCell;
import pl.polsl.architecture.signals.Signal;
import pl.polsl.runner.command.Command;
import pl.polsl.servlet.ArchitectureInfo.AvailableSignals;
import pl.polsl.settings.TrackLevel;

/**
 * Runner allow to run program, command or tact.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public class Runner {
	private WMachine machine;
	
	private Command currentCommand;
	
	public Runner(WMachine machine) {
		this.machine = machine;
	}
	
	public void run(TrackLevel trackLevel) {
		if(machine.isStopped())
			return;
		// Transaction txn = new Transaction(); try { txn.begin()
		switch(trackLevel) {
			case LOW: runProgram(); break;
			case MEDIUM: runCommand(); break;
			case HIGH: runTact(); break;
		}
		// txn.commit() } catch(Exception) { txn.rollback(); }
	}
	
	public void runManually() {
		currentCommand = null;
		// Transaction txn = new Transaction();
		try {
			// txn.begin()
			for(AvailableSignals signalId : AvailableSignals.values()) {
				Signal signal = machine.getSignal(signalId.ID);
				if(signal == null)
					continue;
				if(signal.isEnabled())
					signal.activate();
			}
			// txn.commit()
		}
		catch(Exception ex) {
			// txn.rollback();
		}
	}
	
	private void runProgram() {
		while(!machine.isStopped())
			runCommand();
	}
	
	private void runCommand() {
		if(currentCommand != null) {
			while(currentCommand != null)
				runTact();
		}
		else {
			currentCommand = getNextCommand();
			currentCommand.run(machine);
			currentCommand = null;
		}
	}
	
	private void runTact() {
		if(currentCommand == null)
			currentCommand = getNextCommand();
		Boolean finished = currentCommand.runTact(machine);
		if(finished)
			currentCommand = null;
	}
	
	private Command getNextCommand() {
		MemoryCell cell = machine.getMemory().getCurrentCell();
		CommandList commandList = machine.getCommandList();
		return commandList.get(cell.getOpCode());
	}
}
