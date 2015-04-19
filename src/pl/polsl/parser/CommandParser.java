package pl.polsl.parser;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.polsl.i18n.DefaultLanguage;
import pl.polsl.i18n.Language;
import pl.polsl.i18n.keyWords.DefaultKeyWordsLanguage;
import pl.polsl.i18n.keyWords.KeyWords;
import pl.polsl.i18n.keyWords.KeyWordsLanguage;

/**
 * Parser of the given command.
 * @author Dawid Poloczek
 * @version 1.0
 */
public class CommandParser {
	
	Language lang;
	
	KeyWordsLanguage keyWordsLang;
	
	public CommandParser(){
		this.lang = new DefaultLanguage();
		this.keyWordsLang = new DefaultKeyWordsLanguage();
	}
	
	public Command parseText(String text){
		if(text == null || "".equals(text)){
			throw new IllegalArgumentException("Text is empty/null");
		}
		
		Command command = new Command();
		command.SetCommandDescription("Przykładowy opis");
		command.SetCommandName("Przykładowa nazwa");
		text = text.trim();
		String[] trimmedText = text.split("\n");
		String commandLineRegex = this.getConditionStatementRegex();
		
		for(int i = 0; i < trimmedText.length; i++){
			String line = trimmedText[i];
			line = line.trim();
			ITact parsedCommandLine = null;
			
			if(line.charAt(0) == '@'){
				parsedCommandLine = this.parseLineWithLabel(line);
			}
			else if(line.matches("(([a-zA-Z]+[ ]+)*[a-zA-Z]+);")){
				parsedCommandLine = this.parseLineOfSignals(line);	
			}
			else if(line.toUpperCase().matches(commandLineRegex)){
				parsedCommandLine = this.parseConditionStatement(line);
			}
			else{
				throw new InvalidParameterException("Parser error at line " + (i+1));
			}
			command.AddCommandLine(parsedCommandLine);
		}
		return command;
	}
	
	private ITact parseConditionStatement(String line){
		ConditionStatement statement = new ConditionStatement();
		String[] splited = line.split("( )+");
		
		statement.setCondition(splited[1]);
		statement.setTrueLabel(splited[3]);
		statement.setFalseLabel(splited[6].replace(";", ""));
		
		return statement;
	}
	
	private ITact parseLineOfSignals(String line){
		line = line.trim();
		SetOfSignals signalsSet = new SetOfSignals();
		List<Integer> signalsList = this.getListOfSignalsIndexes(line);
		
		signalsSet.setSignalsList(signalsList);
		
		return signalsSet;
	}
	
	private String getConditionStatementRegex(){
		StringBuilder regex = new StringBuilder();
		regex.append(keyWordsLang.getKeyWord(KeyWords.IF.ID));
		regex.append("( )+[A-Z]+( )+");
		regex.append(keyWordsLang.getKeyWord(KeyWords.THEN.ID));
		regex.append("( )+@[A-Z]+( )+");
		regex.append(keyWordsLang.getKeyWord(KeyWords.IF_NOT.ID));
		regex.append("( )+@[A-Z]+( )*;");
		return regex.toString();
				
	}
	
	private ITact parseLineWithLabel(String line){
		LineWithLabel lineWithLabel = new LineWithLabel();
		line = line.trim();
		
		int spacePos = line.indexOf(" ");
		String label = line.substring(0, spacePos);
		line = line.substring(spacePos + 1);
		
		List<Integer> signalsList = this.getListOfSignalsIndexes(line);
		
		lineWithLabel.setLabel(label);
		lineWithLabel.setSignalsList(signalsList);
		
		return lineWithLabel;
	}
	
	private List<Integer> getListOfSignalsIndexes(String line){
		String[] lineWords = line.split("[ ;]");
		Map<Integer, String> availableSignalsMap = lang.getSignalsMap();
		List<Integer> resultList = new ArrayList();
		
		for(String word: lineWords){
			boolean isValidSignal = false;
			Integer indexOfValidSignal = null;
			
			for(Integer index: availableSignalsMap.keySet()){
				String pattern = availableSignalsMap.get(index);
				
				if(pattern.equalsIgnoreCase(word)){
					isValidSignal = true;
					indexOfValidSignal = index;
					break;
				}
			}
			
			if(!isValidSignal){
				throw new IllegalArgumentException("Illegal signal: " + word);
			}
			
			resultList.add(indexOfValidSignal);
		}
		
		return resultList;
	}
}
