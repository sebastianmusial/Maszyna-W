/**
 * 
 */
package pl.polsl.i18n.keyWords;

/**
 * @author Dawid Poloczek
 * @version 1.0
 */
public class DefaultKeyWordsLanguage extends KeyWordsLanguage {

	public DefaultKeyWordsLanguage(){
		setKeyWord("COMMAND", "COMMAND");
		setKeyWord("IF", "IF");
		setKeyWord("THEN", "THEN");
		setKeyWord("IF_NOT", "IF NOT");
		setKeyWord("END", "END");	
	}
	
	/**
	 * Add translated key word name to language.
	 * @param keyWord name of constant defined in ArchitectureInfo#AvailableRegisters 
	 * @param name translated register name
	 */
	public void setKeyWord(String keyWord, String name) {
		try {
			name = name.toUpperCase();
			KeyWords value = KeyWords.valueOf(keyWord);
			if(value != null)
				keyWords.put(value.ID, name);
		}
		catch(IllegalArgumentException ex) {
			
		}
	}	
}
