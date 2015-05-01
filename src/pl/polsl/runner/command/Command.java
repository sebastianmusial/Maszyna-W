package pl.polsl.runner.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.polsl.architecture.Flag;
import pl.polsl.architecture.WMachine;
import pl.polsl.runner.tact.Tact;
import pl.polsl.runner.tact.branch.Branch;
import pl.polsl.runner.tact.branch.ConditionalStatement;
import pl.polsl.runner.tact.branch.EndStatement;

/**
 * Class represents flow of command execution.
 * If any conditional statement occurs in the command, the label
 * to start point of execution is stored in map.
 * @author Dawid Poloczek
 * @version 1.0
 */
public class Command {	
	/** Command name - used in program. */
	private String name;
	
	/** Command description, i.e. first comment in command definition. */
	private String description;
	
	/** Argument count. Command require argument by default. */
	private Integer argumentCount = 1;
	
	/** List of command tacts. */
	private List<Tact> tacts = new ArrayList<>();
	
	/** Labels mapped to tact indices. */
	private Map<String, Integer> labels = new HashMap<>();
	
	/**
	 * Command name getter.
	 * @return Command name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Command name setter.
	 * @param commandName name to be set
	 */
	public void setName(String commandName){
		this.name = commandName;
	}
	
	/**
	 * Command description getter.
	 * @return Command description.
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Command description setter.
	 * @param commandDescription description to be set.
	 */
	public void setDescription(String commandDescription){
		this.description = commandDescription;
	}
	
	/**
	 * Command argument count getter.
	 * @return Command argument count.
	 */
	public Integer getArgumentCount(){
		return argumentCount;
	}
	
	/**
	 * Command argument count setter.
	 * @param argumentCount argument count to be set.
	 */
	public void setArgumentCount(Integer argumentCount){
		this.argumentCount = argumentCount;
	}
	
	/**
	 * Command tacts collection getter.
	 * @return Command tacts collection.
	 */
	public List<Tact> getTacts(){
		return this.tacts;
	}
	
	/**
	 * Allow to add new tact to this command.
	 * @param tact tact to be added
	 */
	public void addTact(Tact tact){
		if(tact == null)
			return;
		
		if(tact.isLabelled()) {
			labels.put(tact.getLabel(), tacts.size());
		}
		
		if(tact.canBranch()) {
			Branch branch = tact.getBranch();
			
			if(branch instanceof ConditionalStatement) {
				ConditionalStatement conditionalStatement = (ConditionalStatement)branch;
				labels.putIfAbsent(conditionalStatement.getTrueLabel(), null);
				labels.putIfAbsent(conditionalStatement.getFalseLabel(), null);
			}
		}
		
		tacts.add(tact);
	}
	
	/**
	 * Allow to run command on W Machine instance.
	 * @param machine W Machine instance that command will be run on
	 */
	public void run(WMachine machine) {
		for(int i = 0; i < tacts.size(); ) {
			Tact tact = tacts.get(i);
			if(tact.hasSignals())
				tact.run(machine);
			if(tact.canBranch()) {
				Branch branch = tact.getBranch();
				if(branch instanceof ConditionalStatement) {
					ConditionalStatement conditionalStatement = (ConditionalStatement)branch;
					String flagName = conditionalStatement.getCondition();
					Flag flag = Flag.valueOf(flagName);
					if(machine.getFlag(flag))
						i = labels.get(conditionalStatement.getTrueLabel());
					else
						i = labels.get(conditionalStatement.getFalseLabel());
				}
				else if(branch instanceof EndStatement) {
					i = tacts.size();
				}
			}
			else {
				++i;
			}
			machine.nextTact();
		}
	}
	
	/**
	 * Check if command is valid.
	 * TODO: throw another exception
	 */
	protected void validateCommand() {
		labels.forEach( (k, v) -> {
			if(v == null)
				throw new NullPointerException("Label " + k +" not found");
		});
	}
}
