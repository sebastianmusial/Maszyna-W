package pl.polsl.parser;

import pl.polsl.i18n.keywords.Keywords;
import pl.polsl.i18n.keywords.KeywordsLanguage;
import pl.polsl.parser.line.CommandArgumentCountLine;
import pl.polsl.parser.line.CommandCommentLine;
import pl.polsl.parser.line.CommandDefinitionLine;
import pl.polsl.parser.line.CommandLine;
import pl.polsl.parser.line.CommandTactLine;
import pl.polsl.parser.line.InvalidCommandLine;
import pl.polsl.runner.tact.branch.Branch;
import pl.polsl.runner.tact.branch.ConditionalStatement;
import pl.polsl.runner.tact.branch.EndStatement;

/**
 * Parser of single command line.
 * @author Tomasz Rzepka
 * @version 1.0
 */
public final class CommandLineParser {
	/** Keywords language used to build pattern. */
	KeywordsLanguage language;
	
	/** Regular expression describing line. */
	RegularExpression regex;
	
	/**
	 * Create internationalized parser.
	 * @param language command language
	 */
	public CommandLineParser(KeywordsLanguage language) {
		this.language = language;
		this.regex = prepareLinePattern();
	}
	
	/**
	 * Parse given line.
	 * @param line line to be parsed
	 * @return Parsed command line.
	 */
	public CommandLine parse(String line) {
		String[] groups = regex.matchGroups(line);
		if(groups == null) {
			return new InvalidCommandLine();
		}
		
		String comment = find(groups, 1);
		if(comment != null)
			return new CommandCommentLine(comment);
		
		String name = find(groups, 2);
		if(name != null)
			return new CommandDefinitionLine(name);
		
		String argumentCount = find(groups, 3);
		if(argumentCount != null)
			return new CommandArgumentCountLine(Integer.parseInt(argumentCount));
		
		String label = find(groups, 10, 19);
		String signals = find(groups, 4, 11, 12, 20);
		String flag = find(groups, 7, 15, 23);
		String ifTrue = find(groups, 8, 16, 24);
		String ifFalse = find(groups, 9, 17, 25);
		Branch branch = null;
		if(flag != null && ifTrue != null && ifFalse != null) {
			ConditionalStatement conditionalStatement = new ConditionalStatement();
			conditionalStatement.setCondition(flag);
			conditionalStatement.setTrueLabel(ifTrue);
			conditionalStatement.setFalseLabel(ifFalse);
			branch = conditionalStatement; 
		}
		else if(signals.toUpperCase().endsWith(language.getKeyword(Keywords.END))) {
			Integer signalsLength = signals.length();
			Integer keywordLength = language.getKeyword(Keywords.END).length();
			signals = signals.substring(0, signalsLength - keywordLength - 1).trim();
			branch = new EndStatement();
		}
		
		return new CommandTactLine(label, signals, branch);
	}
	
	/**
	 * Find string in array at given indices.
	 * @param groups array to be searched
	 * @param indices indices to be checked
	 * @return String found in array or null if no string is present at given indices.
	 */
	private String find(String[] groups, Integer ... indices) {
		for(Integer index : indices) {
			if(groups[index] != null)
				return groups[index];
		}
		return null;
	}
	
	/**
	 * Prepare internationalized regular expression used to match lines.
	 * @return Internationalized regular expression used to match lines.
	 */
	private RegularExpression prepareLinePattern() {
		RegularExpression label = new RegularExpression();
		RegularExpression onlySignals = new RegularExpression();
		RegularExpression conditionalStatement = new RegularExpression();
		RegularExpression onlyBranch = new RegularExpression();
		RegularExpression labelAndSignals = new RegularExpression();
		RegularExpression signalsAndBranch = new RegularExpression();
		RegularExpression labelSignalsAndBranch = new RegularExpression();
		RegularExpression comment = new RegularExpression();
		RegularExpression definition = new RegularExpression();
		RegularExpression arguments = new RegularExpression();
		RegularExpression linePattern = new RegularExpression();
		
		label.exactly("@").letter().many().group();
		
		onlySignals.characters("a-zA-Z ").many().group().notFollowedBy(language.getKeyword(Keywords.END));
		
		labelAndSignals.exactly("@")
					   .letter().many()
					   .group()
					   .whitespace().many()
					   .expression(onlySignals);
		
		conditionalStatement.exactly(language.getKeyword(Keywords.IF))
							.whitespace().many()
							.openGroup()
							.letter().many()
							.closeGroup()
							.whitespace().many()
							.exactly(language.getKeyword(Keywords.THEN))
							.whitespace().many()
							.expression(label)
							.whitespace().many()
							.exactly(language.getKeyword(Keywords.ELSE).replace(" ", " +"))
							.whitespace().many()
							.expression(label)
						  	.group();
		
		onlyBranch.expression(conditionalStatement)
				  .or()
				  .exactly(language.getKeyword(Keywords.END))
				  .group();
				  
		signalsAndBranch.expression(onlySignals)
						.whitespace().many()
						.expression(onlyBranch);
		
		labelSignalsAndBranch.expression(label)
							 .whitespace().many()
							 .expression(onlySignals)
							 .whitespace().many()
							 .expression(onlyBranch)
							 .group();
		
		comment.exactly("//")
			   .whitespace().any()
			   .openGroup()
			   .anything().any()
			   .closeGroup();
		
		definition.exactly(language.getKeyword(Keywords.COMMAND))
				  .whitespace().many()
				  .openGroup()
				  .letter().many()
				  .closeGroup();
		
		arguments.exactly(language.getKeyword(Keywords.ARGUMENTS))
				 .whitespace().many()
				 .openGroup()
				 .digit().many()
				 .closeGroup();
		
		linePattern.begin()
				   .openGroup()
				   .expression(comment)
		   		   .or()
		   		   .expression(definition)
		   		   .or()
		   		   .expression(arguments)
		   		   .or()
				   .expression(onlySignals)
				   .or()
				   .expression(onlyBranch)
				   .or()
				   .expression(labelAndSignals)
				   .or()
				   .expression(signalsAndBranch)
				   .or()
				   .expression(labelSignalsAndBranch)
				   .closeGroup()
				   .exactly(";").optional()
				   .end();

		return linePattern;
	}
}
