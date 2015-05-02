package pl.polsl.parser;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import pl.polsl.i18n.DefaultLanguage;
import pl.polsl.i18n.Language;
import pl.polsl.i18n.keywords.DefaultKeywordsLanguage;
import pl.polsl.i18n.keywords.Keywords;
import pl.polsl.i18n.keywords.KeywordsLanguage;
import pl.polsl.parser.line.CommandArgumentCountLine;
import pl.polsl.parser.line.CommandCommentLine;
import pl.polsl.parser.line.CommandDefinitionLine;
import pl.polsl.parser.line.CommandLine;
import pl.polsl.parser.line.CommandTactLine;
import pl.polsl.parser.line.InvalidCommandLine;
import pl.polsl.runner.command.Command;
import pl.polsl.runner.tact.Tact;
import pl.polsl.runner.tact.branch.Branch;
import pl.polsl.runner.tact.branch.ConditionalStatement;

/**
 * Parser of the given command.
 * @author Dawid Poloczek
 * @version 1.0
 */
public class CommandParser {
	/** Language of signals. */
	private Language language;
	
	/** Language of keywords. */
	private KeywordsLanguage keywordsLanguage;
	
	/** Current parser state. */
	private ParserState state;
	
	/**
	 * Default constructor. Uses DefaultLanguage
	 * and DefaultKeyWordsLanguage.
	 */
	public CommandParser(){
		this.language = new DefaultLanguage();
		this.keywordsLanguage = new DefaultKeywordsLanguage();
	}
	
	/**
	 * Method to parse text with command definition.
	 * @param text command definition
	 * @return Parsed command.
	 */
	public Command parse(String text){
		if(text == null || "".equals(text)){
			throw new IllegalArgumentException("Text is empty/null");
		}
		
		state = new ParserState();
		CommandLineParser lineParser = new CommandLineParser(keywordsLanguage);
		
		String[] lines = text.trim().split("\n");
		for(int i = 0; i < lines.length; i++){
			CommandLine commandLine = lineParser.parse(lines[i].trim());
			if(commandLine instanceof CommandCommentLine) {
				handleCommentLine((CommandCommentLine)commandLine);
			}
			else {
				state.descriptionFound = true;
				if(commandLine instanceof CommandTactLine)
					handleTactLine((CommandTactLine)commandLine);
				else if(commandLine instanceof CommandDefinitionLine)
					handleDefinitionLine((CommandDefinitionLine)commandLine);
				else if(commandLine instanceof CommandArgumentCountLine)
					handelArgumentCountLine((CommandArgumentCountLine)commandLine);
				else if(commandLine instanceof InvalidCommandLine)
					throw new InvalidParameterException("Invalid line " + (i+1) + ": " + lines[i]);
				else
					throw new InvalidParameterException("Unknown parse error at line " + (i+1));
			}
		}
		state.command.setDescription(state.description.toString());
		return state.command;
	}

	private void handleTactLine(CommandTactLine tactLine) {
		String label = tactLine.getLabel();
		List<Integer> signals = getSignalIds(tactLine.getSignals()); 
		Branch branch = tactLine.getBranch();
		state.command.addTact(new Tact(label, signals, branch));
	}
	
	private void handleCommentLine(CommandCommentLine commentLine) {
		if(!state.descriptionFound) {
			if(state.description.length() > 0)
				state.description.append(" ");
			state.description.append(commentLine.getComment());
		}
	}
	
	private void handleDefinitionLine(CommandDefinitionLine definitionLine) {
		if(!state.nameFound)
			state.command.setName(definitionLine.getName());
		else
			throw new InvalidParameterException("Redefinition of command name.");
	}

	private void handelArgumentCountLine(CommandArgumentCountLine argumentCountLine) {
		if(!state.argumentCountDeclared) {
			state.argumentCountDeclared = true;
			state.command.setArgumentCount(argumentCountLine.getArgumentCount());
		}
		else
			throw new InvalidParameterException("Redefinition of command argument count.");
	}
	
	/**
	 * Class representing internal state of the parser.
	 * @author Tomasz Rzepka
	 * @version 1.0
	 */
	private class ParserState {
		/** Line currently being parsed. */
		public Integer currentLine = 0;
		
		/** Logical value indicating if command name was found and set. */
		public Boolean nameFound = false;
		
		/** Logical value indicating if command description was found. */
		public Boolean descriptionFound = false;
		
		/** Logical value indicating if command argument count was found. */
		public Boolean argumentCountDeclared = false;
		
		/** Description builder. */
		public StringBuilder description = new StringBuilder();
		
		/** Currently parsed command. */
		public Command command = new Command();
	}
	
	private List<Integer> getSignalIds(String[] signals){
		if(signals == null)
			return new ArrayList<Integer>();
		
		Map<Integer, String> availableSignalsMap = language.getSignalsMap();
		Set<Integer> result = new TreeSet<Integer>();
		String endKeyword = keywordsLanguage.getKeyword(Keywords.END);
		
		for(int i = 0; i < signals.length; ++i) {
			String word = signals[i];
			if(endKeyword.equalsIgnoreCase(word)) {
				if((i+1) != signals.length)
					throw new IllegalArgumentException("END keyword should be last in tact.");
				result.add(-1);
				break;
			}
				
			boolean isValidSignal = false;
			
			for(Integer id : availableSignalsMap.keySet()){
				String pattern = availableSignalsMap.get(id);
				if(pattern.equalsIgnoreCase(word)){
					isValidSignal = true;
					result.add(id);
					break;
				}
			}
			
			if(!isValidSignal){
				throw new IllegalArgumentException("Illegal signal: " + word);
			}
		}
		
		return new ArrayList<Integer>(result);
	}
	
	
}
