package pl.polsl.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents flow of command execution.
 * If any condition statement occurs in the command, the label
 * to start point of execution is store in map.
 * @author Dawid Poloczek
 * @version 1.0
 */
public class Command {

	private String commandDesc;
	
	private String commandName;
	
	private List<ITact> commandLines = new ArrayList<ITact>();
	
	private Map<String, Integer> mapOfJumpLabels = new HashMap<String, Integer>();
	
	protected void SetCommandName(String commandName){
		this.commandName = commandName;
	}
	
	protected void SetCommandDescription(String commandDescription){
		this.commandDesc = commandDescription;
	}
	
	protected void AddCommandLine(ITact line){
		if(line == null)
			throw new NullPointerException("line");
		
		if(line instanceof ConditionStatement){
			ConditionStatement condStat = (ConditionStatement)line;
			String ifFalseLabel = condStat.getFalseLabel();
			String ifTrueLabel = condStat.getTrueLabel();
			
			mapOfJumpLabels.putIfAbsent(ifTrueLabel, null);
			mapOfJumpLabels.putIfAbsent(ifFalseLabel, null);		
		}
		else if(line instanceof LineWithLabel){
			LineWithLabel labledLine = (LineWithLabel)line;
			String lineLabel = labledLine.getLabel();
			
			mapOfJumpLabels.put(lineLabel, commandLines.size());
		}
		
		this.commandLines.add(line);
	}
	
	public String GetCommandName(){
		return this.commandName;
	}
	
	public String GetCommandDescription(){
		return this.commandDesc;
	}
	
	public List<ITact> GetCommandLines(){
		return this.commandLines;
	}
	
	public Map<String, Integer> GetMapOfLabels(){
		return this.mapOfJumpLabels;
	}
	
	protected void ValidateCommand(){
		
		mapOfJumpLabels.forEach( (k, v) -> {
			if(v == null)
				throw new NullPointerException("Label " + k +" not found");
		});
	}
}
